package ui.viewcontroller.manager;

import bl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.radioboxpane.RadioBoxPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.componentcontroller.user.HotelStaffCellController;
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
public class HotelManagementAddViewController {

    @FXML
    private CommonTextField nameTextField;

    @FXML
    private MyChoiceBox cityBox;

    @FXML
    private MyChoiceBox placeBox;

    @FXML
    private CommonTextField addressTextField;

    @FXML
    private RadioBoxPane starPane;

    @FXML
    private Label staffLabel;

    @FXML
    private VBox staffVBox;

    @FXML
    private Label alertLabel;

    private HotelManagementViewController hotelManagementViewController;

    private UserBLService userBLService = new BLFactoryImpl().getStaffBLService();
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();

    private StaffVO staff;

    public void setHotelManagementViewController(HotelManagementViewController hotelManagementViewController) {
        this.hotelManagementViewController = hotelManagementViewController;
    }

    public void addHotel() {
        alertLabel.setText("");
        staffLabel.setText("");

        starPane.setValueIndex(0);

        cityBox.setItems(FXCollections.observableArrayList(City.getNames(City.values())));
        cityBox.getSelectionModel().selectFirst();
        placeBox.setItems(FXCollections.observableArrayList(Place.getNames(City.values()[0].getPlaces())));
        placeBox.getSelectionModel().selectFirst();

        cityBox.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        City city = City.getCityByName((String) newValue);
                        placeBox.setItems(FXCollections.observableArrayList(Place.getNames(city.getPlaces())));
                        placeBox.getSelectionModel().selectFirst();
                    }
                }
        );

        ArrayList<StaffVO> staffs = userBLService.search("");

        try {
            for (StaffVO staff : staffs) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/user/HotelStaffCell.fxml"));
                HBox cell = cellLoader.load();

                HotelStaffCellController hotelStaffCellController = cellLoader.getController();
                hotelStaffCellController.setHotelManagementAddViewController(this);
                hotelStaffCellController.setStaff(staff);

                staffVBox.getChildren().add(cell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStaff(StaffVO staff) {
        this.staff = staff;
        staffLabel.setText(staff.staffName + " (" + staff.staffID + ")");
    }

    @FXML
    public void clickBackButton() {
        hotelManagementViewController.back();
    }

    @FXML
    public void clickConfirmButton() {
        String name = nameTextField.getText();
        int star = starPane.getValueIndex() + 1;
        String address = addressTextField.getText();
        City city = City.getCityByName((String) cityBox.getSelectionModel().getSelectedItem());
        Place place = Place.getPlaceByName((String) placeBox.getSelectionModel().getSelectedItem());

        if (name.equals("")) {
            alertLabel.setText("请填写酒店名称");
            return;
        } else if (star == 0) {
            alertLabel.setText("请选择酒店星级");
            return;
        } else if (address.equals("")) {
            alertLabel.setText("请填写酒店地址");
            return;
        } else if (staff == null) {
            alertLabel.setText("请选择酒店工作人员");
            return;
        }

        Hotel_DetailVO hotel = new Hotel_DetailVO("", name, city, address, place, star, "", "", null, 0, 0);
        ResultMessage rm = hotelBLService.addHotel(hotel);
        System.out.println(rm);

        if (rm == ResultMessage.SUCCESS) {
//            staff.hotelID =
            hotelManagementViewController.back();
            hotelManagementViewController.showHotelList();
        }
    }
}
