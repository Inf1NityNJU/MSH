package ui.viewcontroller.manager;

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

    private String ID;

    @FXML
    private Label IDLabel;

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

    public void setID(String ID) {
        this.ID = ID;

        IDLabel.setText(ID);
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

    }

}
