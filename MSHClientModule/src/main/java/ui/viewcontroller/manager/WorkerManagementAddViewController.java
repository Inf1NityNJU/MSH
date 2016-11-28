package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import po.StaffPO;
import vo.SalesmanVO;
import vo.SalesmanVO_Register;
import vo.StaffVO;
import vo.StaffVO_Register;

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
    private Label hotelLabel;

    @FXML
    private ChoiceBox hotelChoiceBox;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;

        hotelLabel.setVisible(false);
        hotelChoiceBox.setVisible(false);
    }

    @FXML
    public void clickBackButton() {
        workerManagementViewController.back();
    }

    @FXML
    public void clickSaveButton() {
        if (staffButton.getIsActiveProperty()) {
            //存酒店工作人员
            if (accountText.getText().equals("") || hotelChoiceBox.getValue() == null) {
                System.out.println("Not complete");
            } else {
                UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
                userBLService.add(new StaffVO_Register(nameText.getText(), hotelChoiceBox.getValue().toString(),
                        accountText.getText(), passwordText.getText()));

                clickBackButton();
            }
        } else {
            //存网站营销人员
            if (accountText.getText().equals("")) {
                System.out.println("Not complete");
            } else {
                UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
                userBLService.add(new SalesmanVO_Register(nameText.getText(), accountText.getText(), passwordText.getText()));

                clickBackButton();
            }
        }
    }

    @FXML
    public void clickStaffButton() {
        staffButton.setIsActiveProperty(true);
        salesmanButton.setIsActiveProperty(false);

        hotelLabel.setVisible(true);
        hotelChoiceBox.setVisible(true);
    }

    @FXML
    public void clickSalesmanButton() {
        staffButton.setIsActiveProperty(false);
        salesmanButton.setIsActiveProperty(true);

        hotelLabel.setVisible(false);
        hotelChoiceBox.setVisible(false);
    }
}
