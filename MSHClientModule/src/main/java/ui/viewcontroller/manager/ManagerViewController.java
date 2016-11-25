package ui.viewcontroller.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;

/**
 * Created by Kray on 2016/11/24.
 */
public class ManagerViewController {

    private BorderPane rootPane;

    private ClientManagementViewController clientManagementViewController;
    private StaffManagementListViewController staffManagementListViewController;
    private SalesmanManagementListViewController salesmanManagementListViewController;

    public ManagerViewController(BorderPane rootPane) {

        this.rootPane = rootPane;

        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(Main.class.getResource("../view/manager/ManagerNavbar.fxml"));
            Pane navbar = navLoader.load();

            ManagerNavbarController managerNavbarController = navLoader.getController();
            managerNavbarController.setManagerViewController(this);

            rootPane.setLeft(navbar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientManagementViewController = new ClientManagementViewController(rootPane);

    }

    public void showClientList(){
        System.out.println("Show Client List");

        clientManagementViewController.showClientList();
    }

    public void showStaffList(){
        System.out.println("Show Staff List");


    }

    public void showSalesmanList(){
        System.out.println("Show Salesman List");

    }
}
