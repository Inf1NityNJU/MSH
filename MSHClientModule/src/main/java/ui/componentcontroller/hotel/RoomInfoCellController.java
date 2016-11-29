package ui.componentcontroller.hotel;

import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.staff.RoomInfoListViewController;
import util.ResultMessage;
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

    private HotelRoomVO hotelRoomVO;


    private RoomInfoListViewController roomInfoListViewController;

    public void setRoomInfoListViewController(RoomInfoListViewController roomInfoListViewController) {
        this.roomInfoListViewController = roomInfoListViewController;
    }

    public void setRoom(HotelRoomVO hotelRoomVO) {
        this.hotelRoomVO=hotelRoomVO;
        roomTypeLabel.setText(hotelRoomVO.roomType.toString());
        priceLabel.setText("¥ " + String.format("%.2f", hotelRoomVO.price));
        quantityLabel.setText(String.valueOf(hotelRoomVO.totalQuantity + " 间"));
    }

    @FXML
    public void deleteRoom() {
        HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();
        ResultMessage resultMessage=hotelBLService.deleteHotelRoom(hotelRoomVO.hotelID, hotelRoomVO.roomType);
        //如果成功，刷新列表
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            roomInfoListViewController.showAllRoomList();
        }
        //失败则打印失败信息
        else {
            System.out.println(resultMessage);
        }
    }
}
