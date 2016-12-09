package ui.componentcontroller.order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientOrderDetailViewController;
import vo.OrderRoomVO;

import java.text.DecimalFormat;

/**
 * Created by Sorumi on 16/12/3.
 */
public class OrderRoomCellController {

    @FXML
    private Label typeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label totalLabel;

    private ClientOrderDetailViewController clientOrderDetailViewController;

    public void setRoom(OrderRoomVO room) {
        DecimalFormat df = new DecimalFormat("#.0");

        typeLabel.setText(room.type.getName());
        priceLabel.setText("¥ " + room.price);
        quantityLabel.setText(room.quantity + " 间");
        totalLabel.setText("¥ " + df.format(room.price * room.quantity));
    }
}
