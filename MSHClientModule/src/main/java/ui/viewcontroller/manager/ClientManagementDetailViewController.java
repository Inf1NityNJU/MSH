package ui.viewcontroller.manager;

import blimpl.userblimpl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import component.tinybutton.TinyButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ui.viewcontroller.common.MainUIController;
import vo.ClientVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class ClientManagementDetailViewController {

    protected ClientVO clientVO;

    protected ClientManagementViewController clientManagementViewController;
    protected MainUIController mainUIController;

    @FXML
    protected Label clientIDLabel;

    @FXML
    protected Label clientNameLabel;

    @FXML
    protected Label accountLabel;

    @FXML
    private TinyButton passwordButton;

    @FXML
    protected Label typeLabel;

    @FXML
    protected Label birthdayLabel;

    @FXML
    private Label contactInfoLabel;

    @FXML
    protected Label creditLabel;

    @FXML
    private TinyButton creditButton;

    @FXML
    protected Label currentLevelLabel;

    @FXML
    private Label nextLevelLabel;

    @FXML
    private Label deltaCreditLabel;

    @FXML
    protected RectButton actionButton;

    @FXML
    private HBox creditHBox;

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;

        actionButton.setText("编 辑");
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void showClient(ClientVO clientVO) {
        this.clientVO = clientVO;

        clientIDLabel.setText(clientVO.clientID);
        clientNameLabel.setText(clientVO.clientName);
        accountLabel.setText(clientVO.account);
        contactInfoLabel.setText(clientVO.contactInfo);

        if (clientVO.enterprise.equals("")) {
            typeLabel.setText("普通会员");
        } else {
            typeLabel.setText("企业会员 ( 所属企业: " + clientVO.enterprise + " )");
        }

        birthdayLabel.setText(clientVO.birthday.toString());
        creditLabel.setText(clientVO.credit + "");

        currentLevelLabel.setText("Lv." + clientVO.level);

        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        if(clientVO.level < userBLService.getAllLevel().size()){
            creditHBox.setVisible(true);

            userBLService = UserBLFactory.getUserBLServiceImpl_Client();
            nextLevelLabel.setText("Lv." + (clientVO.level + 1));
            int nextCredit = Integer.parseInt(userBLService.getLevel((clientVO.level + 1) + "").credit);
            deltaCreditLabel.setText((nextCredit - clientVO.credit) + "");
        }else{
            creditHBox.setVisible(false);
        }

    }

    public void clickBackButton() {
        clientManagementViewController.back();
        clientManagementViewController.getClientManagementListViewController().showClients(clientManagementViewController.getClientManagementListViewController().getType());
    }

    public void clickPasswordButton() {
        clientManagementViewController.resetPassword(clientVO.account, clientVO.clientID);
    }

    public void clickEditButton() {
        clientManagementViewController.editClientDetail(clientVO);
    }

    public void clickCreditButton() {
        clientManagementViewController.showCreditOfClient(clientVO.clientID);
    }
}
