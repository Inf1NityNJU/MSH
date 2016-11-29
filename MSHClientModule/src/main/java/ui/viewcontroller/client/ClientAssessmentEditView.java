package ui.viewcontroller.client;

import javafx.fxml.FXML;

/**
 * Created by Sorumi on 16/11/28.
 */
public class ClientAssessmentEditView {

    private ClientOrderViewController clientOrderViewController;

    public void setClientViewController(ClientOrderViewController clientOrderViewController) {
        this.clientOrderViewController = clientOrderViewController;
    }

    @FXML
    private void clickBackButton() {
        clientOrderViewController.back();
    }
}
