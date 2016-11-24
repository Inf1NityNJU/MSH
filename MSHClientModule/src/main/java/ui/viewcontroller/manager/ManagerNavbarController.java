package ui.viewcontroller.manager;

import component.navbutton.NavButton;
import javafx.fxml.FXML;

/**
 * Created by Kray on 2016/11/24.
 */
public class ManagerNavbarController {

    private ManagerViewController managerViewController;

    @FXML
    private NavButton clientListButton;

    @FXML
    private NavButton staffListButton;

    @FXML
    private NavButton salesmanListButton;

    public void setManagerViewController(ManagerViewController managerViewController) {
        this.managerViewController = managerViewController;
    }

    @FXML
    public void clickClientButton(){
//        System.out.println("Client List");

        clientListButton.setIsCurrentProperty(true);
        staffListButton.setIsCurrentProperty(false);
        salesmanListButton.setIsCurrentProperty(false);

        managerViewController.showClientList();
    }

    @FXML
    public void clickStaffButton(){
//        System.out.println("Staff List");

        clientListButton.setIsCurrentProperty(false);
        staffListButton.setIsCurrentProperty(true);
        salesmanListButton.setIsCurrentProperty(false);
    }

    @FXML
    public void clickSalesmanButton(){
//        System.out.println("Salesman List");

        clientListButton.setIsCurrentProperty(false);
        staffListButton.setIsCurrentProperty(false);
        salesmanListButton.setIsCurrentProperty(true);
    }
}
