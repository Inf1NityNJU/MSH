package ui.componentcontroller.hotel;

import component.mydatepicker.MyDatePicker;
import component.radioboxpane.RadioBoxPane;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientHotelListViewController;
import ui.viewcontroller.client.ClientOrderListViewController;
import util.RoomType;
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

    @FXML
    private RadioBoxPane roomTypePane;

    @FXML
    private RadioBoxPane starPane;

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

        String roomTypeName = roomTypePane.getText();
        RoomType roomType = RoomType.getRoomTypeByName(roomTypeName);

        String starName = starPane.getText();
        //如果为nul则为没有选择任何房间类型或星级


        // TODO: 2016/12/5
        FilterFlagsVO flags = new FilterFlagsVO(null, null, "te", null, 0, 0, null, null, 0, -1, 0, 0, null);
        clientHotelListViewController.showHotel(flags);
    }
}
