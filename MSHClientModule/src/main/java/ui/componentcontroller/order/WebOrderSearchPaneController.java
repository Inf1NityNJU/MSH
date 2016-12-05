package ui.componentcontroller.order;

import component.statebutton.StateButton;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.salesman.WebOrderListViewController;
import util.OrderState;

/**
 * Created by Sorumi on 16/11/24.
 */
public class WebOrderSearchPaneController {

    private WebOrderListViewController webOrderListViewController;

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

    public void setWebOrderListViewController(WebOrderListViewController webOrderListViewController) {
        this.webOrderListViewController = webOrderListViewController;

        buttons = new StateButton[] {allButton, unexecutedButton, executedButton, cancelledButton, abnormalButton};
    }

    @FXML
    public void showAllOrders() {
        setButtonsInactive();
        allButton.setIsActiveProperty(true);
        webOrderListViewController.showOrders(null);
    }

    @FXML
    public void showUnexecutedOrders() {
        setButtonsInactive();
        unexecutedButton.setIsActiveProperty(true);
        webOrderListViewController.showOrders(OrderState.Unexecuted);
    }

    @FXML
    public void showExecutedOrders() {
        setButtonsInactive();
        executedButton.setIsActiveProperty(true);
        webOrderListViewController.showOrders(OrderState.Executed);
    }

    @FXML
    public void showCancelledOrders() {
        setButtonsInactive();
        cancelledButton.setIsActiveProperty(true);
        webOrderListViewController.showOrders(OrderState.Cancelled);
    }

    @FXML
    public void showAbnormalOrders() {
        setButtonsInactive();
        abnormalButton.setIsActiveProperty(true);
        webOrderListViewController.showOrders(OrderState.Abnormal);
    }

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }

}
