package ui.viewcontroller.manager;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vo.StaffVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class StaffManagementDetailEditViewController {

    private StaffVO staffVO;

    private WorkerManagementViewController workerManagementViewController;

    @FXML
    private Label staffIDLabel;

    @FXML
    private TextField staffNameText;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    public void clickBackButton() {
        workerManagementViewController.back();
    }

    //TODO
    public void clickSaveButton() {

    }

    public void showStaffEdit(StaffVO staffVO) {
        this.staffVO = staffVO;

        staffIDLabel.setText(staffVO.staffID);
        staffNameText.setText(staffVO.staffName);
        hotelNameLabel.setText(staffVO.hotelID);
    }

}
