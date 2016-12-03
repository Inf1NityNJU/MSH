package ui.componentcontroller.user;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.manager.ClientManagementListViewController;

/**
 * Created by Kray on 2016/12/3.
 */
public class ClientManagementPaneController {

    private ClientManagementListViewController clientManagementListViewController;

    public void setClientManagementListViewController(ClientManagementListViewController clientManagementListViewController) {
        this.clientManagementListViewController = clientManagementListViewController;
    }

    @FXML
    private PagePane pagePane;

    @FXML
    private void pageChanged() {
        clientManagementListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }
}
