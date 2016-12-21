package ui.viewcontroller.manager;

import blimpl.userbl.UserBLFactory;
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
 * Created by Kray on 2016/11/29.
 */
public class ClientManagementCreditViewController {

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

    public void clickBackButton() {
        clientManagementViewController.back();
    }

    private void showCredit() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Client();

        creditVOs = userBLService.searchCreditByID(clientID);

//        creditVOs.sort(new CreditComparator());

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

//    private class CreditComparator implements Comparator<CreditVO> {
//
//        public int compare(CreditVO o1, CreditVO o2) {
//            if (o1.date.year - o2.date.year != 0) {
//                return o1.date.year - o2.date.year;
//            } else {
//                if (o1.date.month - o2.date.month != 0) {
//                    return o1.date.month - o2.date.month;
//                } else {
//                    if (o1.date.day - o2.date.day != 0) {
//                        return o1.date.day - o2.date.day;
//                    } else {
//                        if (o1.orderID.charAt(0) == '-' && o2.orderID.charAt(0) != '-') {
//                            return 1;
//                        } else if (o1.orderID.charAt(0) != '-' && o2.orderID.charAt(0) == '-') {
//                            return -1;
//                        } else if (o1.orderID.charAt(0) == '-' && o2.orderID.charAt(0) == '-') {
//                            return 1;
//                        } else {
//                            return Integer.parseInt(o1.orderID.substring(o1.orderID.length() - 5, o1.orderID.length() - 1))
//                                    - Integer.parseInt(o2.orderID.substring(o1.orderID.length() - 5, o1.orderID.length() - 1));
//                        }
//                    }
//                }
//            }
//        }
//    }

}
