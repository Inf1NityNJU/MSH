package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
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
    private CommonTextField staffNameText;

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
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        staffVO.staffName = staffNameText.getText();
        userBLService.update(staffVO);

        this.clickBackButton();
    }

    public void showStaffEdit(StaffVO staffVO) {
        this.staffVO = staffVO;

        staffIDLabel.setText(staffVO.staffID);
        staffNameText.setText(staffVO.staffName);
        hotelNameLabel.setText(staffVO.hotelID);
    }

}
