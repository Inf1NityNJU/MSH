package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mycheckbox.MyCheckBox;
import component.radioboxpane.RadioBoxPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.viewcontroller.staff.RoomInfoViewController;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by SilverNarcissus on 2016/11/29.
 */
public class RoomAddViewController {

    private RoomInfoViewController roomInfoViewController;

    @FXML
    private CommonTextField priceTextField;

    @FXML
    private CommonTextField quantityTextField;

    @FXML
    private RadioBoxPane typeBoxPane;

    @FXML
    private Label priceAlertLabel;

    @FXML
    private Label quantityAlertLabel;


    @FXML
    public void initialize() {

        for (RoomType roomType : RoomType.values()) {
            MyCheckBox checkBox = new MyCheckBox();
            checkBox.setText(roomType.getName());
            typeBoxPane.getItems().add(checkBox);
        }
        typeBoxPane.setValueIndex(0);

        priceAlertLabel.setVisible(false);
        quantityAlertLabel.setVisible(false);
    }

    public void setRoomInfoViewController(RoomInfoViewController roomInfoViewController) {
        this.roomInfoViewController = roomInfoViewController;
    }


    @FXML
    public void clickCancelButton() {
        roomInfoViewController.back();
    }

    @FXML
    public void clickSaveButton() {
        Pattern pricePattern = Pattern.compile("^[0-9]+(.[0-9]?[0-9]?)?$");
        boolean isPrice = pricePattern.matcher(priceTextField.getText()).matches();

        Pattern quantityPattern = Pattern.compile("^[1-9][0-9]*$");
        boolean isQuantity = quantityPattern.matcher(quantityTextField.getText()).matches();

        priceAlertLabel.setVisible(!isPrice);
        quantityAlertLabel.setVisible(!isQuantity);

        if (isPrice && isQuantity) {
            HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
            UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
            String staffID = userBLInfo.getCurrentStaffID();
            String hotelID = userBLInfo.getHotelIDByStaffID(staffID);

            String roomTypeName = typeBoxPane.getText();
            RoomType roomType = RoomType.getRoomTypeByName(roomTypeName);
            double price = Double.parseDouble(priceTextField.getText());
            int quantity = Integer.parseInt(quantityTextField.getText());

            HotelRoomVO hotelRoom = new HotelRoomVO(hotelID, roomType, price, quantity, null);


            ResultMessage rm = hotelBLService.addRoom(hotelRoom);

            System.out.println(rm);
            if (rm == ResultMessage.SUCCESS) {
                roomInfoViewController.back();
                roomInfoViewController.showRoomAllList();
            }
        }
    }
}
