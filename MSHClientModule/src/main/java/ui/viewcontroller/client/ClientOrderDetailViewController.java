package ui.viewcontroller.client;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import component.mycheckbox.MyCheckBox;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.order.OrderRoomCellController;
import ui.componentcontroller.promotion.OrderPromotionCellController;
import util.OrderState;
import vo.OrderRoomVO;
import vo.OrderVO;
import vo.PromotionVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/22.
 */
public class ClientOrderDetailViewController {

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label bookTimeLabel;

    @FXML
    private StateButton stateLabel;

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
    private RectButton cancelButton;

    private ClientOrderViewController clientOrderViewController;

    private OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    private OrderVO order;

    public void setClientViewController(ClientOrderViewController clientOrderViewController) {
        this.clientOrderViewController = clientOrderViewController;
    }

    public void showOrder(OrderVO order) {
        this.order = order;

        orderIDLabel.setText(order.orderID);
        hotelNameLabel.setText(order.hotelName);
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

        if (order.state == OrderState.Cancelled) {
            cancelledLabel.setVisible(true);
            cancelledTimeLabel.setVisible(true);
            cancelledTimeLabel.setText(order.cancelledTime.toString());
            cancelButton.setVisible(false);
            cancelButton.setManaged(false);
        } else {
            cancelledLabel.setVisible(false);
            cancelledTimeLabel.setVisible(false);
            cancelButton.setVisible(true);
            cancelButton.setManaged(true);
        }
    }

    private void addRooms(ArrayList<OrderRoomVO> rooms) {
        for (OrderRoomVO room : rooms) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../component/order/OrderRoomCell.fxml"));
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
            loader.setLocation(Main.class.getResource("../component/promotion/OrderPromotionCell.fxml"));
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
        clientOrderViewController.back();
    }

    @FXML
    private void clickCancelButton() {
        orderBLService.revokeOrder(order.orderID);
        order = orderBLService.searchOrderByID(order.orderID);
        updateState();
    }
}
