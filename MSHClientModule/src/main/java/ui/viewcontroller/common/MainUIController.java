package ui.viewcontroller.common;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.Main;
import ui.componentcontroller.common.HeaderViewController;
import ui.viewcontroller.client.ClientViewController;
import ui.viewcontroller.manager.ManagerViewController;
import ui.viewcontroller.salesman.SalesmanViewController;
import ui.viewcontroller.utility.LoginViewController;
import ui.viewcontroller.utility.UtilityViewController;
import ui.viewcontroller.staff.StaffViewController;


import java.io.IOException;

/**
 * Created by Sorumi on 16/11/17.
 */
public class MainUIController {

    @FXML
    private Pane popPane;

    @FXML
    private BorderPane rootPane;

    private HeaderViewController headerViewController;

    private ClientViewController clientViewController;
    private ManagerViewController managerViewController;
    private SalesmanViewController salesmanViewController;
    private UtilityViewController utilityViewController;
    private StaffViewController staffViewController;

    public void showMainView() {
        try {
            FXMLLoader headerLoader = new FXMLLoader();
            headerLoader.setLocation(getClass().getResource("/component/common/Header.fxml"));
            HBox header = headerLoader.load();

            headerViewController = headerLoader.getController();
            headerViewController.setMainUIController(this);

            FXMLLoader sectionLoader = new FXMLLoader();
            sectionLoader.setLocation(getClass().getResource("/component/common/Section.fxml"));
            ScrollPane section = sectionLoader.load();

            rootPane.setTop(header);
            rootPane.setCenter(section);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getCenter() {
        return rootPane.getCenter();
    }

    public void setCenter(Node node) {
        rootPane.setCenter(node);
    }

    public void setTop(Node node) {
        rootPane.setTop(node);
    }

    public void setLeft(Node node) {
        rootPane.setLeft(node);
    }

    public void showPop(Node node) {
        popPane.getChildren().add(node);
        popPane.setVisible(true);
        popPane.setManaged(true);
    }

    public void hidePop() {
        popPane.getChildren().clear();
        popPane.setVisible(false);
        popPane.setManaged(false);
    }

    public void showUtilView() {
        utilityViewController = new UtilityViewController(this);
    }

    public void showClientView() {
        clientViewController = new ClientViewController(this);
    }

    public void showManagerView() {
        managerViewController = new ManagerViewController(this);
    }

    public void showSalesmanView() {
        salesmanViewController = new SalesmanViewController(this);
    }

    public void showStaffView() {
        staffViewController = new StaffViewController(this);
    }

}
