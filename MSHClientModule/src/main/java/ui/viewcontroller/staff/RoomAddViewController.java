package ui.viewcontroller.staff;

import blimpl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mycheckbox.MyCheckBox;
import component.radioboxpane.RadioBoxPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by SilverNarcissus on 2016/11/29.
 */
public class RoomAddViewController {

    private RoomInfoViewController roomInfoViewController;

    private MainUIController mainUIController;
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

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
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
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/component/common/AlertView.fxml"));
                AnchorPane pane = loader.load();

                AlertViewController alertViewController = loader.getController();

                alertViewController.setInfoLabel("确定保存新的房间信息吗？");
                alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        sureSave();
                    }
                });
                alertViewController.setOnClickCancelButton(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        cancelSave();
                    }
                });
                mainUIController.showPop(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void sureSave() {
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
        mainUIController.hidePop();
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }
}
