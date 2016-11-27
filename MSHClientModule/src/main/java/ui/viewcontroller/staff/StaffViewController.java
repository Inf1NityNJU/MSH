package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.client.ClientNavbarController;
import ui.viewcontroller.client.ClientOrderViewController;

import java.io.IOException;

/**
 * Created by SilverNarcissus on 2016/11/27.
 *
 */
public class StaffViewController {
    private BorderPane rootPane;

    public StaffViewController(BorderPane rootPane) {
        this.rootPane = rootPane;

        try {
            //加载navbar
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(Main.class.getResource("../view/staff/StaffNavbar.fxml"));
            Pane navbar = navLoader.load();

            StaffNavbarController staffNavbarController = navLoader.getController();
            staffNavbarController.setStaffViewController(this);

            rootPane.setLeft(navbar);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
