package ui.viewcontroller.manager;

import component.navbutton.NavButton;
import javafx.fxml.FXML;

/**
 * Created by Kray on 2016/11/24.
 */
public class ManagerNavbarController {

    private ManagerViewController managerViewController;

    @FXML
    private NavButton hotelListButton;

    @FXML
    private NavButton clientListButton;

    @FXML
    private NavButton workerListButton;

    public void setManagerViewController(ManagerViewController managerViewController) {
        this.managerViewController = managerViewController;
    }

    @FXML
    public void initialize() {
        hotelListButton.setIcon("\ue675");
        clientListButton.setIcon("\ue678");
        workerListButton.setIcon("\ue678");
    }
    @FXML
    public void clickHotelButton() {

        hotelListButton.setIsCurrentProperty(true);
        clientListButton.setIsCurrentProperty(false);
        workerListButton.setIsCurrentProperty(false);

        managerViewController.showHotelList();
    }

    @FXML
    public void clickClientButton() {

        hotelListButton.setIsCurrentProperty(false);
        clientListButton.setIsCurrentProperty(true);
        workerListButton.setIsCurrentProperty(false);

        managerViewController.showClientList();
    }

    @FXML
    public void clickWorkerButton() {

        hotelListButton.setIsCurrentProperty(false);
        clientListButton.setIsCurrentProperty(false);
        workerListButton.setIsCurrentProperty(true);

        managerViewController.showWorkerList();
    }

}
