package ui.componentcontroller.hotel;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientHotelDetailViewController;
import ui.viewcontroller.staff.HotelDetailViewController;
import vo.OrderRoomStockVO;

/**
 * Created by Sorumi on 16/12/2.
 */
public class StaffHotelRoomCellController {

    @FXML
    private Label typeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label availableQuantityLabel;

    @FXML
    private Label totalQuantityLabel;

    private HotelDetailViewController hotelDetailViewController;

    private OrderRoomStockVO orderRoomStock;

    public void setHotelDetailViewController(HotelDetailViewController hotelDetailViewController) {
        this.hotelDetailViewController = hotelDetailViewController;
    }

    public void setRoom(OrderRoomStockVO room) {
        this.orderRoomStock = room;

        typeLabel.setText(room.orderRoom.type.getName());
        priceLabel.setText("¥ " + room.orderRoom.price);
        availableQuantityLabel.setText(room.availableQuantity + " 间");
//        totalQuantityLabel.setText(room.availableQuantity + " 间");
    }

}
