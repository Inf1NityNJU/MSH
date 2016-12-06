package ui.viewcontroller.utility;

import bl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import javafx.fxml.FXML;

/**
 * Created by Kray on 2016/11/27.
 */
public class LoginViewController {

    @FXML
    private CommonTextField accountText;

    @FXML
    private CommonTextField passwordText;

    private UtilityViewController utilityViewController;

    private UserBLService userBLService;

    public void setUtilityViewController(UtilityViewController utilityViewController) {
        this.utilityViewController = utilityViewController;
    }

    @FXML
    public void clickLoginButton() {
        userBLService = new BLFactoryImpl().getClientBLService();
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
            case LOGIN_SUCCESS_Manager:
                utilityViewController.showManagerView();
                break;
            case LOGIN_FAIL:
                break;
        }
    }

    @FXML
    public void clickSignupButton() {
        utilityViewController.back();
        utilityViewController.showSignUp();
    }
}
