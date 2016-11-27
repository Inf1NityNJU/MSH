package ui.componentcontroller.order;

import component.statebutton.StateButton;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientOrderListViewController;
import util.OrderState;

/**
 * Created by Sorumi on 16/11/24.
 */
public class ClientOrderSearchPaneController {

    private ClientOrderListViewController clientOrderListViewController;

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

    public void setClientOrderListViewController(ClientOrderListViewController clientOrderListViewController) {
        this.clientOrderListViewController = clientOrderListViewController;

        buttons = new StateButton[] {allButton, unexecutedButton, executedButton, cancelledButton, abnormalButton};
    }

    @FXML
    public void showAllOrders() {
        setButtonsInactive();
        allButton.setIsActiveProperty(true);
        clientOrderListViewController.showOrders(null);
    }

    @FXML
    public void showUnexecutedOrders() {
        setButtonsInactive();
        unexecutedButton.setIsActiveProperty(true);
        clientOrderListViewController.showOrders(OrderState.Unexecuted);
    }

    @FXML
    public void showExecutedOrders() {
        setButtonsInactive();
        executedButton.setIsActiveProperty(true);
        clientOrderListViewController.showOrders(OrderState.Executed);
    }

    @FXML
    public void showCancelledOrders() {
        setButtonsInactive();
        cancelledButton.setIsActiveProperty(true);
        clientOrderListViewController.showOrders(OrderState.Cancelled);
    }

    @FXML
    public void showAbnormalOrders() {
        setButtonsInactive();
        abnormalButton.setIsActiveProperty(true);
        clientOrderListViewController.showOrders(OrderState.Abnormal);
    }

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }

}
