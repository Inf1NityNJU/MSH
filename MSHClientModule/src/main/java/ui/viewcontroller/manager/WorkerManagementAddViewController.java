package ui.viewcontroller.manager;

import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import po.StaffPO;

/**
 * Created by Kray on 2016/11/28.
 */
public class WorkerManagementAddViewController {

    private WorkerManagementViewController workerManagementViewController;

    @FXML
    private TextField accountText;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField checkPasswordText;

    @FXML
    private TextField nameText;

    @FXML
    private StateButton staffButton;

    @FXML
    private StateButton salesmanButton;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    @FXML
    public void clickBackButton() {
        workerManagementViewController.back();
    }

    @FXML
    public void clickSaveButton() {
        if (staffButton.getIsActiveProperty()) {
            //存酒店工作人员
        } else {
            //存网站营销人员
        }
    }

    @FXML
    public void clickStaffButton(){
        staffButton.setIsActiveProperty(true);
        salesmanButton.setIsActiveProperty(false);
    }

    @FXML
    public void clickSalesmanButton(){
        staffButton.setIsActiveProperty(false);
        salesmanButton.setIsActiveProperty(true);
    }
}
