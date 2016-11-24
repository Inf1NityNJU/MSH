package ui.viewcontroller.client;

import component.navbutton.NavButton;
import javafx.fxml.FXML;
import ui.viewcontroller.common.MainUIController;

/**
 * Created by Sorumi on 16/11/17.
 */
public class ClientNavbarController {

    private ClientViewController clientViewController;

    @FXML
    private NavButton searchHotelButton;

    @FXML
    private NavButton orderButton;

    @FXML
    private NavButton infoButton;

    public void setClientViewController(ClientViewController clientViewController) {
        this.clientViewController = clientViewController;
    }

    @FXML
    public void clickSearchHotelButton() {
        searchHotelButton.setIsCurrentProperty(true);
        orderButton.setIsCurrentProperty(false);
        infoButton.setIsCurrentProperty(false);
    }

    @FXML
    public void clickOrderButton() {
        searchHotelButton.setIsCurrentProperty(false);
        orderButton.setIsCurrentProperty(true);
        infoButton.setIsCurrentProperty(false);

        clientViewController.showClientOrder();
    }

    @FXML
    public void clickInfoButton() {
        searchHotelButton.setIsCurrentProperty(false);
        orderButton.setIsCurrentProperty(false);
        infoButton.setIsCurrentProperty(true);
    }
}
