package ui.componentcontroller.hotel;

import component.mydatepicker.MyDatePicker;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientHotelListViewController;
import ui.viewcontroller.client.ClientOrderListViewController;

import java.time.LocalDate;

/**
 * Created by Sorumi on 16/11/25.
 */
public class ClientHotelSearchPaneController {

    private ClientHotelListViewController clientHotelListViewController;

    @FXML
    private MyDatePicker checkInDatePicker;

    @FXML
    private MyDatePicker checkOutDatePicker;

    public void setClientHotelListViewController(ClientHotelListViewController clientHotelListViewController) {
        this.clientHotelListViewController = clientHotelListViewController;
    }

    @FXML
    public void initialize() {
        checkInDatePicker.setDate(LocalDate.now());
        checkOutDatePicker.setDate(LocalDate.now().plusDays(1));
    }
}
