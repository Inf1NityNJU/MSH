package ui.viewcontroller.manager;

import blimpl.userblimpl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import ui.componentcontroller.common.AlertViewController;
import util.CreditAction;
import util.DateUtil;
import vo.ClientVO;
import vo.CreditChangeInfoVO;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Kray on 2016/12/6.
 */
public class ClientManagementCreditDetailViewController extends ClientManagementDetailViewController {

    @FXML
    private CommonTextField creditText;

    public void setClientManagementViewController(ui.viewcontroller.manager.ClientManagementViewController clientManagementViewController) {
        super.clientManagementViewController = clientManagementViewController;

        actionButton.setText("确 认");
    }

    public void showClient(ClientVO clientVO) {
        super.clientVO = clientVO;

        clientIDLabel.setText(clientVO.clientID);
        clientNameLabel.setText(clientVO.clientName);
        accountLabel.setText(clientVO.account);

        if (clientVO.enterprise.equals("")) {
            typeLabel.setText("普通会员");
        } else {
            typeLabel.setText("企业会员 ( 所属企业: " + clientVO.enterprise + " )");
        }

        birthdayLabel.setText(clientVO.birthday.toString());
        creditLabel.setText(clientVO.credit + "");

        currentLevelLabel.setText("Lv." + clientVO.level);
    }

    public void clickEditButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/common/AlertView.fxml"));
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

    private void sureSave() {
        int credit = Integer.parseInt(creditText.getText()) * 100;
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        userBLService.addCreditRecord(clientVO.clientID, new CreditChangeInfoVO(credit, CreditAction.RECHARGE_CREDIT,
                "-" + new Date().getTime(), new DateUtil()));

        super.clickBackButton();
        mainUIController.hidePop();
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }

}
