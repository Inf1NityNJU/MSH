package ui.viewcontroller.client;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import com.sun.xml.internal.bind.v2.TODO;
import component.mydatepicker.MyDatePicker;
import component.ratestarpane.RateStarPane;
import component.starlabel.StarLabel;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Main;
import ui.componentcontroller.promotion.OrderPromotionCellController;
import ui.componentcontroller.hotel.ClientHotelRoomCellController;
import util.DateUtil;
import util.RoomType;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientHotelDetailViewController {

    @FXML
    private VBox promotionVBox;

    @FXML
    private VBox roomVBox;

    @FXML
    private MyDatePicker checkInDatePicker;

    @FXML
    private MyDatePicker checkOutDatePicker;

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
    private RateStarPane rateScorePane;

    private OrderVO order;


    private ClientSearchHotelViewController clientSearchHotelViewController;


    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }

    //TODO
    public void setHotel(Hotel_DetailVO hotel) {
        //Add hotel
        nameLabel.setText(hotel.name);
        starLabel.setStar(hotel.star);
        cityButton.setText(hotel.city.toString());
        placeButton.setText(hotel.place.toString());
        addressLabel.setText(hotel.address);
        introductionText.setText(hotel.introduction);
        facilitiesText.setText(hotel.facilities);
        scoreLabel.setText(String.valueOf(hotel.score)+"分");
        rateScorePane.setScore((int)hotel.score);
        //AddPromotion

        checkInDatePicker.setDate(LocalDate.now());
        checkOutDatePicker.setDate(LocalDate.now().plusDays(1));

        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

        // new OrderVO
        order = new OrderVO(hotel.ID, new DateUtil(LocalDate.now().format(dateFormatter)), new DateUtil(LocalDate.now().plusDays(1).format(dateFormatter)));
        order.rooms = new ArrayList<>();
        order.hotelName = hotel.name;

        addPromotions(null);
        addRooms();
    }


    private void addPromotions(ArrayList<Promotion_HotelVO> promotions) {
        for (int i = 0; i < 3; i++) {
//        for (Promotion_HotelVO promotion : promotions) {
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

    private void addRooms() {

        //TODO
        //需要Hotel所有的OrderRoomStock和Order的OrderRoomStock
//        OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();
//        ArrayList<OrderRoomStockVO> rooms = orderBLService.getOrderRoomStocks(order);

        for (int i = 0; i < 3; i++) {
//        for (OrderRoomStockVO room : rooms) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../component/hotel/ClientHotelRoomCell.fxml"));
                Pane pane = loader.load();

                ClientHotelRoomCellController clientHotelRoomCellController = loader.getController();
                clientHotelRoomCellController.setClientHotelDetailViewController(this);
//                clientHotelRoomCellController.setRoom(room);

                roomVBox.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void clickBookButton() {
        //TODO
        OrderRoomVO roomVO = new OrderRoomVO(RoomType.SingleRoom, 1, 200);
        OrderRoomVO roomVO1 = new OrderRoomVO(RoomType.DoubleRoom, 1, 300);
        order.rooms.add(roomVO);
        order.rooms.add(roomVO1);
        clientSearchHotelViewController.showBookOrder(order);
    }

}
