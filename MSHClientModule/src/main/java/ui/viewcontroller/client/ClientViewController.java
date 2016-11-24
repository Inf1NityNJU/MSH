package ui.viewcontroller.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/11/22.
 */
public class ClientViewController {

    private BorderPane rootPane;

    private ClientOrderViewController clientOrderViewController;

    public ClientViewController(BorderPane rootPane) {
        this.rootPane = rootPane;

        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(Main.class.getResource("../view/client/ClientNavbar.fxml"));
            Pane navbar = navLoader.load();

            ClientNavbarController clientNavbarController = navLoader.getController();
            clientNavbarController.setClientViewController(this);

            rootPane.setLeft(navbar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientOrderViewController = new ClientOrderViewController(rootPane);
    }

    public void showClientOrder() {

        clientOrderViewController.showClientOrderList();
    }


}
