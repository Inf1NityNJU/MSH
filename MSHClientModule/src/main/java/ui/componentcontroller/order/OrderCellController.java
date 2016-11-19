package ui.componentcontroller.order;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.viewcontroller.common.OrderListController;

/**
 * Created by Sorumi on 16/11/18.
 */
public class OrderCellController {

    @FXML
    private Button detailButton;


    private OrderListController orderListController;

    public void setOrderListController(OrderListController orderListController) {
        this.orderListController = orderListController;
    }

    @FXML
    public void clickDetailButton() {
        orderListController.someMethodToTest();
//        System.out.print("!!!!");
    }



}
