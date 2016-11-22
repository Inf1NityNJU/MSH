package ui.viewcontroller.order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.common.MainUIController;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/22.
 */
public class OrderDetailViewController {

    private MainUIController mainUIController;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label hotelNameLabel;

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void showOrder(OrderVO order) {
//        System.out.print("@@@");
        orderIDLabel.setText(order.orderID);
        hotelNameLabel.setText(order.hotelName);
    }

    @FXML
    private void clickBackButton() {
        mainUIController.showOrderList();
    }

}
