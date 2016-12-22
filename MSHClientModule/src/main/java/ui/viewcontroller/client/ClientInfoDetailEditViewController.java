package ui.viewcontroller.client;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userbl.UserBLFactory;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.DateUtil;
import util.ResultMessage;
import vo.ClientVO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Sorumi on 16/12/17.
 */
public class ClientInfoDetailEditViewController {

    private ClientInfoViewController clientInfoViewController;

    private ClientVO clientVO;

    @FXML
    private Label clientIDLabel;

    @FXML
    private CommonTextField clientNameText;

    @FXML
    private Label accountLabel;

    @FXML
    private StateButton normalButton;

    @FXML
    private StateButton enterpriseButton;

    @FXML
    private Label enterpriseLabel;

    @FXML
    private CommonTextField enterpriseText;

    @FXML
    private MyDatePicker birthdayPicker;

    @FXML
    private Label creditLabel;

    @FXML
    private Label levelLabel;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();

    private MainUIController mainUIController;

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void setClientInfoViewController(ClientInfoViewController clientInfoViewController) {
        this.clientInfoViewController = clientInfoViewController;
    }

    public void showClientEdit() {
        String clientID = userBLInfo.getCurrentClientID();
        clientVO = userBLInfo.getClientByID(clientID);

        clientIDLabel.setText(clientVO.clientID);
        clientNameText.setText(clientVO.clientName);
        accountLabel.setText(clientVO.account);
        birthdayPicker.setDate(LocalDate.parse(clientVO.birthday.toString()));

        if (clientVO.enterprise.equals("")) {
            enterpriseButton.setVisible(false);
            enterpriseButton.setManaged(false);

            enterpriseText.setVisible(false);
            enterpriseText.setManaged(false);

            enterpriseLabel.setVisible(false);
            enterpriseLabel.setManaged(false);

        } else {
            normalButton.setVisible(false);
            normalButton.setManaged(false);

            enterpriseText.setText(clientVO.enterprise);
        }

        levelLabel.setText("Lv." + clientVO.level);
        creditLabel.setText(clientVO.credit + "");
    }

    public void clickBackButton() {
        clientInfoViewController.back();
    }

    public void clickSaveButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();

            alertViewController.setInfoLabel("确定保存新的用户信息吗？");
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
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        clientVO.clientName = clientNameText.getText();

        if (normalButton.visibleProperty().getValue()) {
            clientVO.enterprise = "";
        } else {
            clientVO.enterprise = enterpriseText.getText();
        }

        DateUtil birthday = new DateUtil(birthdayPicker.getDate());
        clientVO.birthday = birthday;

        ResultMessage rm = userBLService.update(clientVO);

        if (rm == ResultMessage.SUCCESS) {
            clientInfoViewController.back();
            clientInfoViewController.showClientInfo();
        }

        mainUIController.hidePop();
        clientInfoViewController.back();
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }

}
