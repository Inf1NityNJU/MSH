package ui.componentcontroller.order;

import component.mycheckbox.MyCheckBox;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientOrderListViewController;
import ui.viewcontroller.salesman.WebOrderListViewController;
import util.DateUtil;
import util.OrderState;

import java.time.LocalDate;

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

    @FXML
    private MyCheckBox dateCheckBox;

    @FXML
    private MyDatePicker datePicker;

    private StateButton[] buttons;

    private OrderState orderState;

    public void setWebOrderListViewController(WebOrderListViewController webOrderListViewController) {
        this.webOrderListViewController = webOrderListViewController;

        buttons = new StateButton[] {allButton, unexecutedButton, executedButton, cancelledButton, abnormalButton};
    }

    @FXML
    public void initialize() {
        datePicker.setDate(LocalDate.now());
        datePicker.setMaxDate(LocalDate.now());

        dateCheckBox.isActiveProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    DateUtil date = new DateUtil(datePicker.getDate());
                    webOrderListViewController.showOrders(orderState, date);
                }
            }
        });
        datePicker.dateProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (dateCheckBox.getIsActiveProperty()) {
                    DateUtil date = new DateUtil(datePicker.getDate());
                    webOrderListViewController.showOrders(orderState, date);
                }
            }
        });
    }

    @FXML
    public void showAllOrders() {
        setButtonsInactive();
        allButton.setIsActiveProperty(true);

        dateCheckBox.setVisible(false);
        dateCheckBox.setIsActiveProperty(false);
        datePicker.setVisible(false);

        orderState = null;
        webOrderListViewController.showOrders(null, null);
    }

    @FXML
    public void showUnexecutedOrders() {
        setButtonsInactive();
        unexecutedButton.setIsActiveProperty(true);

        dateCheckBox.setVisible(true);
        dateCheckBox.setIsActiveProperty(false);
        datePicker.setVisible(true);

        orderState = OrderState.Unexecuted;
        webOrderListViewController.showOrders(OrderState.Unexecuted, null);
    }

    @FXML
    public void showExecutedOrders() {
        setButtonsInactive();
        executedButton.setIsActiveProperty(true);

        dateCheckBox.setVisible(false);
        dateCheckBox.setIsActiveProperty(false);
        datePicker.setVisible(false);

        orderState = OrderState.Executed;
        webOrderListViewController.showOrders(OrderState.Executed, null);
    }

    @FXML
    public void showCancelledOrders() {
        setButtonsInactive();
        cancelledButton.setIsActiveProperty(true);

        dateCheckBox.setVisible(false);
        dateCheckBox.setIsActiveProperty(false);
        datePicker.setVisible(false);

        orderState = OrderState.Cancelled;
        webOrderListViewController.showOrders(OrderState.Cancelled, null);
    }

    @FXML
    public void showAbnormalOrders() {
        setButtonsInactive();
        abnormalButton.setIsActiveProperty(true);

        dateCheckBox.setVisible(true);
        dateCheckBox.setIsActiveProperty(false);
        datePicker.setVisible(true);

        orderState = OrderState.Abnormal;
        webOrderListViewController.showOrders(OrderState.Abnormal, null);
    }

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }

}
