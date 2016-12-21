package ui.viewcontroller.manager;

import bl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.radioboxpane.RadioBoxPane;
import component.starlabel.StarLabel;
import component.statebutton.StateButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.componentcontroller.user.HotelStaffCellController;
import ui.viewcontroller.common.MainUIController;
import util.City;
import util.Place;
import util.ResultMessage;
import vo.Hotel_DetailVO;
import vo.StaffVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/12/20.
 */
public class HotelManagementDetailViewController {

    @FXML
    private Label nameLabel;

    @FXML
    private StarLabel starLabel;

    @FXML
    private StateButton cityStateLabel;

    @FXML
    private StateButton placeStateLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label staffLabel;

    @FXML
    private VBox staffVBox;

    @FXML
    private Label alertLabel;

    private HotelManagementViewController hotelManagementViewController;

    private Hotel_DetailVO hotel;

    private UserBLService userBLService = new BLFactoryImpl().getStaffBLService();
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();

    private StaffVO staff;

    private MainUIController mainUIController;

    public void setHotelManagementViewController(HotelManagementViewController hotelManagementViewController) {
        this.hotelManagementViewController = hotelManagementViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void setHotel(Hotel_DetailVO hotel) {
        this.hotel = hotel;
        alertLabel.setText("");


        nameLabel.setText(hotel.name);
        starLabel.setStar(hotel.star);
        cityStateLabel.setText(hotel.city.getName());
        placeStateLabel.setText(hotel.place.getName());
        addressLabel.setText(hotel.address);

        StaffVO staffVO = userBLInfo.getStaffByHotelID(hotel.ID);
        if (staffVO != null) {
            staffLabel.setText(staffVO.staffName);
        } else {
            staffLabel.setText("无");
        }


        ArrayList<StaffVO> staffs = userBLService.search("");

        try {
            for (StaffVO staff : staffs) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/user/HotelStaffCell.fxml"));
                HBox cell = cellLoader.load();

                HotelStaffCellController hotelStaffCellController = cellLoader.getController();
                hotelStaffCellController.setHotelManagementDetailViewController(this);
                hotelStaffCellController.setStaff(staff);

                staffVBox.getChildren().add(cell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStaff(StaffVO staff) {
        this.staff = staff;
        staff.hotelID = hotel.ID;
        userBLService.update(staff);
        staffLabel.setText(staff.staffName + " (" + staff.staffID + ")");

    }

    @FXML
    public void clickBackButton() {
        hotelManagementViewController.back();
    }


}
