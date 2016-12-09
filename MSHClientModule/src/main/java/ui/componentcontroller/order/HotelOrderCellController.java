package ui.componentcontroller.order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import component.rectbutton.RectButton;
import component.statebutton.StateButton;

import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.staff.HotelOrderListViewController;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/18.
 */
public class HotelOrderCellController {

    private HotelOrderListViewController hotelOrderListViewController;
    private OrderVO order;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label orderDateLabel;

    @FXML
    private StateButton stateLabel;

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

    public void setHotelOrderListViewController(HotelOrderListViewController hotelOrderListViewController) {
        this.hotelOrderListViewController = hotelOrderListViewController ;
    }

    public void setOrder(OrderVO order) {
        this.order = order;

        orderIDLabel.setText(order.orderID != null ? order.orderID : "") ;
        orderDateLabel.setText(order.bookedTime.toString());
        stateLabel.setText(order.state.getName());
        stateLabel.setColorProperty(order.state.getColor());
        clientNameLabel.setText(order.clientName);
        checkDateLabel.setText(order.checkInDate.toString() + " - " +order.checkOutDate.toString());

        String roomText = "";

        for (OrderRoomVO room : order.rooms) {
            roomText = roomText + " " + room.type.getName() + " × " + room.quantity;
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
        hotelOrderListViewController.showHotelOrderDetail(order);
    }



}
