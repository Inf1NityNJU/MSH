package ui.componentcontroller.hotel;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientHotelListViewController;

/**
 * Created by Sorumi on 16/12/3.
 */
public class ClientHotelPagePaneController {
    private ClientHotelListViewController clientHotelListViewController;

    @FXML
    private PagePane pagePane;

    public void setClientHotelListViewController(ClientHotelListViewController clientHotelListViewController) {
        this.clientHotelListViewController = clientHotelListViewController;
    }

    @FXML
    private void pageChanged() {
        clientHotelListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }
}
