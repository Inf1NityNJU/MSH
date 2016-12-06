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

    /**
     * 管理所有客户
     */
    private ClientManagementViewController clientManagementViewController;
    /**
     * 管理酒店工作人员和网站营销人员
     */
    private WorkerManagementViewController workerManagementViewController;

    public ManagerViewController(BorderPane rootPane) {

        this.rootPane = rootPane;

        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(Main.class.getResource("../view/user/ManagerNavbar.fxml"));
            Pane navbar = navLoader.load();

            ManagerNavbarController managerNavbarController = navLoader.getController();
            managerNavbarController.setManagerViewController(this);

            rootPane.setLeft(navbar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientManagementViewController = new ClientManagementViewController(rootPane);
        workerManagementViewController = new WorkerManagementViewController(rootPane);

    }

    public void showClientList() {
        clientManagementViewController.showClientList();
        clientManagementViewController.setSalesman(false);
    }

    public void showWorkerList() {
        workerManagementViewController.showWorkerList();
    }

}
