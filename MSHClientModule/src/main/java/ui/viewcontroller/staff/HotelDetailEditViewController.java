package ui.viewcontroller.staff;

import blimpl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextarea.CommonTextArea;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.radioboxpane.RadioBoxPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.City;
import util.Place;
import util.ResultMessage;
import vo.Hotel_DetailVO;

import java.io.IOException;

/**
 * Created by Sorumi on 16/12/17.
 */
public class HotelDetailEditViewController {

    @FXML
    private Label hotelIDLabel;

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
    private CommonTextArea introTextArea;

    @FXML
    private CommonTextArea facilityTextArea;

    private HotelInfoViewController hotelInfoViewController;
    private MainUIController mainUIController;

    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();


    private Hotel_DetailVO hotel;

    public void setHotelInfoViewController(HotelInfoViewController hotelInfoViewController) {
        this.hotelInfoViewController = hotelInfoViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void showHotelDetailEdit() {
        String staffID = userBLInfo.getCurrentStaffID();
        String hotelID = userBLInfo.getHotelIDByStaffID(staffID);

        hotel = hotelBLService.getHotel(hotelID);

        hotelIDLabel.setText(hotelID);
        nameTextField.setText(hotel.name);
        addressTextField.setText(hotel.address);
        introTextArea.setText(hotel.introduction);
        facilityTextArea.setText(hotel.facilities);

        starPane.setValueIndex(hotel.star-1);

        cityBox.setItems(FXCollections.observableArrayList(City.getNames(City.values())));
        cityBox.getSelectionModel().select(hotel.city.getName());
        placeBox.setItems(FXCollections.observableArrayList(Place.getNames(hotel.city.getPlaces())));
        placeBox.getSelectionModel().select(hotel.place.getName());

        cityBox.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        City city = City.getCityByName((String)newValue);
                        placeBox.setItems(FXCollections.observableArrayList(Place.getNames(city.getPlaces())));
                        placeBox.getSelectionModel().selectFirst();
                    }
                }
        );
    }

    @FXML
    private void clickBackButton() {
        hotelInfoViewController.back();
    }

    @FXML
    private void clickConfirmButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();

            alertViewController.setInfoLabel("确定保存新的酒店信息吗？");
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

    private void sureSave() {
        hotel.name = nameTextField.getText();
        hotel.address = addressTextField.getText();
        hotel.introduction = introTextArea.getText();
        hotel.facilities = facilityTextArea.getText();

        hotel.star = starPane.getValueIndex() + 1;

        hotel.city = City.getCityByName((String)cityBox.getSelectionModel().getSelectedItem());
        hotel.place = Place.getPlaceByName((String)placeBox.getSelectionModel().getSelectedItem());

        ResultMessage rm = hotelBLService.updateHotel(hotel);

        if (rm == ResultMessage.SUCCESS) {
            hotelInfoViewController.back();
            hotelInfoViewController.showHotelDetail();
        }
        mainUIController.hidePop();
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }
}
