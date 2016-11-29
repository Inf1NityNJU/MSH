package ui.componentcontroller.order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import component.rectbutton.RectButton;
import component.statebutton.StateButton;

import ui.viewcontroller.client.ClientOrderListViewController;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/18.
 */
public class ClientOrderCellController {

    private ClientOrderListViewController clientOrderListViewController;
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

    public void setClientOrderListViewController(ClientOrderListViewController clientOrderListViewController) {
        this.clientOrderListViewController = clientOrderListViewController;
    }

    public void setOrder(OrderVO order) {
        this.order = order;

        orderIDLabel.setText(order.orderID != null ? order.orderID : "") ;
        orderDateLabel.setText(order.bookedTime.toString());
        stateLabel.setText(order.state.getName());
        stateLabel.setColorProperty(order.state.getColor());
        hotelNameLabel.setText(order.hotelName);
        checkDateLabel.setText(order.checkInDate.toString() + " - " +order.checkOutDate.toString());

        String roomText = "";
        for (OrderRoomVO room : order.rooms) {
            roomText = room.type.getName() + " × " + room.quantity;
        }
        roomLabel.setText(roomText);

        BillVO bill = order.bill;
        priceLabel.setText("¥ " + String.format("%.2f", bill.totalPrice));

        if (order.assessment == null) {
            assessmentLabel.setManaged(false);
        } else {
            assessmentButton.setManaged(false);
        }
    }

    @FXML
    private void clickDetailButton() {
        clientOrderListViewController.showClientOrderDetail(order);
    }


    @FXML
    private void clickAssessmentButton() {
        clientOrderListViewController.showAssessmentEditView(order);
    }


}
