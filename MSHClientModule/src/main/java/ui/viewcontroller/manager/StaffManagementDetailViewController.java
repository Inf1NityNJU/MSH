package ui.viewcontroller.manager;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.SalesmanVO;
import vo.StaffVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class StaffManagementDetailViewController {

    private WorkerManagementViewController workerManagementViewController;

    @FXML
    private Label staffIDLabel;

    @FXML
    private Label staffNameLabel;

    @FXML
    private RectButton passwordButton;

    @FXML
    private Label hotelNameLabel;


    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    public void showStaff(StaffVO staffVO){
        staffIDLabel.setText(staffVO.staffID);
        staffNameLabel.setText(staffVO.staffName);
        hotelNameLabel.setText(staffVO.hotelID);
    }

    public void clickBackButton() {
        workerManagementViewController.back();
    }

    //TODO
    public void clickPasswordButton() {
        System.out.println("CHANGE PW");
    }

    //TODO
    public void clickEditButton() {
        System.out.println("EDIT BUTTON");
    }
}
