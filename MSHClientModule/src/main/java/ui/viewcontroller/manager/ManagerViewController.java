package ui.viewcontroller.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;

/**
 * Created by Kray on 2016/11/24.
 */
public class ManagerViewController {

    private MainUIController mainUIController;

    /**
     * 管理酒店
     */
    private HotelManagementViewController hotelManagementViewController;
    /**
     * 管理所有客户
     */
    private ClientManagementViewController clientManagementViewController;
    /**
     * 管理酒店工作人员和网站营销人员
     */
    private WorkerManagementViewController workerManagementViewController;

    public ManagerViewController(MainUIController mainUIController) {

        this.mainUIController = mainUIController;

        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(getClass().getResource("/view/manager/ManagerNavbar.fxml"));
            Pane navbar = navLoader.load();

            ManagerNavbarController managerNavbarController = navLoader.getController();
            managerNavbarController.setManagerViewController(this);

            mainUIController.setLeft(navbar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientManagementViewController = new ClientManagementViewController(mainUIController);
        workerManagementViewController = new WorkerManagementViewController(mainUIController);
        hotelManagementViewController = new HotelManagementViewController(mainUIController);
    }

    public void showClientList() {
        clientManagementViewController.showClientList();
        clientManagementViewController.setSalesman(false);
    }

    public void showWorkerList() {
        workerManagementViewController.showWorkerList();
    }

    public void showHotelList() {
        hotelManagementViewController.showHotelList();
    }
}
