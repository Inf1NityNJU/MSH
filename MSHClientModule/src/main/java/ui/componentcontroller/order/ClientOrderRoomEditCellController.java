package ui.componentcontroller.order;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import component.circlebutton.CircleButton;
import component.circleimage.CircleImage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import ui.viewcontroller.client.ClientBookOrderViewController;
import vo.OrderRoomVO;

import java.text.DecimalFormat;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientOrderRoomEditCellController {

    @FXML
    private CircleImage imageView;

    @FXML
    private Label typeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label availableQuantityLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private CircleButton minusButton;

    @FXML
    private CircleButton plusButton;

    private ClientBookOrderViewController clientBookOrderViewController;

    private Pane pane;
    private OrderRoomVO room;
    private int availableQuantity;

    OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    public void setClientBookOrderViewController(ClientBookOrderViewController clientBookOrderViewController) {
        this.clientBookOrderViewController = clientBookOrderViewController;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setRoom(OrderRoomVO room) {
        this.room = room;

        imageView.setImage(new Image(getClass().getResource("/images/room/" + room.type.toString() + ".png").toExternalForm()));
        imageView.setRadius(40);

        availableQuantity = orderBLService.getOrderRoomStock(room);

        typeLabel.setText(room.type.getName());
        priceLabel.setText("¥ " + room.price);
        availableQuantityLabel.setText("剩余 " + availableQuantity + " 间");

        refreshBill();
    }

    @FXML
    private void clickMinusButton() {
        orderBLService.modifyRoomQuantity(room.type, -1);
        refreshBill();
    }

    @FXML
    private void clickPlusButton() {
        orderBLService.modifyRoomQuantity(room.type, 1);
        refreshBill();
    }

    @FXML
    private void clickDeleteButton() {
        orderBLService.modifyRoomQuantity(room.type, -room.quantity);
        clientBookOrderViewController.removeRoom(pane);
        refreshBill();
    }

    private void refreshBill() {
        DecimalFormat df = new DecimalFormat("#.00");

        minusButton.setAbled(room.quantity > 1);
        plusButton.setAbled(room.quantity < availableQuantity);

        quantityLabel.setText(room.quantity + "");
        totalPriceLabel.setText("¥ " + df.format(room.price * room.quantity));
        clientBookOrderViewController.refreshBill();
    }
}
