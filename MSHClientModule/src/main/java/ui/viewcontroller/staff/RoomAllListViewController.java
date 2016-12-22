package ui.viewcontroller.staff;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLInfo;
import component.pagepane.PagePane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.componentcontroller.hotel.RoomAllCellController;
import vo.HotelRoomVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SilverNarcissus on 2016/11/27.
 */
public class RoomAllListViewController {

    private static final int NUM_OF_CELL = 3;

    @FXML
    private Label availableRoomLabel;

    @FXML
    private VBox roomVBox;

    @FXML
    private PagePane pagePane;

    private FXMLLoader[] cellLoaders = new FXMLLoader[NUM_OF_CELL];
    private Node[] cells = new Node[NUM_OF_CELL];

    private RoomInfoViewController roomInfoViewController;

    private HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();

    private ArrayList<HotelRoomVO> hotelRooms;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        hotelBLService = HotelBLFactory.getHotelBLService();

        try {

            for (int i = 0; i < NUM_OF_CELL; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/hotel/RoomAllCell.fxml"));
                HBox roomCell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = roomCell;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setRoomInfoViewController(RoomInfoViewController roomInfoViewController) {
        this.roomInfoViewController = roomInfoViewController;
    }

    @FXML
    private void clickAvailableLabel() {
        roomInfoViewController.showRoomAvailableList();
    }

    @FXML
    private void pageChanged() {
        turnPage(pagePane.getCurrentPage());
    }

    public void showAllRoomList() {

        String staffID = userBLInfo.getCurrentStaffID();
        String hotelID = userBLInfo.getHotelIDByStaffID(staffID);

        hotelRooms = hotelBLService.getRoom(hotelID);

        int size = hotelRooms.size();
        pagePane.setCount(size / NUM_OF_CELL + ((size % NUM_OF_CELL == 0) ? 0 : 1));

        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Room");
            clearCells();
        }
    }

    public void turnPage(int page) {
        int fromIndex = (page - 1) * NUM_OF_CELL;
        int toIndex = Math.min(page * NUM_OF_CELL, hotelRooms.size());
        List<HotelRoomVO> tmpRooms = hotelRooms.subList(fromIndex, toIndex);
        setCells(tmpRooms);
    }

    private void clearCells() {
        roomVBox.getChildren().clear();
    }

    private void setCells(List<HotelRoomVO> rooms) {

        if (rooms.size() > NUM_OF_CELL) {
            System.out.println("ERROR");
            return;
        }

        clearCells();

        for (int i = 0; i < rooms.size(); i++) {

            HotelRoomVO hotelRoom = rooms.get(i);
            FXMLLoader loader = cellLoaders[i];
            Node roomCell = cells[i];

            RoomAllCellController roomAllCellController = loader.getController();
            roomAllCellController.setRoomAllListViewController(this);
            roomAllCellController.setRoom(hotelRoom);


            roomVBox.getChildren().add(roomCell);
        }

    }

    /**
     * 显示添加房间面板
     */
    public void showAddRoomPane() {
        roomInfoViewController.showAddRoomView();
    }
}
