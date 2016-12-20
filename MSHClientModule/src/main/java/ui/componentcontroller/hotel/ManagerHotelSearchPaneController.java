package ui.componentcontroller.hotel;

import component.mychoicebox.MyChoiceBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import ui.viewcontroller.manager.HotelManagementListViewController;
import util.City;
import util.Place;
import vo.FilterFlagsVO;

import java.util.ArrayList;

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
        ObservableList<String> cities = FXCollections.observableArrayList(City.getNames(City.values()));
        cities.add(0, "全部");
        cityChoiceBox.setItems(cities);
        cityChoiceBox.getSelectionModel().selectFirst();
        ObservableList<String> places = FXCollections.observableArrayList("全部");
        placeChoiceBox.setItems(places);
        placeChoiceBox.getSelectionModel().selectFirst();

        cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        String cityName = (String)newValue;
                        if (cityName.equals("全部")) {
                            ObservableList<String> places = FXCollections.observableArrayList("全部");
                            placeChoiceBox.setItems(places);
                        } else {
                            City city = City.getCityByName(cityName);
                            ObservableList<String> places = FXCollections.observableArrayList(Place.getNames(city.getPlaces()));
                            places.add(0, "全部");
                            placeChoiceBox.setItems(places);

                        }
                        placeChoiceBox.getSelectionModel().selectFirst();
                    }
                }
        );

    }

    public void setHotelManagementListViewController(HotelManagementListViewController hotelManagementListViewController) {
        this.hotelManagementListViewController = hotelManagementListViewController;
    }

    @FXML
    public void clickSearchButton() {
        searchHotels();
    }

    @FXML
    public void clickAddButton() {
        hotelManagementListViewController.showAddHotel();
    }


    private void searchHotels() {
        FilterFlagsVO flags;

        String cityName = (String)cityChoiceBox.getSelectionModel().getSelectedItem();
        if (cityName.equals("全部")) {
            flags = new FilterFlagsVO(null, null, "", null, 0, 0, null, null, 0, -1, 0, 0, null);

        } else {
            City city = City.getCityByName(cityName);
            String placeName = (String)placeChoiceBox.getSelectionModel().getSelectedItem();

            if (placeName.equals("全部")) {
                flags = new FilterFlagsVO(city, null, "", null, 0, 0, null, null, 0, -1, 0, 0, null);

            } else {
                Place place = Place.getPlaceByName(placeName);
                flags = new FilterFlagsVO(city, place, "", null, 0, 0, null, null, 0, -1, 0, 0, null);

            }
        }

        hotelManagementListViewController.showHotels(flags);
    }
}
