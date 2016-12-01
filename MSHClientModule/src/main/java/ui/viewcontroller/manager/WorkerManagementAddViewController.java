package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    private CommonTextField accountText;

    @FXML
    private CommonTextField passwordText;

    @FXML
    private CommonTextField checkPasswordText;

    @FXML
    private CommonTextField nameText;

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

    private ObservableList observableList;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;

        hotelLabel.setVisible(false);
        hotelChoiceBox.setVisible(false);

        observableList = FXCollections.observableArrayList();

        //TODO
        observableList.add("HOTEL1");
        observableList.add("HOTEL2");
        observableList.add("HOTEL3");

        hotelChoiceBox.setItems(observableList);
    }

    @FXML
    public void clickBackButton() {
        workerManagementViewController.back();
        workerManagementViewController.getWorkerManagementListViewController().showAllWorkers();
    }

    @FXML
    public void clickSaveButton() {
        if (staffButton.getIsActiveProperty()) {
            //存酒店工作人员
            if (accountText.getText().equals("") || nameText.getText().equals("")
                    || passwordText.getText().equals("") || checkPasswordText.getText().equals("")
                    || hotelChoiceBox.getValue() == null) {
                System.out.println("Not complete");
            } else {
                UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
                userBLService.add(new StaffVO_Register(nameText.getText(), hotelChoiceBox.getValue().toString(),
                        accountText.getText(), passwordText.getText()));

                clickBackButton();
            }
        } else if (salesmanButton.getIsActiveProperty()) {
            //存网站营销人员
            if (accountText.getText().equals("") || nameText.getText().equals("")
                    || passwordText.getText().equals("") || checkPasswordText.getText().equals("")) {
                System.out.println("Not complete");
            } else {
                UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
                userBLService.add(new SalesmanVO_Register(nameText.getText(), accountText.getText(), passwordText.getText()));

                clickBackButton();
            }
        } else {
            System.out.println("Not complete");
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
