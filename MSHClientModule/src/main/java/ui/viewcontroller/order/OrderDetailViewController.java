package ui.viewcontroller.order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.common.MainUIController;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/22.
 */
public class OrderDetailViewController {

    private OrderViewController orderViewController;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label hotelNameLabel;

    public void setOrderViewController(OrderViewController orderViewController) {
        this.orderViewController = orderViewController;
    }

    public void showOrder(OrderVO order) {
        orderIDLabel.setText(order.orderID);
        hotelNameLabel.setText(order.hotelName);
    }

    @FXML
    private void clickBackButton() {
        orderViewController.showOrderList();
    }

}
