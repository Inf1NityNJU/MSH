package ui.componentcontroller.hotel;

import bl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mycheckbox.MyCheckBox;
import component.mychoicebox.MyChoiceBox;
import component.mydatepicker.MyDatePicker;
import component.myrangeslider.MyRangeSlider;
import component.radioboxpane.RadioBoxPane;
import component.selectpane.SelectPane;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientHotelListViewController;

import util.DateUtil;
import util.City;
import util.Place;
import util.RoomType;
import vo.FilterFlagsVO;

import java.time.LocalDate;

/**
 * Created by Sorumi on 16/11/25.
 */
public class ClientHotelSearchPaneController {

    private ClientHotelListViewController clientHotelListViewController;

    @FXML
    private SelectPane citySelect;

    @FXML
    private SelectPane placeSelect;

    @FXML
    private MyDatePicker checkInDatePicker;

    @FXML
    private MyDatePicker checkOutDatePicker;

    @FXML
    private RadioBoxPane roomTypePane;

    @FXML
    private RadioBoxPane starPane;

    @FXML
    private CommonTextField keywordTextField;

    @FXML
    private SelectPane roomQuantitySelect;

    @FXML
    private MyRangeSlider roomPriceSlider;

    @FXML
    private MyChoiceBox minScoreBox;

    @FXML
    private MyChoiceBox maxScoreBox;

    @FXML
    private MyCheckBox orderedBox;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();


    public void setClientHotelListViewController(ClientHotelListViewController clientHotelListViewController) {
        this.clientHotelListViewController = clientHotelListViewController;
    }

    @FXML
    public void initialize() {
        checkInDatePicker.setDate(LocalDate.now());
        checkOutDatePicker.setDate(LocalDate.now().plusDays(1));

        citySelect.getLabels().clear();
        for (City city : City.values()) {
            Label label = new Label(city.getName());
            citySelect.getLabels().add(label);
        }
        citySelect.setOnValueChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                placeSelect.getLabels().clear();
                City city = City.getCityByName(citySelect.getText());
                for (Place place : city.getPlaces()) {
                    Label label = new Label(place.getName());
                    placeSelect.getLabels().add(label);
                }
            }
        });
    }

    @FXML
    public void searchHotel() {
        //city
        String cityName = citySelect.getText();
        City city = City.getCityByName(cityName);
        //place
        String placeName = placeSelect.getText();
        Place place = Place.getPlaceByName(placeName);
        //name
        String name = keywordTextField.getText();
        //roomType
        String roomTypeName = roomTypePane.getText();
        RoomType roomType = RoomType.getRoomTypeByName(roomTypeName);
        //price
        double minPrice = roomPriceSlider.getMinValue();
        double maxPrice = roomPriceSlider.getMaxValue();
        //date
        DateUtil start = new DateUtil(checkInDatePicker.getDate());
        DateUtil end = new DateUtil(checkOutDatePicker.getDate());
        //quantity
        String quantityName = roomQuantitySelect.getText();
        int quantity=0;
        if(quantityName.charAt(0)!='房'){
           quantity = Integer.parseInt(quantityName.substring(0, 1));
        }
        //star
        String starName = starPane.getText();
        int star = -1;
        if (starName != null) {
            switch (starName) {
                case "一星":
                    star = 1;
                    break;
                case "二星":
                    star = 2;
                    break;
                case "三星":
                    star = 3;
                    break;
                case "四星":
                    star = 4;
                    break;
                case "五星":
                    star = 5;
                    break;
                default:
            }
        }
        //score
        int minScore=0;
        int maxScore=0;
        if(minScoreBox.getSelectionModel().getSelectedItem()!=null) {
            minScore = (Integer) minScoreBox.getSelectionModel().getSelectedItem();
        }
        if(minScoreBox.getSelectionModel().getSelectedItem()!=null) {
            maxScore = (Integer) maxScoreBox.getSelectionModel().getSelectedItem();
        }
        //ordered
        String clientID=null;
        if(orderedBox.getIsActiveProperty()){
              clientID=userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID());
        }

        FilterFlagsVO flags = new FilterFlagsVO(city, place, name, roomType, minPrice, maxPrice, start, end, quantity, star, minScore, maxScore, clientID);
        System.out.println(flags);
        clientHotelListViewController.showHotel(flags);
    }
}
