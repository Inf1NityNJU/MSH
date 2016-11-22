package ui.viewcontroller.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/22.
 */
public class ClientOrderDetailViewController {

    private ClientViewController clientViewController;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label hotelNameLabel;

    public void setClientViewController(ClientViewController clientViewController) {
        this.clientViewController = clientViewController;
    }

    public void showOrder(OrderVO order) {
        orderIDLabel.setText(order.orderID);
        hotelNameLabel.setText(order.hotelName);
    }

    @FXML
    private void clickBackButton() {
        clientViewController.back();
    }

}
