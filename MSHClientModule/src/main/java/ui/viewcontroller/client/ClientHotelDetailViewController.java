package ui.viewcontroller.client;

import component.mydatepicker.MyDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.hotel.ClientHotelCellController;
import ui.componentcontroller.hotel.ClientHotelPromotionCellController;
import ui.componentcontroller.hotel.ClientHotelRoomCellController;
import util.DateUtil;
import vo.HotelRoomVO;
import vo.OrderVO;
import vo.Promotion_HotelVO;
import vo.RoomStockVO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

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

    private OrderVO order;


    private ClientSearchHotelViewController clientSearchHotelViewController;


    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }

    //TODO
    public void setHotel(String hotelID) {
        //AddPromotion
        addPromotions(null);
        addRooms(null);

        checkInDatePicker.setDate(LocalDate.now());
        checkOutDatePicker.setDate(LocalDate.now().plusDays(1));

        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

        // new OrderVO
        order = new OrderVO(hotelID, new DateUtil(LocalDate.now().format(dateFormatter)), new DateUtil(LocalDate.now().plusDays(1).format(dateFormatter)));
    }


    private void addPromotions(ArrayList<Promotion_HotelVO> promotions) {
        for (int i = 0; i < 3; i++) {
//        for (Promotion_HotelVO promotion : promotions) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../component/hotel/ClientHotelPromotionCell.fxml"));
                Pane pane = loader.load();

                ClientHotelPromotionCellController clientHotelPromotionCellController = loader.getController();
//                clientHotelPromotionCellController.setPromotion(promotion);

                promotionVBox.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void addRooms(ArrayList<RoomStockVO> rooms) {
        for (int i = 0; i < 3; i++) {
//        for (HotelRoomVO room : rooms) {
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
        clientSearchHotelViewController.showBookOrder(order);
    }

}
