package ui.viewcontroller.utility;

import bl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLService;
import component.commonpasswordfield.CommonPasswordField;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import util.DateUtil;
import util.ResultMessage;
import vo.ClientVO;
import vo.ClientVO_Register;

/**
 * Created by Kray on 2016/11/27.
 * 
 */
public class SignUpViewController {

    @FXML
    private Label normalLabel;

    @FXML
    private Label enterpriseLabel;

    @FXML
    private CommonTextField accountText;

    @FXML
    private CommonPasswordField passwordText;

    @FXML
    private CommonPasswordField confirmPasswordText;

    @FXML
    private MyDatePicker birthdayPicker;

    private int isEnterprise = 0;

    private UtilityViewController utilityViewController;

    private UserBLService userBLService;

    public void setUtilityViewController(UtilityViewController utilityViewController) {
        this.utilityViewController = utilityViewController;
    }

    @FXML
    public void clickNormalLabel(){
        isEnterprise = 0;
    }

    @FXML
    public void clickEnterpriseLabel(){
        isEnterprise = 1;
    }

    @FXML
    public void clickLoginButton() {
        utilityViewController.back();
        utilityViewController.showLogin();
    }

    @FXML
    public void clickSignUpButton() {
        if (passwordText.getText().equals(confirmPasswordText.getText())) {

            DateUtil birthday = new DateUtil(birthdayPicker.getDate().getYear(),
                    birthdayPicker.getDate().getMonthValue(), birthdayPicker.getDate().getDayOfMonth());

            userBLService = utilityViewController.getUserBLService();
            if (userBLService.add(new ClientVO_Register(birthday, isEnterprise, "",
                    accountText.getText(), passwordText.getText())) == ResultMessage.SUCCESS) {
                utilityViewController.login(accountText.getText(), passwordText.getText());
            }

        } else {
            System.out.println("NOT SAME PASSWORD");
        }
    }
}
