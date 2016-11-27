package ui.viewcontroller.salesman;

import bl.userbl.Salesman;
import component.navbutton.NavButton;
import javafx.fxml.FXML;

/**
 * Created by vivian on 16/11/27.
 */
public class SalesmanNavbarController {
    private SalesmanViewController salesmanViewController;

    @FXML
    private NavButton orderManageButton;

    @FXML
    private NavButton clientRechargeButton;

    @FXML
    private NavButton promotionButton;

    @FXML
    private NavButton clientGradeButton;

    public void setSalesmanViewController(SalesmanViewController salesmanViewController) {
        this.salesmanViewController = salesmanViewController;
    }

    @FXML
    public void clickOrderManageButton() {
//        searchHotelButton.setIsCurrentProperty(true);
//        orderButton.setIsCurrentProperty(false);
//        infoButton.setIsCurrentProperty(false);
    }

    @FXML
    public void clickclientRechargeButton() {
//        searchHotelButton.setIsCurrentProperty(false);
//        orderButton.setIsCurrentProperty(true);
//        infoButton.setIsCurrentProperty(false);
//
//        clientViewController.showClientOrder();
    }

    @FXML
    public void clickpromotionButton() {
//        searchHotelButton.setIsCurrentProperty(false);
//        orderButton.setIsCurrentProperty(false);
//        infoButton.setIsCurrentProperty(true);
    }

    @FXML
    public void clickclientGradeButton(){};
}
