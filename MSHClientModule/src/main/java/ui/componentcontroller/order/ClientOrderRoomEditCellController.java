package ui.componentcontroller.order;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import component.circlebutton.CircleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientBookOrderViewController;
import vo.OrderRoomStockVO;
import vo.OrderRoomVO;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientOrderRoomEditCellController {

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

    private OrderRoomVO room;
    private int availableQuantity;

    OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    public void setClientBookOrderViewController(ClientBookOrderViewController clientBookOrderViewController) {
        this.clientBookOrderViewController = clientBookOrderViewController;
    }

    public void setRoom(OrderRoomVO room) {
        this.room = room;
        OrderRoomStockVO stock = orderBLService.getOrderRoomStock(room);
        availableQuantity = stock.availableQuantity;

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


    private void refreshBill() {
        if (room.quantity <= 1) {
            minusButton.setVisible(false);
        } else {
            minusButton.setVisible(true);
        }

        if (room.quantity >= availableQuantity) {
            plusButton.setVisible(false);
        } else {
            plusButton.setVisible(true);
        }
        quantityLabel.setText(room.quantity + "");
        totalPriceLabel.setText("¥ " + room.price*room.quantity);
        clientBookOrderViewController.refreshBill();
    }
}
