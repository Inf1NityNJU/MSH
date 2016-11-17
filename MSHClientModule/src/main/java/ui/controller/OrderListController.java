package ui.controller;

import component.StatusButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.Main;

import java.io.IOException;

/**
 * Created by Sorumi on 16/11/17.
 */
public class OrderListController {

    @FXML
    private VBox contentVBox;

    @FXML
    private StatusButton allButton;

    public void init() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/order/OrderSearchPane.fxml"));
            VBox pane = loader.load();

            contentVBox.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void clickNavButton() {
        if (allButton.getIsActiveProperty().booleanValue()) {
            allButton.setIsActiveProperty(false);
        } else {
            allButton.setIsActiveProperty(true);
        }

    }
}
