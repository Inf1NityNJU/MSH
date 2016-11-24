package ui.componentcontroller.order.manager;

import component.statebutton.StateButton;
import javafx.fxml.FXML;
import ui.viewcontroller.manager.ClientManagementListViewController;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementListPaneController {

    private ClientManagementListViewController clientManagementListViewController;

    @FXML
    private StateButton allButton;

    public void setClientManagementListViewController(ClientManagementListViewController clientManagementListViewController) {
        this.clientManagementListViewController = clientManagementListViewController;
    }
}
