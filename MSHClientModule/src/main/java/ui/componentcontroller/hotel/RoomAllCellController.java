package ui.componentcontroller.hotel;

import blimpl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import component.commontextfield.CommonTextField;
import component.tinybutton.TinyButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.staff.RoomAllListViewController;
import util.ResultMessage;
import vo.HotelRoomVO;

import java.util.regex.Pattern;

/**
 * Created by SilverNarcissus on 2016/11/27.
 */
public class RoomAllCellController {
    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private CommonTextField priceTextField;

    @FXML
    private Label priceAlertLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private CommonTextField quantityTextField;

    @FXML
    private Label quantityAlertLabel;

    @FXML
    private TinyButton editButton;

    @FXML
    private TinyButton deleteButton;

    private HotelRoomVO hotelRoom;

    private RoomAllListViewController roomAllListViewController;

    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();

    private boolean editMode;

    public void setRoomAllListViewController(RoomAllListViewController roomAllListViewController) {
        this.roomAllListViewController = roomAllListViewController;
    }

    public void setRoom(HotelRoomVO hotelRoom) {
        this.hotelRoom = hotelRoom;

        roomTypeLabel.setText(hotelRoom.roomType.getName());
        setEditMode(false);
    }

    private void setEditMode(boolean editMode) {
        this.editMode = editMode;

        priceLabel.setText(editMode ? "¥" : "¥ " + String.format("%.2f", hotelRoom.price));
        priceTextField.setText(hotelRoom.price + "");
        priceTextField.setVisible(editMode);
        priceTextField.setManaged(editMode);

        quantityLabel.setText(editMode ? "间" : hotelRoom.totalQuantity + " 间");
        quantityTextField.setText(hotelRoom.totalQuantity + "");
        quantityTextField.setVisible(editMode);
        quantityTextField.setManaged(editMode);

        editButton.setText(editMode ? "保存" : "编辑");

        priceAlertLabel.setVisible(false);
        quantityAlertLabel.setVisible(false);
    }

    @FXML
    private void clickEditButton() {
        if (!editMode) {
            setEditMode(true);

        } else {
            Pattern pricePattern = Pattern.compile("^[0-9]+(.[0-9]?[0-9]?)?$");
            boolean isPrice = pricePattern.matcher(priceTextField.getText()).matches();

            Pattern quantityPattern = Pattern.compile("^[1-9][0-9]*$");
            boolean isQuantity = quantityPattern.matcher(quantityTextField.getText()).matches();

            priceAlertLabel.setVisible(!isPrice);
            quantityAlertLabel.setVisible(!isQuantity);


            if (isPrice && isQuantity) {
                hotelRoom.price = Double.parseDouble(priceTextField.getText());
                hotelRoom.totalQuantity = Integer.parseInt(quantityTextField.getText());

                ResultMessage rm = hotelBLService.updateHotelRoom(hotelRoom);

                System.out.println(rm);
                if (rm == ResultMessage.SUCCESS) {
                    setEditMode(false);
                }
            }
        }
    }

    @FXML
    private void clickDeleteRoom() {

        ResultMessage resultMessage = hotelBLService.deleteHotelRoom(hotelRoom.hotelID, hotelRoom.roomType);

        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            roomAllListViewController.showAllRoomList();
        }
//        else {
//            System.out.println(resultMessage);
//        }
    }

}
