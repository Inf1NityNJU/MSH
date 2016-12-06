package ui.viewcontroller.client;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.viewcontroller.manager.ClientManagementListViewController;
import ui.viewcontroller.manager.ClientManagementViewController;
import util.DateUtil;
import vo.ClientVO;

import java.time.LocalDate;

/**
 * Created by Kray on 2016/11/27.
 */
public class ClientDetailEditViewController {

    private ClientManagementViewController clientManagementViewController;
    private ClientManagementListViewController clientManagementListViewController;

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
    private CommonTextField enterpriseText;

    @FXML
    private MyDatePicker birthdayPicker;

    @FXML
    private Label creditLabel;

    @FXML
    private RectButton creditButton;

    @FXML
    private Label levelLabel;

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;
    }

    public void showClientEdit(ClientVO clientVO) {
        this.clientVO = clientVO;

        clientIDLabel.setText(clientVO.clientID);
        clientNameText.setText(clientVO.clientName);
        accountLabel.setText(clientVO.account);
        if (clientVO.enterprise.equals("")) {
            enterpriseButton.setIsActiveProperty(false);
            normalButton.setIsActiveProperty(true);

            enterpriseText.setVisible(false);
        } else {
            enterpriseButton.setIsActiveProperty(true);
            normalButton.setIsActiveProperty(false);

            enterpriseText.setVisible(true);
            enterpriseText.setText(clientVO.enterprise);
        }

        levelLabel.setText("Lv." + clientVO.level);
        creditLabel.setText(clientVO.credit + "");
    }

    public void clickNormalButton() {
        normalButton.setIsActiveProperty(true);
        enterpriseButton.setIsActiveProperty(false);

        enterpriseText.setVisible(false);
    }

    public void clickEnterpriseButton() {
        normalButton.setIsActiveProperty(false);
        enterpriseButton.setIsActiveProperty(true);

        enterpriseText.setVisible(true);
    }

    public void clickBackButton() {
        clientManagementViewController.back();
        clientManagementViewController.back();
        clientManagementViewController.showClientDetail(clientVO);
    }

    public void clickSaveButton() {
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        clientVO.clientName = clientNameText.getText();
        DateUtil birthday = new DateUtil(birthdayPicker.getDate().getYear(),
                birthdayPicker.getDate().getMonthValue(), birthdayPicker.getDate().getDayOfMonth());
        clientVO.birthday = birthday;

        if (normalButton.getIsActiveProperty()) {
            clientVO.enterprise = "";
        } else {
            clientVO.enterprise = enterpriseText.getText();
        }

        userBLService.update(clientVO);

        this.clickBackButton();
    }

}
