package ui.viewcontroller.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main.Main;
import ui.viewcontroller.client.ClientViewController;
import ui.viewcontroller.manager.ManagerViewController;
import ui.viewcontroller.utility.UtilityViewController;
import ui.viewcontroller.staff.StaffViewController;

import java.io.IOException;

/**
 * Created by Sorumi on 16/11/17.
 */
public class MainUIController {

    private BorderPane rootPane;

    private ClientViewController clientViewController;

    private ManagerViewController managerViewController;

    private UtilityViewController utilityViewController;

    private StaffViewController staffViewController;

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;

    }

    public void showMainView() {
        try {
            FXMLLoader headerLoader = new FXMLLoader();
            headerLoader.setLocation(Main.class.getResource("../component/common/Header.fxml"));
            HBox header = headerLoader.load();

            FXMLLoader sectionLoader = new FXMLLoader();
            sectionLoader.setLocation(Main.class.getResource("../component/common/Section.fxml"));
            ScrollPane section = sectionLoader.load();

            rootPane.setTop(header);
            rootPane.setCenter(section);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClientView() {
        clientViewController = new ClientViewController(rootPane);
    }

    public void showManagerView() {
        managerViewController = new ManagerViewController(rootPane);
    }

    public void showUtilView() {
        utilityViewController = new UtilityViewController(rootPane);
    }

    public void showStaffView(){
        staffViewController=new StaffViewController(rootPane);
    }

}
