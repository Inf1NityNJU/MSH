package ui.viewcontroller.client;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userbl.UserBLFactory;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import component.tinybutton.TinyButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import vo.ClientVO;

/**
 * Created by Sorumi on 16/12/17.
 */
public class ClientInfoDetailViewController {

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

    private ClientInfoViewController clientInfoViewController;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();

    public void setClientInfoViewController(ClientInfoViewController clientInfoViewController) {
        this.clientInfoViewController = clientInfoViewController;
    }

    public void showClient() {
        String clientID = userBLInfo.getCurrentClientID();
        ClientVO clientVO = userBLInfo.getClientByID(clientID);

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

    public void clickEditButton() {
        clientInfoViewController.editClientInfo();
    }

    public void clickCreditButton() {
        clientInfoViewController.showCredit();
    }

    public void clickPasswordButton() {
        String clientID = userBLInfo.getCurrentClientID();
        ClientVO clientVO = userBLInfo.getClientByID(clientID);

        clientInfoViewController.resetPassword(clientVO.account, clientVO.clientID);
    }
}
