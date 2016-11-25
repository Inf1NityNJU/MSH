package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import bl.userbl.UserBLServiceImpl;
import blservice.userblservice.UserBLService;
import blservice.userblservice.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.order.manager.ClientManagementCellController;
import ui.componentcontroller.order.manager.ClientManagementListPaneController;
import vo.ClientVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementListViewController {

    /**
     * 客户列表VC
     */
    private ClientManagementViewController clientManagementViewController;

    private UserBLService userBLService;

    private ArrayList<ClientVO> clientVOs = new ArrayList<ClientVO>();

    // tmp
    private FXMLLoader[] cellLoaders = new FXMLLoader[4];
    private Node[] cells = new Node[4];

    @FXML
    private VBox contentVBox;

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;
    }

    /**
     * 自动被调用
     */
    @FXML
    public void initialize() {
        System.out.println("Init ClientManagementViewController");

        //From DB
//        userBLService = UserBLFactory.getUserBLServiceImpl_Client();
        //From Stub
        userBLService = new UserBLService_Stub();
        clientVOs = new ArrayList<ClientVO>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/manager/ClientManagementListPane.fxml"));
            VBox pane = loader.load();

            ClientManagementListPaneController controller = loader.getController();
            controller.setClientManagementListViewController(this);

            contentVBox.getChildren().add(pane);

            for (int i = 0; i < 4; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/manager/ClientInfoCell.fxml"));
                HBox clientCell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = clientCell;
            }

            controller.showAllClients();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据类型显示会员
     *
     * @param type -1:所有; 0:普通; 1:企业
     */
    public void showClients(int type) {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        //TODO
        clientVOs = userBLService.search("000");
        ArrayList<ClientVO> tmpVO = new ArrayList<ClientVO>();
        if(clientVOs.size() > 0) {
            if (type >= 0) {
                for (ClientVO clientVO : clientVOs) {
                    if ((type == 0 && clientVO.enterprise.equals(""))) {
                        tmpVO.add(clientVO);
                    } else if ((type == 1 && !clientVO.enterprise.equals(""))) {
                        tmpVO.add(clientVO);
                    }
                }
            } else {
                tmpVO = clientVOs;
            }

            System.out.println(tmpVO.size());

            for (int i = 0; i < tmpVO.size(); i++) {
                ClientVO clientVO = tmpVO.get(i);
                FXMLLoader loader = cellLoaders[i];
                Node clientCell = cells[i];

                ClientManagementCellController clientManagementCellController = loader.getController();
                clientManagementCellController.setClientManagementListViewController(this);
                clientManagementCellController.setClient(clientVO);

                contentVBox.getChildren().add(clientCell);
            }
        }else{
            System.out.println("no client");
        }
    }
}
