package ui.viewcontroller.manager;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.UserBLFactory;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.rectbutton.RectButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import vo.StaffVO;

import java.io.IOException;

/**
 * Created by Kray on 2016/11/26.
 */
public class StaffManagementDetailEditViewController {

    private StaffVO staffVO;

    private WorkerManagementViewController workerManagementViewController;
    private MainUIController mainUIController;

    @FXML
    private Label accountLabel;

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

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void clickBackButton() {
        workerManagementViewController.back();
        workerManagementViewController.back();
        workerManagementViewController.showStaffDetail(staffVO);
    }

    public void clickSaveButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();

            alertViewController.setInfoLabel("确定保存信息吗？");
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    sureSave();
                }
            });
            alertViewController.setOnClickCancelButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    cancelSave();
                }
            });
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStaffEdit(StaffVO staffVO) {
        this.staffVO = staffVO;

        accountLabel.setText(staffVO.account);
        staffIDLabel.setText(staffVO.staffID);
        staffNameText.setText(staffVO.staffName);

        HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
        hotelNameLabel.setText(hotelBLService.getHotel(staffVO.hotelID).name);
    }

    private void sureSave() {
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        staffVO.staffName = staffNameText.getText();
        userBLService.update(staffVO);

        this.clickBackButton();
        mainUIController.hidePop();
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }
}
