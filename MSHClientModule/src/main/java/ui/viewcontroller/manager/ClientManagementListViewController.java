package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.user.ClientManagementCellController;
import ui.componentcontroller.user.ClientManagementSearchPaneController;
import util.ResultMessage;
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

    private FXMLLoader[] cellLoaders = new FXMLLoader[]{};
    private Node[] cells = new Node[]{};

    private int type;

    private int currentPage;

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
        currentPage = 1;

        //From DB
        userBLService = UserBLFactory.getUserBLServiceImpl_Client();
//        From Stub
//        userBLService = new UserBLService_Stub();
        clientVOs = new ArrayList<ClientVO>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/user/ClientManagementListPane.fxml"));
            VBox pane = loader.load();

            ClientManagementSearchPaneController controller = loader.getController();
            controller.setClientManagementListViewController(this);

            contentVBox.getChildren().add(pane);

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

        this.type = type;

        //TODO
        clientVOs = userBLService.search("000");
        ArrayList<ClientVO> tmpVO = new ArrayList<ClientVO>();
        if (clientVOs.size() > 0) {
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

            cellLoaders = new FXMLLoader[tmpVO.size()];
            cells = new Node[tmpVO.size()];

            try {
                for (int i = 0; i < tmpVO.size(); i++) {
                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/user/ClientInfoCell.fxml"));
                    HBox clientCell = cellLoader.load();

                    cellLoaders[i] = cellLoader;
                    cells[i] = clientCell;

                    ClientVO clientVO = tmpVO.get(i);
                    FXMLLoader loader = cellLoaders[i];
                    contentVBox.getChildren().add(clientCell);

                    ClientManagementCellController clientManagementCellController = loader.getController();
                    clientManagementCellController.setClientManagementListViewController(this);
                    clientManagementCellController.setClientVO(clientVO);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("no client");
        }
    }

    public int getType() {
        return type;
    }

    /**
     * 展示一个客户的详细信息
     *
     * @param clientVO
     */
    public void showClientDetail(ClientVO clientVO) {
        clientManagementViewController.showClientDetail(clientVO);
    }

    /**
     * 更新客户信息
     * @param clientVO
     * @return
     */
    public ResultMessage updateClient(ClientVO clientVO){
        return userBLService.update(clientVO);
    }
}
