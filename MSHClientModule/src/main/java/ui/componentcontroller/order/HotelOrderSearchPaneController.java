package ui.componentcontroller.order;

import component.statebutton.StateButton;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.staff.HotelOrderListViewController;
import util.OrderState;

/**
 * Created by Sorumi on 16/11/24.
 */
public class HotelOrderSearchPaneController {

    private HotelOrderListViewController hotelOrderListViewController;

    @FXML
    private StateButton allButton;

    @FXML
    private StateButton unexecutedButton;

    @FXML
    private StateButton executedButton;

    @FXML
    private StateButton cancelledButton;

    @FXML
    private StateButton abnormalButton;

    private StateButton[] buttons;

    public void setHotelOrderListViewController(HotelOrderListViewController hotelOrderListViewController) {
        this.hotelOrderListViewController = hotelOrderListViewController;

        buttons = new StateButton[] {allButton, unexecutedButton, executedButton, cancelledButton, abnormalButton};

    }

    @FXML
    public void showAllOrders() {
        setButtonsInactive();
        allButton.setIsActiveProperty(true);
        hotelOrderListViewController.showOrders(null);
    }

    @FXML
    public void showUnexecutedOrders() {
        setButtonsInactive();
        unexecutedButton.setIsActiveProperty(true);
        hotelOrderListViewController.showOrders(OrderState.Unexecuted);
    }

    @FXML
    public void showExecutedOrders() {
        setButtonsInactive();
        executedButton.setIsActiveProperty(true);
        hotelOrderListViewController.showOrders(OrderState.Executed);
    }

    @FXML
    public void showCancelledOrders() {
        setButtonsInactive();
        cancelledButton.setIsActiveProperty(true);
        hotelOrderListViewController.showOrders(OrderState.Cancelled);
    }

    @FXML
    public void showAbnormalOrders() {
        setButtonsInactive();
        abnormalButton.setIsActiveProperty(true);
        hotelOrderListViewController.showOrders(OrderState.Abnormal);
    }

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }

}
