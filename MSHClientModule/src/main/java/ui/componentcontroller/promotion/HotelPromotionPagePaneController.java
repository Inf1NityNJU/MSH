package ui.componentcontroller.promotion;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.staff.HotelPromotionListViewController;

/**
 * Created by vivian on 16/12/8.
 */
public class HotelPromotionPagePaneController {
    private HotelPromotionListViewController hotelPromotionListViewController;

    @FXML
    private PagePane pagePane;

    public void setHotelPromotionListViewController(HotelPromotionListViewController hotelPromotionListViewController) {
        this.hotelPromotionListViewController = hotelPromotionListViewController;
    }

    @FXML
    private void pageChanged() {
        hotelPromotionListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }
}
