package ui.componentcontroller.hotel;

import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.manager.HotelManagementListViewController;
import util.OrderState;
import vo.BillVO;
import vo.Hotel_BriefVO;
import vo.OrderRoomVO;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/18.
 */
public class ManagerHotelCellController {

    private HotelManagementListViewController hotelManagementListViewController;
    private Hotel_BriefVO hotel;

//    @FXML
//    private Label orderIDLabel;
//
//    @FXML
//    private Label orderDateLabel;
//
//    @FXML
//    private StateButton stateLabel;
//
//    @FXML
//    private Label hotelNameLabel;
//
//    @FXML
//    private Label checkDateLabel;
//
//    @FXML
//    private Label roomLabel;
//
//    @FXML
//    private Label priceLabel;
//
//    @FXML
//    private Label assessmentLabel;
//
//    @FXML
//    private RectButton assessmentButton;
//
//    @FXML
//    private RectButton detailButton;

    public void setHotelManagementListViewController(HotelManagementListViewController hotelManagementListViewController) {
        this.hotelManagementListViewController = hotelManagementListViewController;
    }

    public void setHotel(Hotel_BriefVO hotel) {
        this.hotel = hotel;

//        orderIDLabel.setText(order.orderID != null ? order.orderID : "") ;
//        orderDateLabel.setText(order.bookedTime.toString());
//        stateLabel.setText(order.state.getName());
//        stateLabel.setColorProperty(order.state.getColor());
//        hotelNameLabel.setText(order.hotelName);
//        checkDateLabel.setText(order.checkInDate.toString() + " - " +order.checkOutDate.toString());
//
//        String roomText = "";
//
//        for (OrderRoomVO room : order.rooms) {
//            if (order.rooms.indexOf(room) > 0) {
//                roomText = roomText + " ";
//            }
//            roomText = roomText + room.type.getName() + " × " + room.quantity;
//        }
//        roomLabel.setText(roomText);
//
//        BillVO bill = order.bill;
//        priceLabel.setText("¥ " + String.format("%.2f", bill.totalPrice));
//
//        if (order.state == OrderState.Executed) {
//            if (order.assessment == null) {
//                assessmentButton.setVisible(true);
//                assessmentButton.setManaged(true);
//                assessmentLabel.setVisible(false);
//                assessmentLabel.setManaged(false);
//
//            } else {
//                assessmentButton.setVisible(false);
//                assessmentButton.setManaged(false);
//                assessmentLabel.setVisible(true);
//                assessmentLabel.setManaged(true);
//            }
//        } else {
//            assessmentLabel.setVisible(false);
//            assessmentLabel.setManaged(false);
//            assessmentButton.setVisible(false);
//            assessmentButton.setManaged(false);
//        }

    }

    @FXML
    private void clickDetailButton() {
        hotelManagementListViewController.showHotelDetail(hotel);
    }


}
