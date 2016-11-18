//package ui.controller;
//
//import component.StatusButton;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Button;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import main.Main;
//
//import java.io.IOException;
//
///**
// * Created by Sorumi on 16/11/17.
// */
//public class OrderListController {
//
//    @FXML
//    private VBox contentVBox;
//
//    @FXML
//    private StatusButton allButton;
//
//    @FXML
//    private Button detailButton;
//
//    /**
//     * Initializes the controller class. This method is automatically called
//     * after the fxml file has been loaded.
//     */
//    @FXML
//    public void initialize() {
//
////        System.out.print(contentVBox == null);
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("../view/order/OrderSearchPane.fxml"));
//            VBox pane = loader.load();
//
//            contentVBox.getChildren().add(pane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addOrder(int num) {
//        try {
//            for (int i = 0; i < num; i++) {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(Main.class.getResource("../view/order/OrderCell.fxml"));
//                HBox pane = loader.load();
//
//                contentVBox.getChildren().add(pane);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    @FXML
//    public void clickNavButton() {
//        if (allButton.getIsActiveProperty().booleanValue()) {
//            allButton.setIsActiveProperty(false);
//        } else {
//            allButton.setIsActiveProperty(true);
//        }
//
//    }
//
//
//    @FXML
//    public void clickDetailButton() {
//        System.out.print("!!!!");
//    }
//}
