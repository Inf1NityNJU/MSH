package ui.componentcontroller.promotion;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.salesman.WebPromotionListViewController;

/**
 * Created by vivian on 16/12/3.
 */
public class WebPromotionPagePaneController {
    private WebPromotionListViewController webPromotionListViewController;

    @FXML
    private PagePane pagePane;

    public void setWebPromotionListViewController(WebPromotionListViewController webPromotionListViewController) {
        this.webPromotionListViewController = webPromotionListViewController;
    }

    @FXML
    private void pageChanged() {
        webPromotionListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }
}
