package ui.viewcontroller.client;

import blimpl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import component.commontextarea.CommonTextArea;
import component.ratestarpane.RateStarPane;
import component.rectbutton.RectButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import vo.AssessmentVO;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

import java.io.IOException;

/**
 * Created by Sorumi on 16/11/28.
 */
public class ClientAssessmentEditViewController {

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

    private MainUIController mainUIController;

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void setClientViewController(ClientOrderViewController clientOrderViewController) {
        this.clientOrderViewController = clientOrderViewController;
    }

    public void setOrder(OrderVO order) {
        this.order = order;

        hotelNameLabel.setText(order.hotelName);
        checkDateLabel.setText(order.checkInDate.toString() + " - " + order.checkOutDate.toString());

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

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();
            alertViewController.hideLeftButton();
            alertViewController.setInfoLabel("评价成功！");
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    confirm();
                }
            });

            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void confirm() {
        mainUIController.hidePop();
        clientOrderViewController.refreshClientOrderList();
//        clientOrderViewController.showClientOrderList();
    }

}
