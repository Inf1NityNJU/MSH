package ui.viewcontroller.utility;

import component.commonpasswordfield.CommonPasswordField;
import component.commontextfield.CommonTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Encryptor;

/**
 * Created by Kray on 2016/11/27.
 */
public class LoginViewController {

    @FXML
    private ImageView bgImageView;

    @FXML
    private CommonTextField accountText;

    @FXML
    private CommonPasswordField passwordText;

    @FXML
    private Label alertLabel;

    private UtilityViewController utilityViewController;

    public void setUtilityViewController(UtilityViewController utilityViewController) {
        this.utilityViewController = utilityViewController;
    }

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResource("/images/hotel/2.png").toExternalForm());
        bgImageView.setImage(image);
        alertLabel.setText("");
    }

    @FXML
    public void clickLoginButton() {
        if (accountText.getText().equals("")) {
            showAlertView("请输入账号！");
            return;
        } else if (passwordText.getText().equals("")) {
            showAlertView("请输入密码！");
            return;
        }
        utilityViewController.login(accountText.getText(), Encryptor.encrypt(passwordText.getText()));

    }

    @FXML
    public void clickSignUpButton() {
        utilityViewController.back();
        utilityViewController.showSignUp();
    }

    public void showAlertView(String text) {
        alertLabel.setText(text);
    }

    @FXML
    public void clickTouristButton() {
        //TODO
        utilityViewController.showClientView();
    }
}
