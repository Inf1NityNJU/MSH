package ui.viewcontroller.client;

import bl.blfactory.BLFactoryImpl;
import bl.orderbl.OrderRoom;
import blservice.hotelblservice.HotelBLService;
import blservice.orderblservice.OrderBLService;
import blservice.promotionblservice.PromotionBLService;
import com.sun.xml.internal.bind.v2.TODO;
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
    private Label bookRoomLabel;

    @FXML
    private RectButton bookButton;

    @FXML
    private RateStarPane rateScorePane;

    private OrderVO order;


    private ClientSearchHotelViewController clientSearchHotelViewController;

    private  HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
    private  OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    private Hotel_DetailVO hotel;

    private ArrayList<OrderRoomStockVO> roomStocks;
    private int bookRoomQuantity;

    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }

    public void setHotel(Hotel_DetailVO hotel) {
        this.hotel = hotel;

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
        checkInDatePicker.setMinDate(LocalDate.now());
        checkInDatePicker.setMaxDate(LocalDate.now().plusDays(28));
        checkOutDatePicker.setDate(checkInDatePicker.getDate().plusDays(1));
        checkOutDatePicker.setMinDate(checkInDatePicker.getDate().plusDays(1));
        checkOutDatePicker.setMaxDate(LocalDate.now().plusDays(29));
        checkInDatePicker.dateProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                checkOutDatePicker.setMinDate(newValue.plusDays(1));
                if (checkOutDatePicker.getDate().isBefore(newValue)) {
                    checkOutDatePicker.setDate(newValue.plusDays(1));
                }
                newOrder();
            }
        });
        checkOutDatePicker.dateProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                newOrder();
            }
        });

        addPromotions();

        newOrder();

    }

    public void newOrder() {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

        order = new OrderVO(hotel.ID, new DateUtil(checkInDatePicker.getDate().format(dateFormatter)), new DateUtil(checkOutDatePicker.getDate().format(dateFormatter)));
        order.rooms = new ArrayList<>();
        order.hotelName = hotel.name;
        orderBLService.startOrder(order);
        addRooms();

        bookRoomQuantity = 0;
        bookRoomLabel.setText("");
        bookButton.setVisible(false);
    }


    private void addPromotions() {
        PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();
        ArrayList<PromotionVO> promotions = promotionBLService.searchHotelPromotions(hotel.ID);

        for (PromotionVO promotion : promotions) {
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
    }

    private void addRooms() {
        roomVBox.getChildren().clear();

        roomStocks = hotelBLService.getRoomStocks(new DateUtil(checkInDatePicker.getDate()), new DateUtil(checkOutDatePicker.getDate()), hotel.ID);

        for (OrderRoomStockVO room : roomStocks) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../component/hotel/ClientHotelRoomCell.fxml"));
                Pane pane = loader.load();

                ClientHotelRoomCellController clientHotelRoomCellController = loader.getController();
                clientHotelRoomCellController.setClientHotelDetailViewController(this);
                clientHotelRoomCellController.setRoom(room);

                roomVBox.getChildren().add(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRoomInOrder(OrderRoomStockVO orderRoomStock) {
        bookRoomQuantity++;
        bookButton.setVisible(true);
        bookRoomLabel.setText("已定 " + bookRoomQuantity + " 间");
    }

    @FXML
    private void clickBookButton() {
        //TODO

        for (OrderRoomStockVO room : roomStocks) {
            if (room.orderRoom.quantity > 0) {
                OrderRoomVO roomVO = room.orderRoom;
                order.rooms.add(roomVO);
            }
        }

        clientSearchHotelViewController.showBookOrder(order);
    }

}
