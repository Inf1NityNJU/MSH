package ui.viewcontroller.client;

import blservice.orderblservice.OrderBLService;
import blservice.orderblservice.OrderBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.order.ClientOrderCellController;
import ui.componentcontroller.order.ClientOrderSearchPaneController;
import util.OrderState;
import vo.OrderVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/17.
 */
public class ClientOrderListViewController {

    private ClientOrderViewController clientOrderViewController;

    @FXML
    private VBox contentVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[4];
    private Node[] cells = new Node[4];


    private OrderBLService orderBLService;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        orderBLService = new OrderBLService_Stub();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/order/ClientOrderSearchPane.fxml"));
            VBox pane = loader.load();

            ClientOrderSearchPaneController controller = loader.getController();
            controller.setClientOrderListViewController(this);

            contentVBox.getChildren().add(pane);

            for (int i = 0; i < 4; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/order/OrderCell.fxml"));
                HBox ordercell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = ordercell;
            }

            controller.showAllOrders();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setClientViewController(ClientOrderViewController clientOrderViewController) {
        this.clientOrderViewController = clientOrderViewController;
    }

    public void showOrders(OrderState orderState) {

        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        ArrayList<OrderVO> orders = orderBLService.searchOrder(orderState, null);

        //TODO is4
        for (int i = 0; i < orders.size(); i++) {

            OrderVO order = orders.get(i);
            FXMLLoader loader = cellLoaders[i];
            Node ordercell = cells[i];

            ClientOrderCellController clientOrderCellController = loader.getController();
            clientOrderCellController.setClientOrderListViewController(this);
            clientOrderCellController.setOrder(order);

            contentVBox.getChildren().add(ordercell);
        }

    }

    public void showClientOrderDetail(OrderVO order) {
        clientOrderViewController.showClientOrderDetail(order);
    }

}
