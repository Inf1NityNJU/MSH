package ui.viewcontroller.utility;

import bl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLService;
import component.commonpasswordfield.CommonPasswordField;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import util.DateUtil;
import util.Encryptor;
import util.ResultMessage;
import vo.ClientVO;
import vo.ClientVO_Register;

import java.time.LocalDate;

/**
 * Created by Kray on 2016/11/27.
 */
public class SignUpViewController {

    @FXML
    private ImageView bgImageView;

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
    private Label enterpriseTextLabel;

    @FXML
    private MyDatePicker birthdayPicker;

    @FXML
    private CommonTextField enterpriseText;

    @FXML
    private Label alertLabel;

    private int isEnterprise = 0;

    private UtilityViewController utilityViewController;

    private UserBLService userBLService;

    public void setUtilityViewController(UtilityViewController utilityViewController) {
        this.utilityViewController = utilityViewController;
    }

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResource("/images/hotel/2.png").toExternalForm());
        bgImageView.setImage(image);
        alertLabel.setText("");
        birthdayPicker.setDate(LocalDate.now());
    }

    @FXML
    public void clickNormalLabel() {
        isEnterprise = 0;

        normalLabel.setTextFill(Color.web("#00cccc"));
        enterpriseLabel.setTextFill(Color.web("#cccccc"));
        enterpriseTextLabel.setVisible(false);
        enterpriseTextLabel.setManaged(false);
        enterpriseText.setVisible(false);
        enterpriseText.setManaged(false);
    }

    @FXML
    public void clickEnterpriseLabel() {
        isEnterprise = 1;

        normalLabel.setTextFill(Color.web("#cccccc"));
        enterpriseLabel.setTextFill(Color.web("#00cccc"));
        enterpriseTextLabel.setVisible(true);
        enterpriseTextLabel.setManaged(true);
        enterpriseText.setVisible(true);
        enterpriseText.setManaged(true);
    }

    @FXML
    public void clickLoginButton() {
        utilityViewController.back();
        utilityViewController.showLogin();
    }

    @FXML
    public void clickSignUpButton() {

        if (accountText.getText().equals("")) {
            alertLabel.setText("请输入账号！");
            return;
        } else if (passwordText.getText().equals("")) {
            alertLabel.setText("请输入密码！");
            return;
        } else if (confirmPasswordText.getText().equals("")) {
            alertLabel.setText("请再次输入密码！");
            return;
        }

        if (passwordText.getText().equals(confirmPasswordText.getText())) {
            userBLService = utilityViewController.getUserBLService();

            DateUtil birthday = new DateUtil(birthdayPicker.getDate().getYear(),
                    birthdayPicker.getDate().getMonthValue(), birthdayPicker.getDate().getDayOfMonth());

            ResultMessage rm = userBLService.add(new ClientVO_Register(
                    birthday,
                    isEnterprise,
                    isEnterprise == 1 ? enterpriseText.getText() : "",
                    accountText.getText(),
                    Encryptor.encrypt(passwordText.getText())
            ));

            if (rm == ResultMessage.SUCCESS) {
                utilityViewController.login(accountText.getText(), Encryptor.encrypt(passwordText.getText()));
            } else {
                alertLabel.setText("该账号已存在！");
            }

        } else {
            alertLabel.setText("两次输入密码不一致！");
        }
    }
}
