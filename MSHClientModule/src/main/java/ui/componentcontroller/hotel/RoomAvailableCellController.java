package ui.componentcontroller.hotel;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import component.circlebutton.CircleButton;
import component.statebutton.StateButton;
import component.tinybutton.TinyButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.staff.RoomAvailableListViewController;
import util.DateUtil;
import util.ResultMessage;
import vo.HotelRoomVO;
import vo.RoomChangeInfoVO;
import vo.RoomStockVO;

import java.text.DecimalFormat;

/**
 * Created by SilverNarcissus on 2016/11/29.
 */
public class RoomAvailableCellController {
    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label totalQuantityLabel;

    @FXML
    private Label availableQuantityLabel;

    @FXML
    private CircleButton minusButton;

    @FXML
    private CircleButton plusButton;

    @FXML
    private TinyButton editButton;

    private HotelRoomVO hotelRoom;

    private RoomStockVO roomStock;

    private RoomAvailableListViewController roomAvailableListViewController;

    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();

    private DateUtil date;
    private int availableQuantity;
    private boolean editMode;

    public void setRoomAvailableListViewController(RoomAvailableListViewController roomAvailableListViewController) {
        this.roomAvailableListViewController = roomAvailableListViewController;
    }

    public void setRoom(HotelRoomVO hotelRoom, DateUtil date) {
        this.hotelRoom = hotelRoom;
        this.date = date;

        roomTypeLabel.setText(hotelRoom.roomType.getName());
        priceLabel.setText("¥ " + String.format("%.2f", hotelRoom.price));

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
            availableQuantity = roomStock.availableQuantity;
            availableQuantityLabel.setText(availableQuantity + "");
        }

        setEditMode(false);
    }

    @FXML
    private void clickMinusButton() {
        availableQuantity--;
        refreshQuantity();
    }

    @FXML
    private void clickPlusButton() {
        availableQuantity++;
        refreshQuantity();
    }

    private void refreshQuantity() {
        minusButton.setAbled(availableQuantity > 1);
        plusButton.setAbled(availableQuantity < hotelRoom.totalQuantity);

        availableQuantityLabel.setText(availableQuantity + "");
    }

    private void setEditMode(boolean editMode) {
        this.editMode = editMode;

        minusButton.setVisible(editMode);
        plusButton.setVisible(editMode);

        editButton.setText(editMode ? "保存" : "编辑");
    }

    @FXML
    public void clickEditButton() {
        if (!editMode) {
            setEditMode(true);

        } else {
            RoomChangeInfoVO roomChangeInfo = new RoomChangeInfoVO(date, date, hotelRoom.hotelID, hotelRoom.roomType, roomStock.availableQuantity - availableQuantity);
            ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfo);

            if (resultMessage.equals(ResultMessage.SUCCESS)) {
//            roomAvailableListViewController.showAvailableRoomList();
                setEditMode(false);
            } else {
                System.out.println(resultMessage);
            }
        }

    }
}


