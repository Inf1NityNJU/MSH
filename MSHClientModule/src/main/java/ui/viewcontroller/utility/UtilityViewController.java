package ui.viewcontroller.utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;

/**
 * Created by Kray on 2016/11/27.
 */
public class UtilityViewController {

    private BorderPane rootPane;

    private MainUIController mainUIController;

    private LoginViewController loginViewController;

    private SignupViewController signupViewController;

    public UtilityViewController(BorderPane rootPane) {
        this.rootPane = rootPane;

//        loginViewController = new LoginViewController(rootPane);
//        signupViewController = new SignupViewController(rootPane);

        showLogin();
//        loginViewController.showLogin();
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void showLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../view/utility/LoginView.fxml"));
            Pane list = fxmlLoader.load();

            LoginViewController loginViewController = fxmlLoader.getController();
            loginViewController.setUtilityViewController(this);

            rootPane.setTop(null);
            rootPane.setLeft(null);
            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void showClientView(){
        mainUIController.showMainView();
        mainUIController.showClientView();
    }

    protected void showStaffView(){
        mainUIController.showMainView();
        mainUIController.showStaffView();
    }

    protected void showSalesmanView(){
        mainUIController.showMainView();
        mainUIController.showSalesmanView();
    }

    protected void showManagerView(){
        mainUIController.showMainView();
        mainUIController.showManagerView();
    }

    public void showSignup() {

    }
}
