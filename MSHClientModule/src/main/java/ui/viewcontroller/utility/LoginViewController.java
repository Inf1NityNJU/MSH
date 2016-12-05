package ui.viewcontroller.utility;

import bl.blfactory.BLFactoryImpl;
import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import util.LoginState;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/27.
 */
public class LoginViewController {

    @FXML
    private TextField accountText;

    @FXML
    private TextField passwordText;

    private UtilityViewController utilityViewController;

    private UserBLService userBLService;

    public void setUtilityViewController(UtilityViewController utilityViewController) {
        this.utilityViewController = utilityViewController;
    }

    @FXML
    public void clickLoginButton(){
        userBLService = new BLFactoryImpl().getClientBLService();
//        System.out.println("LOGIN");
//        System.out.println("ACCOUNT : " + accountText.getText());
//        System.out.println("PASSWORD: " + passwordText.getText());
        switch (userBLService.login(accountText.getText(), passwordText.getText())) {
            case LOGIN_SUCCESS_Client:
                utilityViewController.showClientView();
                break;
            case LOGIN_SUCCESS_Staff:
                utilityViewController.showStaffView();
                break;
            case LOGIN_SUCCESS_Salesman:
                utilityViewController.showSalesmanView();
                break;
            case LOGIN_FAIL:
                break;
        }
    }

    @FXML
    public void clickSignupButton(){

    }
}
