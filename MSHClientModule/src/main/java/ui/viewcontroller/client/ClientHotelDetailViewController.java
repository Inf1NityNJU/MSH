package ui.viewcontroller.client;

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
import vo.HotelRoomVO;
import vo.Promotion_HotelVO;
import vo.RoomStockVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientHotelDetailViewController {

    @FXML
    private VBox promotionVBox;

    @FXML
    private VBox roomVBox;


    private ClientSearchHotelViewController clientSearchHotelViewController;

    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }

    //TODO
    public void setHotel(String HotelID) {
        //AddPromotion
        addPromotions(null);
        addRooms(null);
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

}
