package ui.componentcontroller.common;

import blimpl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.viewcontroller.common.MainUIController;

/**
 * Created by Kray on 2016/12/6.
 */
public class HeaderViewController {

    private MainUIController mainUIController;

    @FXML
    private Button aboutButton;

    @FXML
    private Button logoutButton;

    @FXML
    public void clickLogoutButton(){
        System.out.println("Logout");

        UserBLService userBLService = new BLFactoryImpl().getClientBLService();
        userBLService.logout();

        mainUIController.showMainView();
        mainUIController.showUtilView();
    }

    @FXML
    public void clickAboutButton(){

    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
}
