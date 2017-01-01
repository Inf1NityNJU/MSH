package ui.componentcontroller.order;

import component.statebutton.StateButton;
import component.tinybutton.TinyButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientHotelDetailViewController;
import vo.OrderVO;

/**
 * Created by Sorumi on 17/1/1.
 */
public class ClientHotelOrderCellController {

    @FXML
    private Label orderIDLabel;

    @FXML
    private StateButton stateLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TinyButton detailButton;

    private ClientHotelDetailViewController clientHotelDetailViewController;
    private OrderVO order;

    public void setClientHotelDetailViewController(ClientHotelDetailViewController clientHotelDetailViewController) {
        this.clientHotelDetailViewController = clientHotelDetailViewController;
    }

    public void setOrder(OrderVO order) {
        this.order = order;

        orderIDLabel.setText(order.orderID);
        stateLabel.setText(order.state.getName());
        stateLabel.setColorProperty(order.state.getColor());
        dateLabel.setText(order.checkInDate.toString() + " - " +order.checkOutDate.toString());
    }

    @FXML
    public void clickDetailButton() {
        clientHotelDetailViewController.showClientOrderDetail(order);
    }
}
