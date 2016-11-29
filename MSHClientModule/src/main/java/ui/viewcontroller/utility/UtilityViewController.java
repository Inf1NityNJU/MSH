package ui.viewcontroller.utility;

import javafx.scene.layout.BorderPane;

/**
 * Created by Kray on 2016/11/27.
 */
public class UtilityViewController {

    private BorderPane rootPane;

    private LoginViewController loginViewController;

    private SignupViewController signupViewController;

    public UtilityViewController(BorderPane rootPane) {
        this.rootPane = rootPane;

        loginViewController = new LoginViewController(rootPane);
        signupViewController = new SignupViewController(rootPane);

//        showLogin();
        loginViewController.showLogin();
    }

    public void showLogin(){
        loginViewController.showLogin();
    }

    public void showSignup(){

    }
}
