package ui.viewcontroller.client;

import blimpl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import component.mycheckbox.MyCheckBox;
import component.mychoicebox.MyChoiceBox;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.componentcontroller.promotion.OrderPromotionCellController;
import ui.componentcontroller.order.ClientOrderRoomEditCellController;
import ui.viewcontroller.common.MainUIController;
import util.ResultMessage;
import util.TimeUtil;
import vo.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientBookOrderViewController {

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label checkDateLabel;

    @FXML
    private MyCheckBox hasChildrenCheckBox;

    @FXML
    private MyChoiceBox peopleQuantityBox;

    @FXML
    private MyChoiceBox executeTimeBox;

    @FXML
    private Label executeDateLabel;

    @FXML
    private Label originPriceLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private VBox roomVBox;

    @FXML
    private VBox promotionVBox;

    private ClientSearchHotelViewController clientSearchHotelViewController;

    private OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    private OrderVO order;

    private MainUIController mainUIController;

    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void setOrder(OrderVO order) {
        this.order = order;
        orderBLService.startOrder(order);

        hotelNameLabel.setText(order.hotelName);
        checkDateLabel.setText(order.checkInDate.toString() + " - " + order.checkOutDate.toString());
        executeDateLabel.setText(order.checkInDate.toString());

        peopleQuantityBox.getSelectionModel().selectFirst();
        executeTimeBox.getSelectionModel().selectFirst();

        addRoom(order.rooms);

    }

    private void addRoom(ArrayList<OrderRoomVO> rooms) {

        for (OrderRoomVO room : rooms) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/component/order/ClientOrderRoomEditCell.fxml"));
                Pane pane = loader.load();

                ClientOrderRoomEditCellController clientOrderRoomEditCellController = loader.getController();
                clientOrderRoomEditCellController.setClientBookOrderViewController(this);
                clientOrderRoomEditCellController.setRoom(room);
                clientOrderRoomEditCellController.setPane(pane);

                roomVBox.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPromotions(PromotionVO promotion) {
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
    private void clickBookButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();
            alertViewController.setInfoLabel("确认预订吗？");
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    sureBook();
                }
            });
            alertViewController.setOnClickCancelButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    cancelBook();
                }
            });
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sureBook() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();
            alertViewController.setInfoLabel("预订成功！可在'我的订单'里查看详情");
            alertViewController.hideLeftButton();
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    sure();
                }
            });
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sure() {
        boolean hasChildren = hasChildrenCheckBox.getIsActiveProperty();
        int peopleQuantity = (Integer) peopleQuantityBox.getSelectionModel().getSelectedItem();
        String time = (String) executeTimeBox.getSelectionModel().getSelectedItem();
        TimeUtil latest = new TimeUtil(order.checkInDate.toString() + " " + time + ":00");

        ResultMessage rm = orderBLService.generateOrder(latest, peopleQuantity, hasChildren);

        if (rm == ResultMessage.SUCCESS) {
            mainUIController.hidePop();
            clientSearchHotelViewController.back();
            clientSearchHotelViewController.back();
            clientSearchHotelViewController.refreshHotel();
        }

    }

    private void cancelBook() {
        mainUIController.hidePop();
    }

    @FXML
    private void clickBackButton() {
        clientSearchHotelViewController.back();
        clientSearchHotelViewController.refreshHotel();
    }

    public void removeRoom(Pane pane) {
        roomVBox.getChildren().remove(pane);
    }


    public void refreshBill() {
        BillVO bill = orderBLService.getBill();
        originPriceLabel.setText("¥ " + bill.originPrice);
        totalPriceLabel.setText("¥ " + bill.totalPrice);

        promotionVBox.getChildren().clear();
        if (bill.hotelPromotion != null) {
            addPromotions(bill.hotelPromotion);
        }
        if (bill.websitePromotion != null) {
            addPromotions(bill.websitePromotion);
        }
    }
}
