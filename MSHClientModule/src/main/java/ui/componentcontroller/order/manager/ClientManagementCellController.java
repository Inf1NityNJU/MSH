package ui.componentcontroller.order.manager;

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

    private ClientVO client;

    private ClientManagementListViewController clientManagementListViewController;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label clientIDLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private RectButton detailButton;

    @FXML
    private StateButton typeLabel;

    @FXML
    public void clickDetailButton() {
        System.out.println("Client Detail");
    }

    public void setClientManagementListViewController(ClientManagementListViewController clientManagementListViewController) {
        this.clientManagementListViewController = clientManagementListViewController;
    }

    public void setClient(ClientVO client) {
        this.client = client;

        clientNameLabel.setText(client.clientName);
        clientIDLabel.setText(client.clientID);
        userNameLabel.setText(client.account);

        if (client.enterprise.equals("")) {
            typeLabel.setText("普通会员");
            typeLabel.setColorProperty("12B7F3");
        } else {
            typeLabel.setText("企业会员");
            typeLabel.setColorProperty("BC52FD");
        }

    }
}
