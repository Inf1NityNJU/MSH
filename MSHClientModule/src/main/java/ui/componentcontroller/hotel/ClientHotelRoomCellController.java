package ui.componentcontroller.hotel;

import component.circleimage.CircleImage;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import ui.viewcontroller.client.ClientHotelDetailViewController;
import vo.OrderRoomStockVO;
import vo.RoomStockVO;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientHotelRoomCellController {

    @FXML
    private CircleImage imageView;

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

        imageView.setImage(new Image(getClass().getResource("/images/room/" + room.orderRoom.type.toString() + ".png").toExternalForm()));
        imageView.setRadius(40);

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
