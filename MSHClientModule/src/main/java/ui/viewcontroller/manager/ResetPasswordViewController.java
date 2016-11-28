package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Kray on 2016/11/28.
 */
public class ResetPasswordViewController {

    private ClientManagementViewController clientManagementViewController;

    private WorkerManagementViewController workerManagementViewController;

    private String account;
    private String ID;

    @FXML
    private Label accountLabel;

    @FXML
    private CommonTextField oldPWText;

    @FXML
    private CommonTextField newPWText;

    @FXML
    private CommonTextField checkPWText;

    @FXML
    private RectButton backButton;

    @FXML
    private RectButton saveButton;

    public void setAccountAndID(String account, String ID) {
        this.account = account;
        this.ID = ID;

        accountLabel.setText(account);
    }

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;
    }

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    public void clickBackButton() {
        if (clientManagementViewController != null) {
            clientManagementViewController.back();
        } else if (workerManagementViewController != null) {
            workerManagementViewController.back();
        } else {
            return;
        }
    }

    public void clickSaveButton() {
        if (oldPWText.getText().equals(newPWText.getText())) {
            System.out.println("Old same as new");
            return;
        }
        if (!newPWText.getText().equals(checkPWText.getText())) {
            System.out.println("Not same password");
            return;
        }
        UserBLService userBLService;
        if (ID.charAt(0) == '3') {
            userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
            userBLService.reset(account, oldPWText.getText(), newPWText.getText());

            clickBackButton();
        } else if (ID.charAt(0) == '1') {
            userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
            userBLService.reset(account, oldPWText.getText(), newPWText.getText());

            clickBackButton();
        } else {
            userBLService = UserBLFactory.getUserBLServiceImpl_Client();
            userBLService.reset(account, oldPWText.getText(), newPWText.getText());

            clickBackButton();
        }
    }

}
