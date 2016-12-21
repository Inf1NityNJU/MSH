package ui.viewcontroller.manager;

import blimpl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
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
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.City;
import util.Place;
import util.ResultMessage;
import vo.Hotel_DetailVO;

import java.io.IOException;

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
    private Label alertLabel;

    private HotelManagementViewController hotelManagementViewController;

    private UserBLService userBLService = new BLFactoryImpl().getStaffBLService();
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();

    private MainUIController mainUIController;

    public void setHotelManagementViewController(HotelManagementViewController hotelManagementViewController) {
        this.hotelManagementViewController = hotelManagementViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void addHotel() {
        alertLabel.setText("");

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
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();

            alertViewController.setInfoLabel("确定增加该酒店吗？");
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    sureSave(name, city, address, place, star);
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

    private void sureSave(String name, City city, String address, Place place, int star) {
        Hotel_DetailVO hotel = new Hotel_DetailVO("", name, city, address, place, star, "", "", null, 0, 0);
        ResultMessage rm = hotelBLService.addHotel(hotel);

        if (rm == ResultMessage.SUCCESS) {
            hotelManagementViewController.back();
            hotelManagementViewController.showHotelList();
        }
        mainUIController.hidePop();
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }
}
