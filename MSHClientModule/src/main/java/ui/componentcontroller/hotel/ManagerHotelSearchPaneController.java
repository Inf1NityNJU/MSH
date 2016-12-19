package ui.componentcontroller.hotel;

import component.mychoicebox.MyChoiceBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import ui.viewcontroller.manager.HotelManagementListViewController;
import util.City;
import util.Place;
import vo.FilterFlagsVO;

/**
 * Created by Sorumi on 16/12/19.
 */
public class ManagerHotelSearchPaneController {

    @FXML
    private MyChoiceBox cityChoiceBox;

    @FXML
    private MyChoiceBox placeChoiceBox;

    private HotelManagementListViewController hotelManagementListViewController;

    @FXML
    public void initialize() {
        cityChoiceBox.setItems(FXCollections.observableArrayList(City.getNames(City.values())));
        cityChoiceBox.getSelectionModel().selectFirst();
        placeChoiceBox.setItems(FXCollections.observableArrayList(Place.getNames(City.values()[0].getPlaces())));
        placeChoiceBox.getSelectionModel().selectFirst();

        cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        City city = City.getCityByName((String)newValue);
                        placeChoiceBox.setItems(FXCollections.observableArrayList(Place.getNames(city.getPlaces())));
                        placeChoiceBox.getSelectionModel().selectFirst();
                        searchHotels();
                    }
                }
        );

        placeChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                searchHotels();
            }
        });
    }

    public void setHotelManagementListViewController(HotelManagementListViewController hotelManagementListViewController) {
        this.hotelManagementListViewController = hotelManagementListViewController;
    }

    public void searchHotels() {
        String cityName = (String)cityChoiceBox.getSelectionModel().getSelectedItem();
        City city = City.getCityByName(cityName);
        String placeName = (String)placeChoiceBox.getSelectionModel().getSelectedItem();
        Place place = Place.getPlaceByName(placeName);
        FilterFlagsVO flags = new FilterFlagsVO(city, place, null, null, 0, 5000, null, null, 0, -1, 0, 0, null);

        hotelManagementListViewController.showHotels(flags);
    }
}
