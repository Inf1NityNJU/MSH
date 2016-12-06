package ui.viewcontroller.utility;

import bl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import util.DateUtil;
import vo.ClientVO;
import vo.ClientVO_Register;

/**
 * Created by Kray on 2016/11/27.
 */
public class SignUpViewController {

//    private BorderPane rootPane;

    @FXML
    private CommonTextField accountText;

    @FXML
    private CommonTextField passwordText;

    @FXML
    private CommonTextField confirmPasswordText;

    private UtilityViewController utilityViewController;

    private UserBLService userBLService;

//    public SignUpViewController(BorderPane rootPane) {
//        this.rootPane = rootPane;
//    }

    public void setUtilityViewController(UtilityViewController utilityViewController) {
        this.utilityViewController = utilityViewController;
    }

    @FXML
    public void clickLoginButton() {
        utilityViewController.back();
        utilityViewController.showLogin();
    }

    @FXML
    public void clickSignUpButton() {
        System.out.println("SIGN UP NOW");
        userBLService = new BLFactoryImpl().getClientBLService();
        userBLService.add(new ClientVO_Register(new DateUtil("1996-04-25"), 0, "123", "",
                accountText.getText(), passwordText.getText()));
    }
}
