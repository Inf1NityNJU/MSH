package ui.viewcontroller.manager;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.UserBLFactory;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.StaffVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class StaffManagementDetailViewController {

    private StaffVO staffVO;

    private WorkerManagementViewController workerManagementViewController;

    @FXML
    private Label accountLabel;

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

        accountLabel.setText(staffVO.account);
        staffIDLabel.setText(staffVO.staffID);
        staffNameLabel.setText(staffVO.staffName);

        HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();

        if (staffVO.hotelID != null) {
            hotelNameLabel.setText(hotelBLService.getHotel(staffVO.hotelID).name);
        } else {
            hotelNameLabel.setText("无");
        }

    }

    public void clickBackButton() {
        workerManagementViewController.back();
        workerManagementViewController.getWorkerManagementListViewController().refreshList();
    }

    public void clickPasswordButton() {
        workerManagementViewController.resetPassword(staffVO.account, staffVO.staffID);
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
