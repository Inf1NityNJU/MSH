package ui.viewcontroller.utility;

import bl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/27.
 */
public class UtilityViewController {

    private BorderPane rootPane;

    private MainUIController mainUIController;

    private LoginViewController loginViewController;
    private SignUpViewController signUpViewController;

    private UserBLService userBLService;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public UtilityViewController(BorderPane rootPane) {
        this.rootPane = rootPane;
        userBLService = new BLFactoryImpl().getClientBLService();
        showLogin();
//        showSignUp();
    }

    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            rootPane.setCenter(node);
        }
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void showLogin() {
        if (initNode != null) {
            stack.clear();
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../view/utility/LoginView.fxml"));
            Pane list = fxmlLoader.load();

            loginViewController = fxmlLoader.getController();
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

    public void showSignUp() {
        if (initNode != null) {
            stack.clear();
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../view/utility/SignUpView.fxml"));
            Pane list = fxmlLoader.load();

            signUpViewController = fxmlLoader.getController();
            signUpViewController.setUtilityViewController(this);

            rootPane.setTop(null);
            rootPane.setLeft(null);
            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void login(String account, String password){
        switch (userBLService.login(account, password)) {
            case LOGIN_SUCCESS_Client:
                showClientView();
                break;
            case LOGIN_SUCCESS_Staff:
                showStaffView();
                break;
            case LOGIN_SUCCESS_Salesman:
                showSalesmanView();
                break;
            case LOGIN_SUCCESS_Manager:
                showManagerView();
                break;
            case LOGIN_FAIL:
                break;
        }
    }

    public UserBLService getUserBLService() {
        return userBLService;
    }
}
