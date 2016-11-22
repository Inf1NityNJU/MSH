package ui.viewcontroller.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.order.OrderDetailViewController;
import ui.viewcontroller.order.OrderListViewController;
import ui.viewcontroller.order.OrderViewController;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/11/17.
 */
public class MainUIController {

    private BorderPane rootPane;

    private OrderViewController orderViewController = new OrderViewController();

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;
        this.orderViewController.setRootPane(rootPane);
    }

    public void showMainView() {
        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(Main.class.getResource("../component/common/Navbar.fxml"));
            Pane navbar = navLoader.load();

            FXMLLoader headerLoader = new FXMLLoader();
            headerLoader.setLocation(Main.class.getResource("../component/common/Header.fxml"));
            HBox header = headerLoader.load();

            FXMLLoader sectionLoader = new FXMLLoader();
            sectionLoader.setLocation(Main.class.getResource("../component/common/Section.fxml"));
            ScrollPane section = sectionLoader.load();

            rootPane.setTop(header);
            rootPane.setLeft(navbar);
            rootPane.setCenter(section);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClientOrders() {
        orderViewController.showOrderList();
    }


}
