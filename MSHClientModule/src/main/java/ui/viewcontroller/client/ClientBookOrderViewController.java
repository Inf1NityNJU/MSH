package ui.viewcontroller.client;

import javafx.fxml.FXML;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientBookOrderViewController {

    private ClientSearchHotelViewController clientSearchHotelViewController;

    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }


    public void setOrder(OrderVO order) {

    }

    @FXML
    private void clickBackButton() {
        clientSearchHotelViewController.back();
    }
}
