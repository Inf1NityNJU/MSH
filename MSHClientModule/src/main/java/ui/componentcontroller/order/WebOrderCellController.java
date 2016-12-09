package ui.componentcontroller.order;

import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.salesman.WebOrderListViewController;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/18.
 */
public class WebOrderCellController {

    private WebOrderListViewController webOrderListViewController;
    private OrderVO order;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label orderDateLabel;

    @FXML
    private StateButton stateLabel;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label checkDateLabel;

    @FXML
    private Label roomLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label assessmentLabel;

    @FXML
    private RectButton detailButton;

    public void setWebOrderListViewController(WebOrderListViewController webOrderListViewController) {
        this.webOrderListViewController = webOrderListViewController;
    }

    public void setOrder(OrderVO order) {
        this.order = order;

        orderIDLabel.setText(order.orderID != null ? order.orderID : "") ;
        orderDateLabel.setText(order.bookedTime.toString());
        stateLabel.setText(order.state.getName());
        stateLabel.setColorProperty(order.state.getColor());
        hotelNameLabel.setText(order.hotelName);
        clientNameLabel.setText(order.clientName);
        checkDateLabel.setText(order.checkInDate.toString() + " - " +order.checkOutDate.toString());

        String roomText = "";

        for (OrderRoomVO room : order.rooms) {
            if (order.rooms.indexOf(room) > 0) {
                roomText = roomText + " ";
            }
            roomText = roomText + room.type.getName() + " × " + room.quantity;
        }
        roomLabel.setText(roomText);

        BillVO bill = order.bill;
        priceLabel.setText("¥ " + String.format("%.2f", bill.totalPrice));

        if (order.assessment == null) {
            assessmentLabel.setText("未评价");
        } else {
            assessmentLabel.setText("已评价");
        }
    }

    @FXML
    private void clickDetailButton() {
        webOrderListViewController.showWebOrderDetail(order);
    }

}
