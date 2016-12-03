package ui.componentcontroller.order;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.staff.HotelOrderListViewController;

/**
 * Created by Sorumi on 16/12/3.
 */
public class HotelOrderPagePaneController {

    private HotelOrderListViewController hotelOrderListViewController;

    @FXML
    private PagePane pagePane;

    public void setHotelOrderListViewController(HotelOrderListViewController hotelOrderListViewController) {
        this.hotelOrderListViewController = hotelOrderListViewController;
    }

    @FXML
    private void pageChanged() {
        hotelOrderListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }

}
