package ui.viewcontroller.client;

import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.hotel.ClientHotelCellController;
import ui.componentcontroller.hotel.ClientHotelPagePaneController;
import ui.componentcontroller.hotel.ClientHotelSearchPaneController;
import vo.FilterFlagsVO;
import vo.Hotel_BriefVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sorumi on 16/11/27.
 */
public class ClientHotelListViewController {

    private static final int NUM_OF_CELL = 6;

    private ClientSearchHotelViewController clientSearchHotelViewController;

    private FXMLLoader[] cellLoaders = new FXMLLoader[NUM_OF_CELL];
    private Node[] cells = new Node[NUM_OF_CELL];

    private Node pagePane;
    private ClientHotelPagePaneController clientHotelPagePaneController;

    @FXML
    private VBox contentVBox;

    private TilePane tilePane;


    private HotelBLService hotelBLService;

    private ArrayList<Hotel_BriefVO> hotels = new ArrayList<>();

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {

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

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(Main.class.getResource("../component/hotel/ClientHotelPagePane.fxml"));
            pagePane = pageLoader.load();

            clientHotelPagePaneController = pageLoader.getController();
            clientHotelPagePaneController.setClientHotelListViewController(this);

            for (int i = 0; i < NUM_OF_CELL; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/hotel/ClientHotelCell.fxml"));
                VBox cell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = cell;
            }

            //showHotel();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }

    public void setHotelBLService(HotelBLService hotelBLService) {
        this.hotelBLService = hotelBLService;
    }

    public void showHotel(FilterFlagsVO filterFlagsVO) {
        hotelBLService = HotelBLFactory.getHotelBLService();
        hotels = hotelBLService.searchHotelInBriefVO(filterFlagsVO);

//        Hotel_BriefVO hotel = new Hotel_BriefVO("00000000", "酒店名字", "地址很长啦", 3, 4.8);
//
//        for (int i = 0; i < 7; i++) {
//            hotels.add(hotel);
//        }

        int size = hotels.size();
        clientHotelPagePaneController.setPageCount(size / NUM_OF_CELL + ((size % NUM_OF_CELL == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Hotel");
        }

    }

    public void turnPage(int page) {
        int fromIndex = (page - 1) * NUM_OF_CELL;
        int toIndex = Math.min(page * NUM_OF_CELL, hotels.size());
        List<Hotel_BriefVO> tmpHotels = hotels.subList(fromIndex, toIndex);
//        for(Hotel_BriefVO hotel_briefVO:tmpHotels){
//            System.out.println(hotel_briefVO);
//        }
        setCells(tmpHotels);
    }

    private void setCells(List<Hotel_BriefVO> hotel_BriefVOs) {


        if (hotel_BriefVOs.size() > NUM_OF_CELL) {
            System.out.println("ERROR");
            return;
        }

        for (Node cell : cells) {
            tilePane.getChildren().remove(cell);
        }

        contentVBox.getChildren().remove(pagePane);

        for (int i = 0; i < hotel_BriefVOs.size(); i++) {

            Hotel_BriefVO hotel = hotel_BriefVOs.get(i);

            FXMLLoader loader = cellLoaders[i];
            Node cell = cells[i];

            ClientHotelCellController clientHotelCellController = loader.getController();
            clientHotelCellController.setClientHotelListViewController(this);
            clientHotelCellController.setHotel(hotel);

            tilePane.getChildren().add(cell);
        }

        contentVBox.getChildren().add(pagePane);
    }

    public void showHotelDetail(String hotelID) {
        clientSearchHotelViewController.showHotelDetail(hotelID);
    }

}
