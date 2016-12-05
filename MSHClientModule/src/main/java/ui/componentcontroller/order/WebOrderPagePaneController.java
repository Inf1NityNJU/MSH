package ui.componentcontroller.order;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.salesman.WebOrderListViewController;

/**
 * Created by Sorumi on 16/12/3.
 */
public class WebOrderPagePaneController {

    private WebOrderListViewController webOrderListViewController;

    @FXML
    private PagePane pagePane;

    public void setWebOrderListViewController(WebOrderListViewController webOrderListViewController) {
        this.webOrderListViewController = webOrderListViewController;
    }

    @FXML
    private void pageChanged() {
        webOrderListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }

}
