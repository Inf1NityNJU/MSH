package ui.viewcontroller.utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.Main;

import java.io.IOException;

/**
 * Created by Kray on 2016/11/27.
 */
public class UtilityViewController {

    private BorderPane rootPane;

    private LoginViewController loginViewController;

    private SignupViewController signupViewController;

    public UtilityViewController(BorderPane rootPane) {
        this.rootPane = rootPane;

//        loginViewController = new LoginViewController(rootPane);
//        signupViewController = new SignupViewController(rootPane);

        showLogin();
//        loginViewController.showLogin();
    }

    public void showLogin() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../view/utility/LoginView.fxml"));
            AnchorPane list = fxmlLoader.load();

            LoginViewController loginViewController = fxmlLoader.getController();
            loginViewController.setUtilityViewController(this);

            rootPane.setTop(null);
            rootPane.setLeft(null);
            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSignup() {

    }
}
