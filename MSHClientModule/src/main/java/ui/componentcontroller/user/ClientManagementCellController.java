package ui.componentcontroller.user;

import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.manager.ClientManagementListViewController;
import vo.ClientVO;

/**
 * Created by Kray on 2016/11/25.
 */
public class ClientManagementCellController {

    private ClientVO clientVO;

    private ClientManagementListViewController clientManagementListViewController;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label clientIDLabel;

    @FXML
    private Label accountLabel;

    @FXML
    private RectButton detailButton;

    @FXML
    private StateButton typeLabel;

    @FXML
    public void clickDetailButton() {
        clientManagementListViewController.showClientDetail(clientVO);
    }

    public void setClientManagementListViewController(ClientManagementListViewController clientManagementListViewController) {
        this.clientManagementListViewController = clientManagementListViewController;
    }

    public void setClientVO(ClientVO clientVO) {
        this.clientVO = clientVO;

        clientNameLabel.setText(clientVO.clientName);
        clientIDLabel.setText(clientVO.clientID);
        accountLabel.setText(clientVO.account);

        if (clientVO.enterprise.equals("")) {
            typeLabel.setText("普通会员");
            typeLabel.setColorProperty("12B7F3");
        } else {
            typeLabel.setText("企业会员");
            typeLabel.setColorProperty("BC52FD");
        }

    }
}
