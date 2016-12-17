package ui.viewcontroller.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.common.MainUIController;
import ui.viewcontroller.manager.ClientManagementViewController;

import java.io.IOException;

/**
 * Created by Sorumi on 16/11/22.
 */
public class ClientViewController {

    private MainUIController mainUIController;

    private ClientOrderViewController clientOrderViewController;
    private ClientSearchHotelViewController clientSearchHotelViewController;
    private ClientInfoViewController clientInfoViewController;

    public ClientViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;

        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(getClass().getResource("/view/client/ClientNavbar.fxml"));
            Pane navbar = navLoader.load();

            ClientNavbarController clientNavbarController = navLoader.getController();
            clientNavbarController.setClientViewController(this);

            mainUIController.setLeft(navbar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientOrderViewController = new ClientOrderViewController(mainUIController);
        clientSearchHotelViewController = new ClientSearchHotelViewController(mainUIController);
        clientInfoViewController = new ClientInfoViewController(mainUIController);
    }

    public void showClientOrderList() {

        clientOrderViewController.showClientOrderList();
    }

    public void showHotelSearch() {
        clientSearchHotelViewController.showClientHotelList();
    }

    public void showClientDetail() {
        clientInfoViewController.showClientInfo();
    }

}
