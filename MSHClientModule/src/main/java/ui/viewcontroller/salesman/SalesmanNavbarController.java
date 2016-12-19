package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import bl.userbl.Salesman;
import blservice.userblservice.UserBLInfo;
import component.navbutton.NavButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.SalesmanVO;

/**
 * Created by vivian on 16/11/27.
 */
public class SalesmanNavbarController {
    private SalesmanViewController salesmanViewController;

    @FXML
    private Label nameLabel;

    @FXML
    private Label IDLabel;

    @FXML
    private NavButton orderManageButton;

    @FXML
    private NavButton clientRechargeButton;

    @FXML
    private NavButton promotionButton;

    @FXML
    private NavButton clientGradeButton;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Salesman();

    @FXML
    public void initialize() {
        String salesmanID = userBLInfo.getCurrentSalesmanID();
        if (salesmanID != null) {
            SalesmanVO salesman = userBLInfo.getSalesmanByID(salesmanID);
            nameLabel.setText(salesman.salesmanName);
            IDLabel.setText("编号：" + salesmanID);
        }
    }

    public void setSalesmanViewController(SalesmanViewController salesmanViewController) {
        this.salesmanViewController = salesmanViewController;
    }

    @FXML
    public void clickOrderManageButton() {
        orderManageButton.setIsCurrentProperty(true);
        clientRechargeButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(false);
        clientGradeButton.setIsCurrentProperty(false);

        salesmanViewController.showWebOrderList();
    }

    @FXML
    public void clickClientRechargeButton() {
        orderManageButton.setIsCurrentProperty(false);
        clientRechargeButton.setIsCurrentProperty(true);
        promotionButton.setIsCurrentProperty(false);
        clientGradeButton.setIsCurrentProperty(false);

        salesmanViewController.showClientList();
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
