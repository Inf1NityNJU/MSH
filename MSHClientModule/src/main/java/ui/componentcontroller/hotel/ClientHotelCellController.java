package ui.componentcontroller.hotel;

import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientHotelListViewController;
import vo.Hotel_BriefVO;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientHotelCellController {

    private ClientHotelListViewController clientHotelListViewController;
    private Hotel_BriefVO hotel;

    public void setClientHotelListViewController(ClientHotelListViewController clientHotelListViewController) {
        this.clientHotelListViewController = clientHotelListViewController;
    }

    // TODO
    public void setHotel(Hotel_BriefVO hotel) {
        this.hotel = hotel;

    }

    @FXML
    public void showHotelDetail() {
        clientHotelListViewController.showHotelDetail(hotel.ID);
    }
}
