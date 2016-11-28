package ui.componentcontroller.user;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.manager.WorkerManagementListViewController;
import vo.StaffVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class StaffManagementCellController {

    private StaffVO staffVO;

    private WorkerManagementListViewController workerManagementListViewController;

    @FXML
    private Label staffNameLabel;

    @FXML
    private Label staffIDLabel;

    @FXML
    private Label staffHotelLabel;

    @FXML
    private RectButton detailButton;

    @FXML
    public void clickDetailButton() {
        workerManagementListViewController.showStaffDetail(staffVO);
    }

    public void setWorkerManagementListViewController(WorkerManagementListViewController workerManagementListViewController) {
        this.workerManagementListViewController = workerManagementListViewController;
    }

    public void setStaffVO(StaffVO staffVO) {
        this.staffVO = staffVO;

        staffNameLabel.setText(staffVO.staffName);
        staffIDLabel.setText(staffVO.staffID);
        staffHotelLabel.setText(staffVO.hotelID);
    }
}
