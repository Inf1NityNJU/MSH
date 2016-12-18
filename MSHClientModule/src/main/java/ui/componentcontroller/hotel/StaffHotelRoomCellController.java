package ui.componentcontroller.hotel;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientHotelDetailViewController;
import ui.viewcontroller.staff.HotelDetailViewController;
import util.DateUtil;
import vo.HotelRoomVO;
import vo.OrderRoomStockVO;
import vo.RoomStockVO;

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

    private HotelRoomVO hotelRoom;
    private RoomStockVO roomStock;

    public void setHotelDetailViewController(HotelDetailViewController hotelDetailViewController) {
        this.hotelDetailViewController = hotelDetailViewController;
    }

    public void setRoom(HotelRoomVO hotelRoom, DateUtil date) {
        this.hotelRoom = hotelRoom;

        typeLabel.setText(hotelRoom.roomType.getName());
        priceLabel.setText("¥ " + hotelRoom.price);
        totalQuantityLabel.setText(hotelRoom.totalQuantity + " 间");

        for (RoomStockVO roomStock : hotelRoom.roomStockVOs) {
            if (roomStock.date.equals(date)) {
                this.roomStock = roomStock;
                break;
            }
        }

        if (roomStock == null) {
            System.out.println("Can't find roomStockVO");

        } else {
            int availableQuantity = roomStock.availableQuantity;
            availableQuantityLabel.setText(availableQuantity + "");
        }

    }

}
