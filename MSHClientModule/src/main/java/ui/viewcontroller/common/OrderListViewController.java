package ui.viewcontroller.common;

import blservice.orderblservice.OrderBLService;
import blservice.orderblservice.OrderBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.order.OrderCellController;
import util.OrderState;
import vo.OrderVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/17.
 */
public class OrderListViewController {

    private MainUIController mainUIController;

    @FXML
    private VBox contentVBox;

    private OrderBLService orderBLService;

    /**
     * Initializes the OrderListViewController class. This method is automatically called
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

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void showOrders(OrderState orderState) {

        ArrayList<OrderVO> orders = orderBLService.searchOrder(orderState, null);
        try {
            for (OrderVO order : orders) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../component/order/OrderCell.fxml"));
                HBox ordercell = loader.load();

                OrderCellController orderCellController = loader.getController();
                orderCellController.setOrderListViewController(this);
                orderCellController.setOrder(order);

                contentVBox.getChildren().add(ordercell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOrderDetail() {
        mainUIController.showOrderDetail();
    }

}
