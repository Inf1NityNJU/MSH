package ui.componentcontroller.user;

import component.statebutton.StateButton;
import javafx.fxml.FXML;
import ui.viewcontroller.manager.ClientManagementListViewController;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementSearchPaneController {

    private ClientManagementListViewController clientManagementListViewController;

    @FXML
    private StateButton allButton;

    @FXML
    private StateButton normalClientButton;

    @FXML
    private StateButton enterpriseClientButton;

    private StateButton[] buttons;

    public void setClientManagementListViewController(ClientManagementListViewController clientManagementListViewController) {
        this.clientManagementListViewController = clientManagementListViewController;

        buttons = new StateButton[]{allButton, normalClientButton, enterpriseClientButton};
    }

    @FXML
    public void showAllClients() {
        setButtonsInactive();
        allButton.setIsActiveProperty(true);
        clientManagementListViewController.showClients(-1);
    }

    @FXML
    public void showNormalClients(){
        setButtonsInactive();
        normalClientButton.setIsActiveProperty(true);
        clientManagementListViewController.showClients(0);
    }

    @FXML
    public void showEnterpriseClients(){
        setButtonsInactive();
        enterpriseClientButton.setIsActiveProperty(true);
        clientManagementListViewController.showClients(1);
    }

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }

}
