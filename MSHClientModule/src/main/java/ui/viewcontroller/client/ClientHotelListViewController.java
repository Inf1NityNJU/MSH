package ui.viewcontroller.client;

import blimpl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import ui.componentcontroller.hotel.ClientHotelCellController;
import ui.componentcontroller.hotel.ClientHotelPagePaneController;
import ui.componentcontroller.hotel.ClientHotelSearchPaneController;
import util.HotelSortMethod;
import vo.FilterFlagsVO;
import vo.Hotel_DetailVO;

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


    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();

    private ArrayList<Hotel_DetailVO> hotels = new ArrayList<>();

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/hotel/ClientHotelSearchPane.fxml"));
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
            pageLoader.setLocation(getClass().getResource("/component/hotel/ClientHotelPagePane.fxml"));
            pagePane = pageLoader.load();

            clientHotelPagePaneController = pageLoader.getController();
            clientHotelPagePaneController.setClientHotelListViewController(this);

            for (int i = 0; i < NUM_OF_CELL; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/hotel/ClientHotelCell.fxml"));
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

    public void searchHotel(FilterFlagsVO filterFlagsVO) {
        hotels = hotelBLService.searchHotel(filterFlagsVO);
        sortHotel(HotelSortMethod.ScoreDescendingSort);
    }

    public void sortHotel(HotelSortMethod sortMethod) {
        switch (sortMethod) {
            case ScoreAscendingSort:
                hotelBLService.scoreAscendingSort(hotels);
                break;
            case ScoreDescendingSort:
                hotelBLService.scoreDescendingSort(hotels);
                break;
            case PriceAscendingSort:
                hotelBLService.priceAscendingSort(hotels);
                break;
            case PriceDescendingSort:
                hotelBLService.priceDescendingSort(hotels);
                break;
            case StarAscendingSort:
                hotelBLService.starAscendingSort(hotels);
                break;
            case StarDescendingSort:
                hotelBLService.starDescendingSort(hotels);
                break;
        }
        showHotel();
    }

    private void showHotel() {

        int size = hotels.size();
        clientHotelPagePaneController.setPageCount(size / NUM_OF_CELL + ((size % NUM_OF_CELL == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Hotel");
            clearCells();
        }

    }

    public void turnPage(int page) {
        int fromIndex = (page - 1) * NUM_OF_CELL;
        int toIndex = Math.min(page * NUM_OF_CELL, hotels.size());
        List<Hotel_DetailVO> tmpHotels = hotels.subList(fromIndex, toIndex);

        setCells(tmpHotels);
    }

    private void clearCells() {
        for (Node cell : cells) {
            tilePane.getChildren().remove(cell);
        }

        contentVBox.getChildren().remove(pagePane);
    }

    private void setCells(List<Hotel_DetailVO> hotels) {


        if (hotels.size() > NUM_OF_CELL) {
            System.out.println("ERROR");
            return;
        }

        clearCells();

        for (int i = 0; i < hotels.size(); i++) {

            Hotel_DetailVO hotel = hotels.get(i);

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
