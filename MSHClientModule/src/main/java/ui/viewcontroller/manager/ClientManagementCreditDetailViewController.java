package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import javafx.fxml.FXML;
import util.CreditAction;
import util.DateUtil;
import vo.ClientVO;
import vo.CreditVO;

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
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        userBLService.addCreditRecord(clientVO.clientID, new CreditVO(Integer.parseInt(creditText.getText()) * 100, 0,
                CreditAction.RECHARGE_CREDIT, "-" + new Date().getTime(), new DateUtil()));

        super.clickBackButton();
    }

}
