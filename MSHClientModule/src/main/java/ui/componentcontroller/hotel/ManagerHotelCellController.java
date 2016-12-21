package ui.componentcontroller.hotel;

import blimpl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLInfo;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.manager.HotelManagementListViewController;
import vo.*;

/**
 * Created by Sorumi on 16/11/18.
 */
public class ManagerHotelCellController {

    private HotelManagementListViewController hotelManagementListViewController;
    private Hotel_DetailVO hotel;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label hotelIDLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label staffLabel;

    @FXML
    private StateButton cityLabel;

    @FXML
    private StateButton placeLabel;

    @FXML
    private RectButton detailButton;

    public void setHotelManagementListViewController(HotelManagementListViewController hotelManagementListViewController) {
        this.hotelManagementListViewController = hotelManagementListViewController;
    }

    public void setHotel(Hotel_DetailVO hotel) {
        this.hotel = hotel;

        hotelNameLabel.setText(hotel.name);
        hotelIDLabel.setText(hotel.ID);
        addressLabel.setText(hotel.address);
        cityLabel.setText(hotel.city.getName());
        placeLabel.setText(hotel.place.getName());
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
        StaffVO staff = userBLInfo.getStaffByHotelID(hotel.ID);
        if (staff != null) {
            staffLabel.setText(staff.staffName + " " + staff.staffID);
        } else {
            staffLabel.setText("无");
        }

    }

    @FXML
    private void clickDetailButton() {
        hotelManagementListViewController.showHotelDetail(hotel);
    }


}
