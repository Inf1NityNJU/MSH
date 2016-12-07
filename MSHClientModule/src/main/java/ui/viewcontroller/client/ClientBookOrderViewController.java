package ui.viewcontroller.client;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import component.mycheckbox.MyCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.promotion.OrderPromotionCellController;
import ui.componentcontroller.order.ClientOrderRoomEditCellController;
import util.RoomType;
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
    private Label originPriceLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private VBox roomVBox;

    @FXML
    private VBox promotionVBox;

    private ClientSearchHotelViewController clientSearchHotelViewController;

    private OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }


    //TODO
    public void setOrder(OrderVO order) {
        orderBLService.startOrder(order);

        hotelNameLabel.setText(order.hotelName);
        checkDateLabel.setText(order.checkInDate.toString() + " - " + order.checkOutDate.toString());

        addPromotions(null);
        addRoom(order.rooms);

    }

    private void addRoom(ArrayList<OrderRoomVO> rooms) {
//        for (int i = 0; i < 1; i++) {
        for (OrderRoomVO room : rooms) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../component/order/ClientOrderRoomEditCell.fxml"));
                Pane pane = loader.load();

                ClientOrderRoomEditCellController clientOrderRoomEditCellController = loader.getController();
                clientOrderRoomEditCellController.setClientBookOrderViewController(this);
                clientOrderRoomEditCellController.setRoom(room);

                roomVBox.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPromotions(ArrayList<PromotionVO> promotions) {
        for (int i = 0; i < 2; i++) {
//        for (PromotionVO promotion : promotions) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../component/promotion/OrderPromotionCell.fxml"));
                Pane pane = loader.load();

                OrderPromotionCellController orderPromotionCellController = loader.getController();
//                orderPromotionCellController.setPromotion(promotion);

                promotionVBox.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void clickBackButton() {
        clientSearchHotelViewController.back();
    }


    public void refreshBill() {
        BillVO bill = orderBLService.getBill();
        originPriceLabel.setText("¥ " + bill.originPrice);
        totalPriceLabel.setText("¥ " + bill.totalPrice);
    }
}
