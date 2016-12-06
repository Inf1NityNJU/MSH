package ui.viewcontroller.salesman;

import bl.userbl.Salesman;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.manager.ClientManagementViewController;

import java.io.IOException;

/**
 * Created by vivian on 16/11/27.
 */
public class SalesmanViewController {

    private BorderPane rootPane;

    private WebOrderViewController webOrderViewController;

    private WebPromotionViewController webPromotionViewController;

    private LevelManagementViewController levelManagementViewController;

    private ClientManagementViewController clientManagementViewController;

    public SalesmanViewController(BorderPane rootPane) {
        this.rootPane = rootPane;

        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(Main.class.getResource("../view/salesman/SalesmanNavbar.fxml"));
            Pane navbar = navLoader.load();

            SalesmanNavbarController salesmanNavbarController = navLoader.getController();
            salesmanNavbarController.setSalesmanViewController(this);

            rootPane.setLeft(navbar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        webOrderViewController = new WebOrderViewController(rootPane);
        webPromotionViewController = new WebPromotionViewController(rootPane);
        levelManagementViewController = new LevelManagementViewController(rootPane);
        clientManagementViewController = new ClientManagementViewController(rootPane);
    }

    public void showWebOrderList() {
        webOrderViewController.showWebOrderList();
    }

    public void showWebPromotionList() {
        webPromotionViewController.showWebPromotionList();
    }

    public void showLevelList(){
        levelManagementViewController.showLevelList();
    }

    public void showClientList() {
        clientManagementViewController.showClientList();
        clientManagementViewController.setSalesman(true);
    }
}
