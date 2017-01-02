package ui.viewcontroller.manager;

import blimpl.userblimpl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.commonpasswordfield.CommonPasswordField;
import component.rectbutton.RectButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.client.ClientInfoViewController;
import ui.viewcontroller.common.MainUIController;
import util.Encryptor;

import java.io.IOException;

/**
 * Created by Kray on 2016/11/28.
 */
public class ResetPasswordViewController {

    private ClientManagementViewController clientManagementViewController;
    private ClientInfoViewController clientInfoViewController;

    private WorkerManagementViewController workerManagementViewController;

    private String account;
    private String ID;

    private MainUIController mainUIController;

    @FXML
    private Label accountLabel;

    @FXML
    private CommonPasswordField oldPWText;

    @FXML
    private CommonPasswordField newPWText;

    @FXML
    private CommonPasswordField checkPWText;

    @FXML
    private RectButton backButton;

    @FXML
    private RectButton saveButton;

    @FXML
    private Label newAlertLabel;

    @FXML
    private Label checkAlertLabel;

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void setAccountAndID(String account, String ID) {
        this.account = account;
        this.ID = ID;

        accountLabel.setText(account);

        newAlertLabel.setVisible(false);
        checkAlertLabel.setVisible(false);
    }

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;
    }

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    public void setClientInfoViewController(ClientInfoViewController clientInfoViewController) {
        this.clientInfoViewController = clientInfoViewController;
    }

    public void clickBackButton() {
        if (clientInfoViewController != null) {
            clientInfoViewController.back();
        } else if (workerManagementViewController != null) {
            workerManagementViewController.back();
        } else if (clientManagementViewController != null) {
            clientManagementViewController.back();
        } else {
            return;
        }
    }

    public void clickSaveButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();

            alertViewController.setInfoLabel("确定更新密码吗？");
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    sureSave();
                }
            });
            alertViewController.setOnClickCancelButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    cancelSave();
                }
            });
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sureSave() {
        mainUIController.hidePop();

        UserBLService userBLService;
        if (ID.charAt(0) == '3') {
            userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
            userBLService.reset(account, Encryptor.encrypt(oldPWText.getText()), Encryptor.encrypt(newPWText.getText()));

            clickBackButton();
        } else if (ID.charAt(0) == '1') {
            userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
            userBLService.reset(account, Encryptor.encrypt(oldPWText.getText()), Encryptor.encrypt(newPWText.getText()));

            clickBackButton();
        } else {
            userBLService = UserBLFactory.getUserBLServiceImpl_Client();
            userBLService.reset(account, Encryptor.encrypt(oldPWText.getText()), Encryptor.encrypt(newPWText.getText()));

            clickBackButton();
        }
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }

    public void checkOldText() {
        if (newPWText.getText().equals(oldPWText.getText())) {
            newAlertLabel.setVisible(true);
        } else {
            newAlertLabel.setVisible(false);
        }
    }

    public void checkNewText() {
        if (newPWText.getText().equals(oldPWText.getText())) {
            newAlertLabel.setVisible(true);
        } else {
            newAlertLabel.setVisible(false);
        }
        if (!checkPWText.getText().equals(newPWText.getText())) {
            checkAlertLabel.setVisible(true);
        } else {
            checkAlertLabel.setVisible(false);
        }
    }

    public void checkCheckText() {
        if (!checkPWText.getText().equals(newPWText.getText())) {
            checkAlertLabel.setVisible(true);
        } else {
            checkAlertLabel.setVisible(false);
        }
    }

}
