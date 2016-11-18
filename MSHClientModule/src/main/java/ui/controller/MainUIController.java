package ui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;

/**
 * Created by Sorumi on 16/11/17.
 */
public class MainUIController {

    private BorderPane rootPane;

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;
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

    public void showOrderList() {
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/order/OrderList.fxml"));
            ScrollPane list = listLoader.load();

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
