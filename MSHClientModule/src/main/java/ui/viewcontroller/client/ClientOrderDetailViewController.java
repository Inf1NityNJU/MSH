package ui.viewcontroller.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/22.
 */
public class ClientOrderDetailViewController {

    private ClientOrderViewController clientOrderViewController;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label hotelNameLabel;

    public void setClientViewController(ClientOrderViewController clientOrderViewController) {
        this.clientOrderViewController = clientOrderViewController;
    }

    public void showOrder(OrderVO order) {
        //TODO
        orderIDLabel.setText(order.orderID);
        hotelNameLabel.setText(order.hotelName);
    }

    @FXML
    private void clickBackButton() {
        clientOrderViewController.back();
    }

}
