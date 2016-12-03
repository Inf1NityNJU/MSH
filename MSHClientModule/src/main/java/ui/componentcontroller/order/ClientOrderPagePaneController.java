package ui.componentcontroller.order;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientOrderListViewController;

/**
 * Created by Sorumi on 16/12/3.
 */
public class ClientOrderPagePaneController {

    private ClientOrderListViewController clientOrderListViewController;

    @FXML
    private PagePane pagePane;

    public void setClientOrderListViewController(ClientOrderListViewController clientOrderListViewController) {
        this.clientOrderListViewController = clientOrderListViewController;
    }

    @FXML
    private void pageChanged() {
        clientOrderListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }

}
