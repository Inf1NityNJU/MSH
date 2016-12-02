package ui.viewcontroller.client;

import blservice.hotelblservice.HotelBLService;
import blservice.hotelblservice.HotelBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.hotel.ClientHotelCellController;
import ui.componentcontroller.hotel.ClientHotelSearchPaneController;
import ui.componentcontroller.order.ClientOrderCellController;
import ui.componentcontroller.order.ClientOrderSearchPaneController;
import vo.Hotel_BriefVO;
import vo.Hotel_DetailVO;

import java.io.IOException;

/**
 * Created by Sorumi on 16/11/27.
 */
public class ClientHotelListViewController {

    private static final int NUM_OF_CELL = 6;

    private ClientSearchHotelViewController clientSearchHotelViewController;

    private FXMLLoader[] cellLoaders = new FXMLLoader[NUM_OF_CELL];
    private Node[] cells = new Node[NUM_OF_CELL];

    @FXML
    private VBox contentVBox;

    private TilePane tilePane;


    private HotelBLService hotelBLService;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {

//        hotelBLService = new HotelBLService_Stub();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/hotel/ClientHotelSearchPane.fxml"));
            VBox pane = loader.load();

            ClientHotelSearchPaneController controller = loader.getController();
            controller.setClientHotelListViewController(this);

            contentVBox.getChildren().add(pane);

            tilePane = new TilePane();
            tilePane.setPrefColumns(3);
            tilePane.setHgap(20);
            tilePane.setVgap(20);

            contentVBox.getChildren().add(tilePane);

            for (int i=0; i<NUM_OF_CELL; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/hotel/ClientHotelCell.fxml"));
                VBox cell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = cell;
            }

            showHotel();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }

    //TODO
    public void showHotel() {
//        hotelBLService.
        Hotel_BriefVO hotel = new Hotel_BriefVO("01012345", "酒店名字", "地址很长啦", 3, 4.8);

        for (int i=0; i<NUM_OF_CELL; i++) {
            FXMLLoader loader = cellLoaders[i];
            Node cell = cells[i];

            ClientHotelCellController clientHotelCellController = loader.getController();
            clientHotelCellController.setClientHotelListViewController(this);
            clientHotelCellController.setHotel(hotel);
            tilePane.getChildren().add(cell);
        }

    }

    public void showHotelDetail(String hotelID) {
        clientSearchHotelViewController.showHotelDetail(hotelID);
    }

}
