package ui.viewcontroller.manager;

import blimpl.userblimpl.UserBLFactory;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.componentcontroller.user.ClientManagementCellController;
import ui.componentcontroller.user.ClientManagementPaneController;
import ui.componentcontroller.user.ClientManagementSearchPaneController;
import vo.ClientVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementListViewController {

    private static final int ROW_IN_PANE = 4;

    /**
     * 客户列表VC
     */
    private ClientManagementViewController clientManagementViewController;
    private ClientManagementPaneController clientManagementPaneController;
    private ClientManagementSearchPaneController clientManagementSearchPaneController;

    private UserBLService userBLService;

    private ArrayList<ClientVO> clientVOs = new ArrayList<ClientVO>();
    private ArrayList<ClientVO> tmpVOs = new ArrayList<>();

    private FXMLLoader[] cellLoaders = new FXMLLoader[ROW_IN_PANE];
    private Node[] cells = new Node[ROW_IN_PANE];

    private Node pagePane;
    private int type;

    @FXML
    private VBox contentVBox;

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;
    }

    public void setUserBLService(UserBLService userBLService) {
        this.userBLService = userBLService;
    }

    /**
     * 自动被调用
     */
    @FXML
    public void initialize() {

        clientVOs = new ArrayList<ClientVO>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/user/ClientManagementSearchPane.fxml"));
            VBox pane = loader.load();

            clientManagementSearchPaneController = loader.getController();
            clientManagementSearchPaneController.setClientManagementListViewController(this);

            contentVBox.getChildren().add(pane);

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/component/user/ClientManagementPagePane.fxml"));
            pagePane = pageLoader.load();

            clientManagementPaneController = pageLoader.getController();
            clientManagementPaneController.setClientManagementListViewController(this);

            for (int i = 0; i < ROW_IN_PANE; i++) {

                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/user/ClientInfoCell.fxml"));
                HBox clientCell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = clientCell;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchSearchPane(){
        clientManagementSearchPaneController.showAllClients();
    }

    /**
     * 根据类型显示会员
     *
     * @param type -1:所有; 0:普通; 1:企业
     */
    public void showClients(int type) {

        tmpVOs.clear();

        this.type = type;

        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Client();

        if(userBLService == null){
            System.out.println("NULL");
        }

        this.clientVOs = userBLService.search("");

        if (clientVOs.size() > 0) {
            if (type >= 0) {
                for (ClientVO clientVO : clientVOs) {
                    if ((type == 0 && clientVO.enterprise.equals(""))) {
                        this.tmpVOs.add(clientVO);
                    } else if ((type == 1 && !clientVO.enterprise.equals(""))) {
                        this.tmpVOs.add(clientVO);
                    }
                }
            } else {
                this.tmpVOs = clientVOs;
            }
        } else {
            System.out.println("No Client");
        }

        int size = this.tmpVOs.size();
        clientManagementPaneController.setPageCount(size / ROW_IN_PANE + ((size % ROW_IN_PANE == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Client");
        }
    }

    public int getType() {
        return type;
    }

    public void turnPage(int page) {
        int fromIndex = (page - 1) * ROW_IN_PANE;
        int toIndex = Math.min(page * ROW_IN_PANE, this.tmpVOs.size());
        List<ClientVO> tmpClientVOs = this.tmpVOs.subList(fromIndex, toIndex);
        setCells(tmpClientVOs);
    }

    private void setCells(List<ClientVO> clientVOs) {

        if (clientVOs.size() > ROW_IN_PANE) {
            System.out.println("ERROR");
            return;
        }

        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        contentVBox.getChildren().remove(pagePane);

        for (int i = 0; i < clientVOs.size(); i++) {
            ClientVO clientVO = clientVOs.get(i);
            FXMLLoader loader = cellLoaders[i];
            Node ordercell = cells[i];

            ClientManagementCellController clientManagementCellController = loader.getController();
            clientManagementCellController.setClientManagementListViewController(this);
            clientManagementCellController.setClientVO(clientVO);

            contentVBox.getChildren().add(ordercell);
        }

        contentVBox.getChildren().add(pagePane);
    }

    /**
     * 展示一个客户的详细信息
     *
     * @param clientVO
     */
    public void showClientDetail(ClientVO clientVO) {
        clientManagementViewController.showClientDetail(clientVO);
    }

}
