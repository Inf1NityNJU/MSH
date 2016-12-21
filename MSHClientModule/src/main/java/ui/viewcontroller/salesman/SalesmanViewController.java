package ui.viewcontroller.salesman;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.viewcontroller.common.MainUIController;
import ui.viewcontroller.manager.ClientManagementViewController;

import java.io.IOException;

/**
 * Created by vivian on 16/11/27.
 */
public class SalesmanViewController {

    private MainUIController mainUIController;

    private WebOrderViewController webOrderViewController;

    private WebPromotionViewController webPromotionViewController;

    private LevelManagementViewController levelManagementViewController;

    private ClientManagementViewController clientManagementViewController;

    public SalesmanViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;

        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(getClass().getResource("/view/salesman/SalesmanNavbar.fxml"));
            Pane navbar = navLoader.load();

            SalesmanNavbarController salesmanNavbarController = navLoader.getController();
            salesmanNavbarController.setSalesmanViewController(this);

            mainUIController.setLeft(navbar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        webOrderViewController = new WebOrderViewController(mainUIController);
        webPromotionViewController = new WebPromotionViewController(mainUIController);
        levelManagementViewController = new LevelManagementViewController(mainUIController);
        clientManagementViewController = new ClientManagementViewController(mainUIController);
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
