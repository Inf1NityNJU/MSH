package ui.viewcontroller.client;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import component.tinybutton.TinyButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.manager.ClientManagementViewController;
import vo.ClientVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class ClientDetailViewController {

    private ClientVO clientVO;

    private ClientManagementViewController clientManagementViewController;

    @FXML
    private Label clientIDLabel;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label accountLabel;

    @FXML
    private TinyButton passwordButton;

    @FXML
    private Label typeLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label creditLabel;

    @FXML
    private TinyButton creditButton;

    @FXML
    private Label currentLevelLabel;

    @FXML
    private Label nextLevelLabel;

    @FXML
    private Label deltaCreditLabel;

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;
    }

    public void showClient(ClientVO clientVO) {
        this.clientVO = clientVO;

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
        nextLevelLabel.setText("Lv." + (clientVO.level+1));

        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        int nextCredit = Integer.parseInt(userBLService.getLevel((clientVO.level+1)+"").credit);
        deltaCreditLabel.setText((nextCredit - clientVO.credit) + "");

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

    public void clickCreditButton(){
        clientManagementViewController.showCreditOfClient(clientVO.clientID);
    }
}
