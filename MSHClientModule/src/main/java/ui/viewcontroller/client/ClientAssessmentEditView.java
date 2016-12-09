package ui.viewcontroller.client;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import component.commontextarea.CommonTextArea;
import component.ratestarpane.RateStarPane;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.AssessmentVO;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

/**
 * Created by Sorumi on 16/11/28.
 */
public class ClientAssessmentEditView {

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label checkDateLabel;

    @FXML
    private Label roomLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private RectButton detailButton;

    @FXML
    private RateStarPane serviceScorePane;

    @FXML
    private RateStarPane facilityScorePane;

    @FXML
    private RateStarPane healthScorePane;

    @FXML
    private RateStarPane locationScorePane;

    @FXML
    private CommonTextArea commentTextArea;

    @FXML
    private RectButton backButton;

    @FXML
    private RectButton confirmButton;

    private ClientOrderViewController clientOrderViewController;

    private OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    private OrderVO order;

    public void setClientViewController(ClientOrderViewController clientOrderViewController) {
        this.clientOrderViewController = clientOrderViewController;
    }

    public void setOrder(OrderVO order) {
        this.order = order;

        hotelNameLabel.setText(order.hotelName);
        checkDateLabel.setText(order.checkInDate.toString() + " - " +order.checkOutDate.toString());

        String roomText = "";

        for (OrderRoomVO room : order.rooms) {
            roomText = roomText + " " + room.type.getName() + " × " + room.quantity;
        }
        roomLabel.setText(roomText);

        BillVO bill = order.bill;
        priceLabel.setText("¥ " + String.format("%.2f", bill.totalPrice));

    }

    @FXML
    private void clickDetailButton() {
        clientOrderViewController.showClientOrderDetail(order);
    }

    @FXML
    private void clickBackButton() {
        clientOrderViewController.back();
    }

    @FXML
    private void clickConfirmButton() {
        int serviceScore = serviceScorePane.getScore();
        int facilityScore = facilityScorePane.getScore();
        int healthScore = healthScorePane.getScore();
        int locationScore = locationScorePane.getScore();
        String comment = commentTextArea.getText();

        AssessmentVO assessment = new AssessmentVO(serviceScore, facilityScore, healthScore, locationScore, comment);
        orderBLService.editOrderAssessment(order.orderID, assessment);
    }
}
