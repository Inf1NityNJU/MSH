package ui.viewcontroller.client;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.user.ClientCreditCellController;
import ui.viewcontroller.manager.ClientManagementViewController;
import vo.CreditVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/29.
 */
public class ClientCreditListViewController {

    private ArrayList<CreditVO> creditVOs;
    private UserBLService userBLService;

    private String clientID;
    private ClientManagementViewController clientManagementViewController;

    private FXMLLoader[] cellLoaders = new FXMLLoader[]{};
    private Node[] cells = new Node[]{};

    @FXML
    private VBox contentVBox;

    @FXML
    private RectButton backButton;

    public void setClientID(String clientID) {
        this.clientID = clientID;

        showCredit();
    }

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;
    }

    public void clickBackButton(){
        clientManagementViewController.back();
    }

    private void showCredit(){
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Client();

        creditVOs = userBLService.searchCreditByID(clientID);

        if (creditVOs.size() > 0) {

            cellLoaders = new FXMLLoader[creditVOs.size()];
            cells = new Node[creditVOs.size()];

            try {

                for (int i = 0; i < creditVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/manager/ClientCreditCell.fxml"));
                    HBox clientCell = cellLoader.load();

                    cellLoaders[i] = cellLoader;
                    cells[i] = clientCell;

                    CreditVO creditVO = creditVOs.get(i);
                    FXMLLoader loader = cellLoaders[i];

                    ClientCreditCellController clientCreditCellController = loader.getController();
                    clientCreditCellController.setCreditVO(creditVO);

                    contentVBox.getChildren().add(2, clientCell);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no credit");
        }
    }

}
