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
    }

    @FXML
    public void clickClientRechargeButton() {
    }

    @FXML
    public void clickPromotionButton() {
        orderManageButton.setIsCurrentProperty(false);
        clientRechargeButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(true);
        clientGradeButton.setIsCurrentProperty(false);

        salesmanViewController.showWebPromotionList();
    }

    @FXML
    public void clickClientGradeButton(){
        orderManageButton.setIsCurrentProperty(false);
        clientRechargeButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(false);
        clientGradeButton.setIsCurrentProperty(true);

        salesmanViewController.showLevelList();
    };
}
