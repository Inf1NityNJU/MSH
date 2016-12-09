package ui.componentcontroller.hotel;

import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import component.mycheckbox.MyCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.viewcontroller.staff.RoomInfoViewController;
import util.RoomType;
import vo.HotelRoomVO;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 2016/11/29.
 */
public class AddHotelRoomPaneController {
    //上层组件
    private RoomInfoViewController roomInfoViewController;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private MyCheckBox singleRoomCheckBox;

    @FXML
    private MyCheckBox doubleRoomCheckBox;

    @FXML
    private MyCheckBox doubleDoubleCheckBox;

    @FXML
    private MyCheckBox familyRoomCheckBox;

    @FXML
    private MyCheckBox economicRoomCheckBox;

    /**
     * CheckBox组
     */
    private ArrayList<MyCheckBox> checkBoxes = new ArrayList<MyCheckBox>();

    /**
     * 房间类型组
     */
    private ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();

    @FXML
    public void initialize() {
        //表驱动
        //添加复选框表
        checkBoxes.add(singleRoomCheckBox);
        checkBoxes.add(doubleRoomCheckBox);
        checkBoxes.add(doubleDoubleCheckBox);
        checkBoxes.add(familyRoomCheckBox);
        checkBoxes.add(economicRoomCheckBox);
        //添加房间类型表
        roomTypes.add(RoomType.SingleRoom);
        roomTypes.add(RoomType.DoubleRoom);
        roomTypes.add(RoomType.DoubleDouble);
        roomTypes.add(RoomType.FamilyRoom);
        roomTypes.add(RoomType.EconomicRoom);
    }

    public void setRoomInfoViewController(RoomInfoViewController roomInfoViewController) {
        this.roomInfoViewController = roomInfoViewController;
    }

    /**
     * 取消添加，返回上一层
     */
    public void cancel() {
        roomInfoViewController.back();
    }

    public void save() {
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).getIsActiveProperty()) {
                HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();
                hotelBLService.addRoom(new HotelRoomVO("00000000"
                        , roomTypes.get(i)
                        , Double.parseDouble(priceField.getText())
                        , Integer.parseInt(quantityField.getText())
                        , null));
                roomInfoViewController.back();
                return;
            }
        }
    }

    /**
     * 清空上一次留下的痕迹
     */
    public void clean() {
        priceField.setText("");
        quantityField.setText("");
        for (MyCheckBox myCheckBox : checkBoxes) {
            myCheckBox.setIsActiveProperty(false);
        }
    }
}
