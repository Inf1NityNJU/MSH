package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLInfo;
import component.mydatepicker.MyDatePicker;
import component.pagepane.PagePane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.hotel.RoomAvailableCellController;
import util.DateUtil;
import vo.HotelRoomVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SilverNarcissus on 2016/11/29.
 */
public class RoomAvailableListViewController {
    private static final int NUM_OF_CELL = 3;

    @FXML
    private Label availableRoomLabel;

    @FXML
    private VBox roomVBox;

    @FXML
    private PagePane pagePane;

    @FXML
    private MyDatePicker datePicker;

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
                cellLoader.setLocation(Main.class.getResource("../component/hotel/RoomAvailableCell.fxml"));
                HBox roomCell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = roomCell;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        datePicker.setDate(LocalDate.now());
        datePicker.dateProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                showAvailableRoomList();
            }
        });
    }

    public void setRoomInfoViewController(RoomInfoViewController roomInfoViewController) {
        this.roomInfoViewController = roomInfoViewController;
    }

    @FXML
    private void clickAllLabel() {
        roomInfoViewController.back();
    }

    @FXML
    private void pageChanged() {
        turnPage(pagePane.getCurrentPage());
    }

    public void showAvailableRoomList() {

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

            RoomAvailableCellController roomAvailableCellController = loader.getController();
            roomAvailableCellController.setRoomAvailableListViewController(this);
            roomAvailableCellController.setRoom(hotelRoom, new DateUtil(datePicker.getDate()));


            roomVBox.getChildren().add(roomCell);
        }

    }

}
