package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.SalesmanVO;
import vo.StaffVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class StaffManagementDetailViewController {

    private StaffVO staffVO;

    private WorkerManagementViewController workerManagementViewController;

    @FXML
    private Label staffIDLabel;

    @FXML
    private Label staffNameLabel;

    @FXML
    private RectButton passwordButton;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private RectButton backButton;

    @FXML
    private RectButton deleteButton;

    @FXML
    private RectButton editButton;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    public void showStaff(StaffVO staffVO) {
        this.staffVO = staffVO;
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

    public void clickDeleteButton() {
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        userBLService.delete(staffVO.staffID);

        clickBackButton();
    }

    public void clickEditButton() {
        workerManagementViewController.editStaffDetail(staffVO);
    }
}
