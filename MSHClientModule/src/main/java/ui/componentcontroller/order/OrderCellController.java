package ui.componentcontroller.order;

import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.viewcontroller.common.OrderListController;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/18.
 */
public class OrderCellController {

    private OrderListController orderListController;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label orderDateLabel;

    @FXML
    private StateButton stateLabel;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label checkDateLabel;

    @FXML
    private Label roomLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label assessmentLabel;

    @FXML
    private RectButton assessmentButton;

    @FXML
    private RectButton detailButton;

    public void setOrderListController(OrderListController orderListController) {
        this.orderListController = orderListController;
    }

    @FXML
    public void clickDetailButton() {
        orderListController.someMethodToTest();
//        System.out.print("!!!!");
    }

    public void setOrder(OrderVO order) {
        orderIDLabel.setText(order.orderID != null ? order.orderID : "") ;
        orderDateLabel.setText(order.bookedTime.toString());
        stateLabel.setText(order.state.toString());
        stateLabel.setColorProperty(order.state.getColor());
        hotelNameLabel.setText(order.hotelName);
        checkDateLabel.setText(order.checkInDate.toString() + " - " +order.checkOutDate.toString());

        String roomText = "";
        for (OrderRoomVO room : order.rooms) {
            roomText = room.type.toString() + " × " + room.quantity;
        }
        roomLabel.setText(roomText);

        BillVO bill = order.bill;
        priceLabel.setText("¥ " + String.format("%.2f", bill.totalPrice));
    }



}
