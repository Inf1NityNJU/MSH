package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;

/**
 * Created by SilverNarcissus on 2016/11/27.
 */
public class StaffViewController {
    private MainUIController mainUIController;

    private HotelInfoViewController hotelInfoViewController;
    private RoomInfoViewController roomInfoViewController;
    private HotelOrderViewController hotelOrderViewController;
    private HotelPromotionViewController hotelPromotionViewController;

    public StaffViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;

        try {
            //加载navbar
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(getClass().getResource("/view/staff/StaffNavbar.fxml"));
            Pane navbar = navLoader.load();

            StaffNavbarController staffNavbarController = navLoader.getController();
            staffNavbarController.setStaffViewController(this);

            mainUIController.setLeft(navbar);

        } catch (IOException e) {
            e.printStackTrace();
        }

        hotelInfoViewController = new HotelInfoViewController(mainUIController);
        roomInfoViewController = new RoomInfoViewController(mainUIController);
        hotelOrderViewController = new HotelOrderViewController(mainUIController);
        hotelPromotionViewController = new HotelPromotionViewController(mainUIController);
    }

    public void showHotelInfoView() {
        hotelInfoViewController.showHotelDetail();
    }

    public void showRoomInfoList() {
        roomInfoViewController.showRoomAllList();
    }

    public void showHotelOrderList() {
        hotelOrderViewController.showHotelOrderList();
    }

    public void showHotelPromotionList(){hotelPromotionViewController.showHotelPromotionList();}
}
