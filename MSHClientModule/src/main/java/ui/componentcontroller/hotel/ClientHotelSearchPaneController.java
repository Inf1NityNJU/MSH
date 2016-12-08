package ui.componentcontroller.hotel;

import com.sun.xml.internal.bind.v2.TODO;
import component.commontextfield.CommonTextField;
import component.mycheckbox.MyCheckBox;
import component.mychoicebox.MyChoiceBox;
import component.mydatepicker.MyDatePicker;
import component.myrangeslider.MyRangeSlider;
import component.radioboxpane.RadioBoxPane;
import component.selectpane.SelectPane;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import ui.viewcontroller.client.ClientHotelListViewController;
import ui.viewcontroller.client.ClientOrderListViewController;
import util.City;
import util.DateUtil;
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

    @FXML
    private SelectPane citySelect;

    @FXML
    private SelectPane placeSelect;


    public void setClientHotelListViewController(ClientHotelListViewController clientHotelListViewController) {
        this.clientHotelListViewController = clientHotelListViewController;
    }

    @FXML
    public void initialize() {
        checkInDatePicker.setDate(LocalDate.now());
        checkOutDatePicker.setDate(LocalDate.now().plusDays(1));
    }

    @FXML
    public void searchHotel() {
        //city
        String cityName = citySelect.getText();
        City city=City.getCityByName(cityName);
        //place
        String placeName = citySelect.getText();
        Place place=Place.getPlaceByName(placeName);
        //name
        String name=keywordTextField.getText();
        //roomType
        String roomTypeName = roomTypePane.getText();
        RoomType roomType = RoomType.getRoomTypeByName(roomTypeName);
        //price
        double minPrice=roomPriceSlider.getMinValue();
        double maxPrice=roomPriceSlider.getMaxValue();
        //date
        DateUtil start=new DateUtil(checkInDatePicker.getDate());
        DateUtil end=new DateUtil(checkOutDatePicker.getDate());
        //quantity
        String quantityName=roomQuantitySelect.getText();
        int quantity=Integer.parseInt(quantityName.substring(0,1));
        //star
        String starName = starPane.getText();
        int star=-1;
        if (starName!=null){
            switch (starName){
                case "一星":
                    star=1;
                    break;
                case "二星":
                    star=2;
                    break;
                case "三星":
                    star=3;
                    break;
                case "四星":
                    star=4;
                    break;
                case "五星":
                    star=5;
                    break;
                default:
                    System.err.println("Wrong in starPane!");
            }
        }
        //score
        int minScore=(Integer) minScoreBox.getSelectionModel().getSelectedItem();
        int maxScore=(Integer) minScoreBox.getSelectionModel().getSelectedItem();
        //ordered

        // TODO: 2016/12/9    client ID
        FilterFlagsVO flags = new FilterFlagsVO(city, place, name, roomType, minPrice, maxPrice, start, end, quantity,star, minScore, maxScore, null);
        clientHotelListViewController.showHotel(flags);
    }
}
