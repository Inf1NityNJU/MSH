package ui.componentcontroller.hotel;

import bl.hotelbl.Hotel;
import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
//import ui.viewcontroller.staff.DataClass;
import ui.viewcontroller.staff.EditRoomInfoListViewController;
import ui.viewcontroller.staff.RoomInfoListViewController;
import util.DateUtil;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;
import vo.RoomChangeInfoVO;
import vo.RoomStockVO;

/**
 * Created by SilverNarcissus on 2016/11/29.
 */
public class EditRoomInfoCellController {
    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;


    @FXML
    private StateButton saveButton;


    @FXML
    private Label minusLabel;

    @FXML
    private Label addLabel;

    @FXML
    private Label availableQuantityLabel;


    private HotelRoomVO hotelRoomVO;

    private RoomStockVO roomStockVO;

    //上层组件
    private EditRoomInfoListViewController editRoomInfoListViewController;

    public void setRoomInfoListViewController(EditRoomInfoListViewController editRoomInfoListViewController) {
        this.editRoomInfoListViewController = editRoomInfoListViewController;
    }

    public void setRoom(HotelRoomVO hotelRoomVO, DateUtil dateUtil) {
        this.hotelRoomVO = hotelRoomVO;
        roomTypeLabel.setText(hotelRoomVO.roomType.toString());
        priceLabel.setText("¥ " + String.format("%.2f", hotelRoomVO.price));
        quantityLabel.setText(String.valueOf(hotelRoomVO.totalQuantity + " 间"));
        //显示可用数量
        for (RoomStockVO rVO : hotelRoomVO.roomStockVOs) {
            if (rVO.date.equals(dateUtil)) {
                roomStockVO = rVO;
                break;
            }
        }
        //检测空对象
        if (roomStockVO == null) {
            System.out.println("Can't find roomStockVO");
        }
        //
        availableQuantityLabel.setText(String.valueOf(roomStockVO.availableQuantity) + " 间");
    }

    @FXML
    public void minusRoomNumber() {
        availableQuantityLabel.setText(String.valueOf(Integer.parseInt(availableQuantityLabel.getText().split(" ")[0]) - 1)+" 间");
    }

    @FXML
    public void addRoomNumber() {
        availableQuantityLabel.setText(String.valueOf(Integer.parseInt(availableQuantityLabel.getText().split(" ")[0]) + 1)+" 间");
    }

    @FXML
    public void save() {
        HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();
        ResultMessage resultMessage=hotelBLService.updateHotelRoomQuantity(new RoomChangeInfoVO(editRoomInfoListViewController.getDate()
                , editRoomInfoListViewController.getDate()
                , hotelRoomVO.hotelID
                , hotelRoomVO.roomType
                , roomStockVO.availableQuantity - Integer.parseInt(availableQuantityLabel.getText().split(" ")[0])));
        //如果成功，刷新列表
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            editRoomInfoListViewController.showEditRoomList();
        }
        //如果失败，打印信息
        else {
            System.out.println(resultMessage);
        }
    }
}


