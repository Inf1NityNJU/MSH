package ui.componentcontroller.hotel;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientHotelListViewController;
import ui.viewcontroller.manager.HotelManagementListViewController;

/**
 * Created by Sorumi on 16/12/3.
 */
public class ManagerHotelPagePaneController {
    private HotelManagementListViewController hotelManagementListViewController;

    @FXML
    private PagePane pagePane;


    public void setHotelManagementListViewController(HotelManagementListViewController hotelManagementListViewController) {
        this.hotelManagementListViewController = hotelManagementListViewController;
    }

    @FXML
    private void pageChanged() {
        hotelManagementListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }
}
