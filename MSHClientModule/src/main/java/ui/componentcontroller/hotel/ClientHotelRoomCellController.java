package ui.componentcontroller.hotel;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientHotelDetailViewController;
import vo.OrderRoomStockVO;
import vo.RoomStockVO;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientHotelRoomCellController {

    @FXML
    private Label typeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private RectButton addButton;

    private ClientHotelDetailViewController clientHotelDetailViewController;

    public void setClientHotelDetailViewController(ClientHotelDetailViewController clientHotelDetailViewController) {
        this.clientHotelDetailViewController = clientHotelDetailViewController;
    }

    public void setRoom(OrderRoomStockVO room) {
        typeLabel.setText(room.type.getName());
        priceLabel.setText("¥ " + room.price);
        quantityLabel.setText("剩余 " + room.availableQuantity + " 间");
        addButton.setIsAbledProperty(room.availableQuantity > 0);
    }
}
