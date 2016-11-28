package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.DateUtil;
import vo.ClientVO;

/**
 * Created by Kray on 2016/11/27.
 */
public class ClientManagementDetailEditViewController {

    private ClientManagementViewController clientManagementViewController;
    private ClientManagementListViewController clientManagementListViewController;

    private ClientVO clientVO;

    @FXML
    private Label clientIDLabel;

    @FXML
    private TextField clientNameText;

    @FXML
    private TextField accountText;

    @FXML
    private StateButton normalButton;

    @FXML
    private StateButton enterpriseButton;

    @FXML
    private TextField birthdayText;

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
        accountText.setText(clientVO.account);
        birthdayText.setText(clientVO.birthday.toString());

        if (clientVO.enterprise.equals("")) {
            enterpriseButton.setIsActiveProperty(false);
            normalButton.setIsActiveProperty(true);
        } else {
            enterpriseButton.setIsActiveProperty(true);
            normalButton.setIsActiveProperty(false);
        }

        levelLabel.setText("Lv." + clientVO.level);

    }

    //TODO
    public void clickNormalButton(){
        normalButton.setIsActiveProperty(true);
        enterpriseButton.setIsActiveProperty(false);
    }

    //TODO
    public void clickEnterpriseButton(){
        normalButton.setIsActiveProperty(false);
        enterpriseButton.setIsActiveProperty(true);
    }

    public void clickBackButton() {
        clientManagementViewController.back();
    }

    public void clickSaveButton() {
        System.out.println("CLIENT SAVE");
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        clientVO.clientName = clientNameText.getText();
        clientVO.birthday = new DateUtil(birthdayText.getText());
        clientVO.account = accountText.getText();
        userBLService.update(clientVO);

        System.out.println(clientVO.account);

        this.clickBackButton();
    }

}
