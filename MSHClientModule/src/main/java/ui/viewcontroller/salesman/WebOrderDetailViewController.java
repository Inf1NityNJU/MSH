package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import component.mycheckbox.MyCheckBox;
import component.ratestarpane.RateStarPane;
import component.statebutton.StateButton;
import component.tinybutton.TinyButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Main;
import ui.componentcontroller.order.OrderRoomCellController;
import ui.componentcontroller.promotion.OrderPromotionCellController;
import ui.viewcontroller.client.ClientOrderViewController;
import util.OrderState;
import util.TimeUtil;
import vo.AssessmentVO;
import vo.OrderRoomVO;
import vo.OrderVO;
import vo.PromotionVO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/22.
 */
public class WebOrderDetailViewController {

    private WebOrderViewController webOrderViewController;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label bookTimeLabel;

    @FXML
    private StateButton stateLabel;

    @FXML
    private TinyButton cancelButton;

    @FXML
    private HBox revokeHBox;

    @FXML
    private TinyButton halfCreditButton;

    @FXML
    private TinyButton allCreditButton;

    @FXML
    private Label cancelledLabel;

    @FXML
    private Label cancelledTimeLabel;

    @FXML
    private Label checkDateLabel;

    @FXML
    private Label checkInTimeLabel;

    @FXML
    private Label checkOutTimeLabel;

    @FXML
    private Label latestExecuteDateLabel;

    @FXML
    private Label latestExecuteTimeLabel;

    @FXML
    private Label peopleQuantityLabel;

    @FXML
    private MyCheckBox hasChildrenCheckBox;

    @FXML
    private VBox roomVBox;

    @FXML
    private VBox promotionVBox;

    @FXML
    private Label originPriceLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label noAssessmentLabel;

    @FXML
    private GridPane scorePane;

    @FXML
    private RateStarPane serviceScorePane;

    @FXML
    private RateStarPane facilityScorePane;

    @FXML
    private RateStarPane healthScorePane;

    @FXML
    private RateStarPane locationScorePane;

    @FXML
    private Text commentText;

    private OrderVO order;

    private OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    public void setWebOrderViewController(WebOrderViewController webOrderViewController) {
        this.webOrderViewController = webOrderViewController;
    }

    public void showOrder(OrderVO order) {
        this.order = order;

        orderIDLabel.setText(order.orderID);
        hotelNameLabel.setText(order.hotelName);
        clientNameLabel.setText(order.clientName);
        bookTimeLabel.setText(order.bookedTime.toString());
        checkDateLabel.setText(order.checkInDate.toString() + " - " + order.checkOutDate.toString());
        checkInTimeLabel.setText((order.checkInTime != null) ? order.checkInTime.toString() : "未入住");
        checkOutTimeLabel.setText((order.checkOutTime != null) ? order.checkOutTime.toString() : "未退房");
        latestExecuteDateLabel.setText(order.latestExecuteTime.date.toString());
        latestExecuteTimeLabel.setText(order.latestExecuteTime.timeString());
        peopleQuantityLabel.setText(order.peopleQuantity + "");
        hasChildrenCheckBox.setIsAbledProperty(false);
        hasChildrenCheckBox.setIsActiveProperty(order.hasChildren);
        originPriceLabel.setText("¥ " + order.bill.originPrice);
        totalPriceLabel.setText("¥ " + order.bill.totalPrice);

        updateState();

        addRooms(order.rooms);

        if (order.bill.websitePromotion != null) {
            addPromotion(order.bill.websitePromotion);
        }
        if (order.bill.hotelPromotion != null) {
            addPromotion(order.bill.hotelPromotion);
        }

    }

    private void updateState() {
        stateLabel.setText(order.state.getName());
        stateLabel.setColorProperty(order.state.getColor());
        revokeHBox.setVisible(false);
        revokeHBox.setManaged(false);

        OrderState state = order.state;
        if (state == OrderState.Cancelled) {
            cancelledLabel.setVisible(true);
            cancelledTimeLabel.setVisible(true);
            cancelledTimeLabel.setText(order.cancelledTime.toString());
        } else {
            cancelledLabel.setVisible(false);
            cancelledTimeLabel.setVisible(false);
        }

        if (state == OrderState.Abnormal) {
            cancelButton.setVisible(true);
        } else {
            cancelButton.setVisible(false);
        }

        if (state == OrderState.Executed) {
            AssessmentVO assessment = order.assessment;
            if (assessment == null) {
                setAssessmentDisplay(false);

            } else {
                setAssessmentDisplay(true);

                serviceScorePane.setScore(assessment.serviceScore);
                facilityScorePane.setScore(assessment.facilityScore);
                healthScorePane.setScore(assessment.healthScore);
                locationScorePane.setScore(assessment.locationScore);
                commentText.setText(assessment.comment);
            }
        } else {
            setAssessmentDisplay(false);

        }

    }
    private void addRooms(ArrayList<OrderRoomVO> rooms) {
        for (OrderRoomVO room : rooms) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/component/order/OrderRoomCell.fxml"));
                Pane pane = loader.load();

                OrderRoomCellController orderRoomCellController = loader.getController();
                orderRoomCellController.setRoom(room);

                roomVBox.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPromotion(PromotionVO promotion) {
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

    @FXML
    private void clickBackButton() {
        webOrderViewController.refreshHotelOrderList();
        webOrderViewController.back();
    }


    @FXML
    private void clickCancelButton() {
        cancelButton.setVisible(false);
        cancelButton.setManaged(false);
        revokeHBox.setVisible(true);
        revokeHBox.setManaged(true);
    }

    @FXML
    private void clickHalfRevokeButton() {
        orderBLService.revokeAbnormalOrder(order.orderID, (int)order.bill.totalPrice/2);
        order = orderBLService.searchOrderByID(order.orderID);
        updateState();
    }


    @FXML
    private void clickAllRevokeButton() {
        orderBLService.revokeAbnormalOrder(order.orderID, (int)order.bill.totalPrice);
        order = orderBLService.searchOrderByID(order.orderID);
        updateState();
    }

    private void setAssessmentDisplay(boolean display) {
        noAssessmentLabel.setVisible(!display);
        noAssessmentLabel.setManaged(!display);
        scorePane.setVisible(display);
        scorePane.setManaged(display);
        commentText.setVisible(display);
        commentText.setManaged(display);
    }
}
