package ui.viewcontroller.client;

import blservice.orderblservice.OrderBLService;
import blservice.orderblservice.OrderBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.order.ClientOrderCellController;
import util.OrderState;
import vo.OrderVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/17.
 */
public class ClientOrderListViewController {

    private ClientViewController clientViewController;

    @FXML
    private VBox contentVBox;

    private OrderBLService orderBLService;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/order/OrderSearchPane.fxml"));
            VBox pane = loader.load();

            contentVBox.getChildren().add(pane);

            orderBLService = new OrderBLService_Stub();
            showOrders(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClientViewController(ClientViewController clientViewController) {
        this.clientViewController = clientViewController;
    }
    private void showOrders(OrderState orderState) {

        ArrayList<OrderVO> orders = orderBLService.searchOrder(orderState, null);
        try {
            for (OrderVO order : orders) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../component/order/OrderCell.fxml"));
                HBox ordercell = loader.load();

                ClientOrderCellController clientOrderCellController = loader.getController();
                clientOrderCellController.setClientOrderListViewController(this);
                clientOrderCellController.setOrder(order);

                contentVBox.getChildren().add(ordercell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClientOrderDetail(OrderVO order) {
        clientViewController.showClientOrderDetail(order);
    }

}
