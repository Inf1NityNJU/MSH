package ui.viewcontroller.client;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userbl.UserBLFactory;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.componentcontroller.user.ClientCreditCellController;
import vo.CreditVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/12/17.
 */
public class ClientInfoCreditViewController {

    private ArrayList<CreditVO> creditVOs;
    private UserBLService userBLService;

    private String clientID;
    private ClientInfoViewController clientInfoViewController;

    private FXMLLoader[] cellLoaders = new FXMLLoader[]{};
    private Node[] cells = new Node[]{};

    @FXML
    private VBox contentVBox;

    @FXML
    private RectButton backButton;


    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();

//    public void setClientID(String clientID) {
//        this.clientID = clientID;
//        showCredit();
//    }

    public void setClientInfoViewController(ClientInfoViewController clientInfoViewController) {
        this.clientInfoViewController = clientInfoViewController;
    }

    public void clickBackButton() {
        clientInfoViewController.back();
    }

    public void showCredit() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        clientID = userBLInfo.getCurrentClientID();

        creditVOs = userBLService.searchCreditByID(clientID);

        if (creditVOs.size() > 0) {

            cellLoaders = new FXMLLoader[creditVOs.size()];
            cells = new Node[creditVOs.size()];

            try {

                for (int i = 0; i < creditVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(getClass().getResource("/component/user/ClientCreditCell.fxml"));
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
