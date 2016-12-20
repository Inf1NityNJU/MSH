package ui.componentcontroller.user;

import bl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLInfo;
import component.circlebutton.CircleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.manager.HotelManagementAddViewController;
import vo.Hotel_DetailVO;
import vo.StaffVO;

/**
 * Created by Sorumi on 16/12/20.
 */
public class HotelStaffCellController {

    @FXML
    private Label IDLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private CircleButton addButton;

    private HotelBLInfo hotelBLInfo = new BLFactoryImpl().getHotelBLInfo();
    private HotelManagementAddViewController hotelManagementAddViewController;

    private StaffVO staff;

    public void setHotelManagementAddViewController(HotelManagementAddViewController hotelManagementAddViewController) {
        this.hotelManagementAddViewController = hotelManagementAddViewController;
    }

    public void setStaff(StaffVO staff) {
        this.staff = staff;

        IDLabel.setText(staff.staffID);
        nameLabel.setText(staff.staffName);

        if (staff.hotelID != null) {
            Hotel_DetailVO hotel = hotelBLInfo.getHotel(staff.hotelID);

            if (hotel != null) {
                hotelNameLabel.setText(hotel.name);
                addButton.setAbled(false);
            } else {
                hotelNameLabel.setText("/");
            }

        } else {
            hotelNameLabel.setText("/");
        }

    }

    @FXML
    public void clickAddButton() {
        hotelManagementAddViewController.addStaff(staff);
    }
}
