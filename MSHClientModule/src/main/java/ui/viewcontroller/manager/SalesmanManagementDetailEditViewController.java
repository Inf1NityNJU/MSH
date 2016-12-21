package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.rectbutton.RectButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import vo.SalesmanVO;

import java.io.IOException;

/**
 * Created by Kray on 2016/11/26.
 */
public class SalesmanManagementDetailEditViewController {

    private SalesmanVO salesmanVO;

    private WorkerManagementViewController workerManagementViewController;
    private MainUIController mainUIController;

    @FXML
    private Label accountLabel;

    @FXML
    private Label salesmanIDLabel;

    @FXML
    private CommonTextField salesmanNameText;

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
        workerManagementViewController.showSalesmanDetail(salesmanVO);
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

    public void showSalesmanEdit(SalesmanVO salesmanVO) {
        this.salesmanVO = salesmanVO;

        accountLabel.setText(salesmanVO.account);
        salesmanIDLabel.setText(salesmanVO.salesmanID);
        salesmanNameText.setText(salesmanVO.salesmanName);
    }

    private void sureSave() {
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        salesmanVO.salesmanName = salesmanNameText.getText();
        userBLService.update(salesmanVO);

        this.clickBackButton();
        mainUIController.hidePop();
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }
}
