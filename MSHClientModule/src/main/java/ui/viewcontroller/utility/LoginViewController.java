package ui.viewcontroller.utility;

import blservice.userblservice.UserBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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

    private BorderPane rootPane;
    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    @FXML
    private TextField accountText;

    @FXML
    private TextField passwordText;

    private UserBLService userBLService;

    public LoginViewController(BorderPane rootPane) {
        System.out.println("C LOGIN");
        this.rootPane = rootPane;
    }

    public void showLogin(){
        System.out.println("INIT LOGIN");
        if (initNode != null) {
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../view/utility/LoginView.fxml"));
            BorderPane list = fxmlLoader.load();

            initNode = list;
//            stack.push(list);

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void clickLoginButton(){
        System.out.println("LOGIN");
    }
}
