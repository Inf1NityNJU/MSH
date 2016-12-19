package ui.viewcontroller.manager;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.Hotel;
import blservice.hotelblservice.HotelBLInfo;
import blservice.hotelblservice.HotelBLService;
import blservice.orderblservice.OrderBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.componentcontroller.hotel.ManagerHotelCellController;
import ui.componentcontroller.hotel.ManagerHotelPagePaneController;
import ui.componentcontroller.hotel.ManagerHotelSearchPaneController;
import ui.componentcontroller.order.ClientOrderCellController;
import ui.componentcontroller.order.ClientOrderSearchPaneController;
import vo.FilterFlagsVO;
import vo.Hotel_BriefVO;
import vo.Hotel_DetailVO;
import vo.OrderVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sorumi on 16/12/19.
 */
public class HotelManagementListViewController {

    private static final int NUM_OF_CELL = 4;

    private HotelManagementViewController hotelManagementViewController;

    @FXML
    private VBox contentVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[NUM_OF_CELL];
    private Node[] cells = new Node[NUM_OF_CELL];

    private Node pagePane;
    private ManagerHotelPagePaneController managerHotelPagePaneController;

    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();

    private ArrayList<Hotel_BriefVO> hotels = new ArrayList<>();
//    private OrderState orderState;

    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/hotel/ManagerHotelSearchPane.fxml"));
            VBox pane = loader.load();

            ManagerHotelSearchPaneController managerHotelSearchPaneController = loader.getController();
            managerHotelSearchPaneController.setHotelManagementListViewController(this);

            contentVBox.getChildren().add(pane);


            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/component/hotel/ManagerHotelPagePane.fxml"));
            pagePane = pageLoader.load();

            managerHotelPagePaneController = pageLoader.getController();
            managerHotelPagePaneController.setHotelManagementListViewController(this);


            for (int i = 0; i < NUM_OF_CELL; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/hotel/ManagerHotelCell.fxml"));
                HBox cell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = cell;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setHotelManagementViewController(HotelManagementViewController hotelManagementViewController) {
        this.hotelManagementViewController = hotelManagementViewController;
    }


    public void showHotels(FilterFlagsVO flags) {
        hotels = hotelBLService.searchHotelInBriefVO(flags);
        int size = hotels.size();
        managerHotelPagePaneController.setPageCount(size / NUM_OF_CELL + ((size % NUM_OF_CELL == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Order");
            clearCells();
        }
    }

    public void turnPage(int page) {
        int fromIndex = (page - 1) * NUM_OF_CELL;
        int toIndex = Math.min(page * NUM_OF_CELL, hotels.size());
        List<Hotel_BriefVO> tmpHotels = hotels.subList(fromIndex, toIndex);
        setCells(tmpHotels);
    }

    private void clearCells() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        contentVBox.getChildren().remove(pagePane);
    }

    private void setCells(List<Hotel_BriefVO> hotels) {

        if (hotels.size() > NUM_OF_CELL) {
            System.out.println("ERROR");
            return;
        }

        clearCells();

        for (int i = 0; i < hotels.size(); i++) {

            Hotel_BriefVO hotel = hotels.get(i);
            FXMLLoader loader = cellLoaders[i];
            Node cell = cells[i];

            ManagerHotelCellController managerHotelCellController = loader.getController();
            managerHotelCellController.setHotelManagementListViewController(this);
            managerHotelCellController.setHotel(hotel);

            contentVBox.getChildren().add(cell);
        }

        contentVBox.getChildren().add(pagePane);
    }

    public void showHotelDetail(Hotel_BriefVO hotel_briefVO) {

    }
}
