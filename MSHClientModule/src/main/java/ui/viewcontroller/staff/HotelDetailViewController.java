package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import blservice.orderblservice.OrderBLService;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import component.mydatepicker.MyDatePicker;
import component.ratestarpane.RateStarPane;
import component.rectbutton.RectButton;
import component.starlabel.StarLabel;
import component.statebutton.StateButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Main;
import ui.componentcontroller.hotel.ClientHotelRoomCellController;
import ui.componentcontroller.hotel.StaffHotelRoomCellController;
import ui.componentcontroller.promotion.OrderPromotionCellController;
import ui.viewcontroller.client.ClientSearchHotelViewController;
import util.DateUtil;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/12/2.
 */
public class HotelDetailViewController {

    @FXML
    private Label nameLabel;

    @FXML
    private StarLabel starLabel;

    @FXML
    private StateButton cityButton;

    @FXML
    private StateButton placeButton;

    @FXML
    private Label addressLabel;

    @FXML
    private Text introductionText;

    @FXML
    private Text facilitiesText;

    @FXML
    private Label scoreLabel;

    @FXML
    private MyDatePicker datePicker;

    @FXML
    private VBox promotionVBox;

    @FXML
    private VBox roomVBox;

    @FXML
    private RectButton promotionDetailButton;

    @FXML
    private RectButton roomDetailButton;

    @FXML
    private RateStarPane rateScorePane;

    private HotelInfoViewController hotelInfoViewController;

    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
    private PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();

    private Hotel_DetailVO hotel;

    private ArrayList<RoomStockVO> roomStocks;

    public void setHotelInfoViewController(HotelInfoViewController hotelInfoViewController) {
        this.hotelInfoViewController = hotelInfoViewController;
    }

    public void setHotel() {
        String staffID = userBLInfo.getCurrentStaffID();
        String hotelID = userBLInfo.getHotelIDByStaffID(staffID);

        hotel = hotelBLService.getHotel(hotelID);

        //Add hotel
        nameLabel.setText(hotel.name);
        starLabel.setStar(hotel.star);
        cityButton.setText(hotel.city.getName());
        placeButton.setText(hotel.place.getName());
        addressLabel.setText(hotel.address);
        introductionText.setText(hotel.introduction);
        facilitiesText.setText(hotel.facilities);
        scoreLabel.setText(String.valueOf(hotel.score)+"分");
        rateScorePane.setScore((int)hotel.score);
        //AddPromotion

        datePicker.setDate(LocalDate.now());
        datePicker.dateProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                addRooms();

            }
        });

        addPromotions();
        addRooms();
    }


    private void addPromotions() {
        promotionVBox.getChildren().clear();
        ArrayList<PromotionVO> promotions = promotionBLService.searchHotelPromotions(hotel.ID);

        for (PromotionVO promotion : promotions) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/component/promotion/OrderPromotionCell.fxml"));
                Pane pane = loader.load();

                OrderPromotionCellController orderPromotionCellController = loader.getController();
                orderPromotionCellController.setPromotion(promotion);

                promotionVBox.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addRooms() {
        roomVBox.getChildren().clear();

        DateUtil date = new DateUtil(datePicker.getDate());

        ArrayList<HotelRoomVO> hotelRooms = hotelBLService.getRoom(hotel.ID);

        for (HotelRoomVO hotelRoom : hotelRooms) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/component/hotel/StaffHotelRoomCell.fxml"));
                Pane pane = loader.load();

                StaffHotelRoomCellController staffHotelRoomCellController = loader.getController();
                staffHotelRoomCellController.setHotelDetailViewController(this);
                staffHotelRoomCellController.setRoom(hotelRoom, date);

                roomVBox.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void clickEditButton() {
        hotelInfoViewController.showHotelDetailEdit();
    }

}
