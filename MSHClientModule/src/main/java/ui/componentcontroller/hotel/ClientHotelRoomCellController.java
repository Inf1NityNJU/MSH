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

    private OrderRoomStockVO orderRoomStock;

    public void setClientHotelDetailViewController(ClientHotelDetailViewController clientHotelDetailViewController) {
        this.clientHotelDetailViewController = clientHotelDetailViewController;
    }

    public void setRoom(OrderRoomStockVO room) {
        this.orderRoomStock = room;

        typeLabel.setText(room.orderRoom.type.getName());
        priceLabel.setText("¥ " + room.orderRoom.price);
        quantityLabel.setText("剩余 " + room.availableQuantity + " 间");
        addButton.setIsAbledProperty(room.availableQuantity > 0);
    }

    @FXML
    private void clickAddButton() {
        orderRoomStock.orderRoom.quantity++;
        if (orderRoomStock.orderRoom.quantity >= orderRoomStock.availableQuantity) {
            addButton.setIsAbledProperty(false);
        }
        clientHotelDetailViewController.addRoomInOrder(orderRoomStock);
    }
}
