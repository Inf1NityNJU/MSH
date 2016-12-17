package ui.viewcontroller.client;

import bl.blfactory.BLFactoryImpl;
import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.manager.ClientManagementListViewController;
import ui.viewcontroller.manager.ClientManagementViewController;
import util.DateUtil;
import vo.ClientVO;

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
    private Label typeHintLabel;

    @FXML
    private CommonTextField enterpriseText;

    @FXML
    private MyDatePicker birthdayPicker;

    @FXML
    private Label creditLabel;

    @FXML
    private Label levelLabel;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();

    public void setClientInfoViewController(ClientInfoViewController clientInfoViewController) {
        this.clientInfoViewController = clientInfoViewController;
    }

    public void showClientEdit() {
        String clientID = userBLInfo.getCurrentClientID();
        clientVO = userBLInfo.getClientByID(clientID);

        clientIDLabel.setText(clientVO.clientID);
        clientNameText.setText(clientVO.clientName);
        accountLabel.setText(clientVO.account);
        if (clientVO.enterprise.equals("")) {
            enterpriseButton.setVisible(false);
            enterpriseButton.setManaged(false);

            enterpriseText.setVisible(false);
            enterpriseText.setManaged(false);

            typeHintLabel.setText("生日");
            birthdayPicker.setDate(LocalDate.parse(clientVO.birthday.toString()));

        } else {
            normalButton.setVisible(false);
            normalButton.setManaged(false);

            birthdayPicker.setVisible(false);
            birthdayPicker.setManaged(false);

            typeHintLabel.setText("企业名称");
            enterpriseText.setText(clientVO.enterprise);
        }

        levelLabel.setText("Lv." + clientVO.level);
        creditLabel.setText(clientVO.credit + "");
    }

    public void clickBackButton() {
        clientInfoViewController.back();
//        clientInfoViewController.showClientInfo();
    }

    public void clickSaveButton() {
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        clientVO.clientName = clientNameText.getText();

        if (normalButton.visibleProperty().getValue()) {
            clientVO.enterprise = "";
            DateUtil birthday = new DateUtil(birthdayPicker.getDate());
            clientVO.birthday = birthday;

        } else {
            clientVO.enterprise = enterpriseText.getText();
        }

        userBLService.update(clientVO);

        clientInfoViewController.back();
        clientInfoViewController.showClientInfo();
    }

}
