package ui.viewcontroller.staff;

import blimpl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import blservice.orderblservice.OrderBLInfo;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ui.componentcontroller.hotel.StaffHotelRoomCellController;
import ui.componentcontroller.order.HotelAssessmentCellController;
import ui.componentcontroller.promotion.OrderPromotionCellController;
import util.DateUtil;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
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
    private MyDatePicker datePicker;

    @FXML
    private VBox promotionVBox;

    @FXML
    private VBox roomVBox;

    @FXML
    private VBox commentVBox;

    @FXML
    private RectButton promotionDetailButton;

    @FXML
    private RectButton roomDetailButton;

    @FXML
    private Label assessmentCountLabel;

    @FXML
    private RateStarPane rateScorePane;

    @FXML
    private Label scoreLabel;

    @FXML
    private RateStarPane serviceScorePane;

    @FXML
    private Label serviceScoreLabel;

    @FXML
    private RateStarPane facilityScorePane;

    @FXML
    private Label facilityScoreLabel;

    @FXML
    private RateStarPane healthScorePane;

    @FXML
    private Label healthScoreLabel;

    @FXML
    private RateStarPane locationScorePane;

    @FXML
    private Label locationScoreLabel;


    private HotelInfoViewController hotelInfoViewController;

    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
    private PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
    private OrderBLInfo orderBLInfo = new BLFactoryImpl().getOrderBLInfo();

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
//        scoreLabel.setText(String.valueOf(hotel.score)+"分");
//        rateScorePane.setScore((int)hotel.score);
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
        addAssessment();
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

    private void addAssessment() {
        commentVBox.getChildren().clear();

        ArrayList<Assessment_HotelVO> assessment_hotelVOs = orderBLInfo.getAssessmentByHotelID(hotel.ID);

        int size = assessment_hotelVOs.size();
        assessmentCountLabel.setText(size + " 条评论");

        double score = 0;
        double serviceScore = 0;
        double facilityScore = 0;
        double healthScore = 0;
        double locationScore = 0;

        for (Assessment_HotelVO assessment_hotelVO : assessment_hotelVOs) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/component/order/HotelAssessmentCell.fxml"));
                Pane pane = loader.load();

                HotelAssessmentCellController hotelAssessmentCellController = loader.getController();
                hotelAssessmentCellController.setAssessment(assessment_hotelVO);

                commentVBox.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }

            score += assessment_hotelVO.serviceScore + assessment_hotelVO.healthScore + assessment_hotelVO.facilityScore + assessment_hotelVO.locationScore;
            serviceScore += assessment_hotelVO.serviceScore;
            facilityScore += assessment_hotelVO.facilityScore;
            healthScore += assessment_hotelVO.healthScore;
            locationScore += assessment_hotelVO.locationScore;
        }

        if (size != 0) {
            score = score/size/4.0;
            serviceScore /= size;
            facilityScore /= size;
            healthScore /= size;
            locationScore /= size;
        }

        scoreLabel.setText(String.format("%.1f", score) + " 分");
        rateScorePane.setScore((int)score);

        serviceScoreLabel.setText(String.format("%.1f", serviceScore) + " 分");
        serviceScorePane.setScore((int)serviceScore);
        facilityScoreLabel.setText(String.format("%.1f", facilityScore) + " 分");
        facilityScorePane.setScore((int)facilityScore);
        healthScoreLabel.setText(String.format("%.1f", healthScore) + " 分");
        healthScorePane.setScore((int)healthScore);
        locationScoreLabel.setText(String.format("%.1f", locationScore) + " 分");
        locationScorePane.setScore((int)locationScore);
    }

    @FXML
    private void clickEditButton() {
        hotelInfoViewController.showHotelDetailEdit();
    }

}
