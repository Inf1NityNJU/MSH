package main;
/**
 * Created by Sorumi on 16/11/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import network.UtilClientNetworkImpl;
import ui.viewcontroller.common.MainUIController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        UtilClientNetworkImpl.getUtilClientNetwork();

        FXMLLoader rootLoader = new FXMLLoader();
        rootLoader.setLocation(getClass().getResource("/view/common/Main.fxml"));
        Pane root = rootLoader.load();


        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("MSH");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        MainUIController controller = rootLoader.getController();
        controller.showUtilView();
    }
}
