package ui.componentcontroller.hotel;

import component.mydatepicker.MyDatePicker;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientHotelListViewController;
import ui.viewcontroller.client.ClientOrderListViewController;
import vo.FilterFlagsVO;

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

    @FXML
    public void searchHotel() {
        // TODO: 2016/12/5
        FilterFlagsVO flags = new FilterFlagsVO(null, null, "te", null, 0, 0, null, null, 0, -1, 0, 0, null);
        clientHotelListViewController.showHotel(flags);
    }
}
