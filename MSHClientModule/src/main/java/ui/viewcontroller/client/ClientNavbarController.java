package ui.viewcontroller.client;

import bl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLInfo;
import component.navbutton.NavButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.common.MainUIController;
import vo.ClientVO;

/**
 * Created by Sorumi on 16/11/17.
 */
public class ClientNavbarController {

    private ClientViewController clientViewController;

    @FXML
    private Label nameLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label IDLabel;

    @FXML
    private NavButton searchHotelButton;

    @FXML
    private NavButton orderButton;

    @FXML
    private NavButton infoButton;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();

    @FXML
    public void initialize() {
        String clientID = userBLInfo.getCurrentClientID();
        if (clientID != null) {
            ClientVO client = userBLInfo.getClientByID(clientID);
            nameLabel.setText(client.clientName);
            typeLabel.setText("客户类型：" + (client.enterprise.equals("") ? "普通客户" : "企业客户"));
            IDLabel.setText("编号：" + clientID);
        } else {
            nameLabel.setText("游客");
        }

    }

    public void setClientViewController(ClientViewController clientViewController) {
        this.clientViewController = clientViewController;
    }

    @FXML
    public void clickSearchHotelButton() {
        searchHotelButton.setIsCurrentProperty(true);
        orderButton.setIsCurrentProperty(false);
        infoButton.setIsCurrentProperty(false);

        clientViewController.showHotelSearch();
    }

    @FXML
    public void clickOrderButton() {
        searchHotelButton.setIsCurrentProperty(false);
        orderButton.setIsCurrentProperty(true);
        infoButton.setIsCurrentProperty(false);

        clientViewController.showClientOrderList();
    }

    @FXML
    public void clickInfoButton() {
        searchHotelButton.setIsCurrentProperty(false);
        orderButton.setIsCurrentProperty(false);
        infoButton.setIsCurrentProperty(true);

        clientViewController.showClientDetail();
    }
}
