package ui.componentcontroller.hotel;

import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.staff.RoomInfoListViewController;
import vo.HotelRoomVO;

/**
 * Created by SilverNarcissus on 2016/11/27.
 */
public class RoomInfoCellController {
    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;


    @FXML
    private StateButton editButton;


    @FXML
    private Label deleteButton;


    private RoomInfoListViewController roomInfoListViewController;

    public void setRoomInfoListViewController(RoomInfoListViewController roomInfoListViewController) {
        this.roomInfoListViewController = roomInfoListViewController;
    }

    public void setRoom(HotelRoomVO hotelRoomVO) {
        roomTypeLabel.setText(hotelRoomVO.roomType.toString());
        priceLabel.setText("¥ " + String.format("%.2f", hotelRoomVO.price));
        quantityLabel.setText(String.valueOf(hotelRoomVO.totalQuantity+" 间"));
    }
}
