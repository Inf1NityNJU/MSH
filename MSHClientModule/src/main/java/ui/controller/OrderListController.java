package ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;

import java.io.IOException;

/**
 * Created by Sorumi on 16/11/17.
 */
public class OrderListController {

    @FXML
    private VBox contentVBox;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/order/OrderSearchPane.fxml"));
            VBox pane = loader.load();

            contentVBox.getChildren().add(pane);

            addOrder(3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(int num) {
        try {
            for (int i = 0; i < num; i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../view/order/OrderCell.fxml"));
                HBox pane = loader.load();

                contentVBox.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
