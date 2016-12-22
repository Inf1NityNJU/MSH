package ui.viewcontroller.manager;

import blimpl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;
import vo.ClientVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementViewController {

    private MainUIController mainUIController;

    private boolean isSalesman;

    private ClientManagementListViewController clientManagementListViewController;
    private ClientManagementDetailViewController clientManagementDetailViewController;
    private ClientManagementDetailEditViewController clientManagementDetailEditViewController;
    private ResetPasswordViewController resetPasswordViewController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();
    private UserBLService userBLService;

    public ClientManagementViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
        this.userBLService = new BLFactoryImpl().getClientBLService();
    }

    /**
     * 返回上一界面
     */
    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            mainUIController.setCenter(node);
        }
    }

    /**
     * 展示客户列表
     */
    public void showClientList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/manager/ClientManagementListView.fxml"));
            ScrollPane list = listLoader.load();

            clientManagementListViewController = listLoader.getController();
            clientManagementListViewController.setClientManagementViewController(this);
            clientManagementListViewController.setUserBLService(this.userBLService);

            initNode = list;

            mainUIController.setCenter(list);

            clientManagementListViewController.launchSearchPane();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示客户详情
     *
     * @param clientVO
     */
    public void showClientDetail(ClientVO clientVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            if(isSalesman) {
                loader.setLocation(getClass().getResource("/view/manager/ClientManagementDetailCreditView.fxml"));
            }else{
                loader.setLocation(getClass().getResource("/view/manager/ClientManagementDetailView.fxml"));
            }
            ScrollPane view = loader.load();

            clientManagementDetailViewController = loader.getController();
            clientManagementDetailViewController.setClientManagementViewController(this);
            clientManagementDetailViewController.setMainUIController(mainUIController);
            clientManagementDetailViewController.showClient(clientVO);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编辑客户详情
     *
     * @param clientVO
     */
    public void editClientDetail(ClientVO clientVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/manager/ClientManagementDetailEditView.fxml"));
            ScrollPane view = loader.load();

            clientManagementDetailEditViewController = loader.getController();
            clientManagementDetailEditViewController.setClientManagementViewController(this);
            clientManagementDetailEditViewController.setMainUIController(mainUIController);
            clientManagementDetailEditViewController.showClientEdit(clientVO);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     *
     * @param account
     * @param ID
     */
    public void resetPassword(String account, String ID) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/manager/ResetPasswordView.fxml"));
            ScrollPane view = loader.load();

            resetPasswordViewController = loader.getController();
            resetPasswordViewController.setClientManagementViewController(this);
            resetPasswordViewController.setMainUIController(mainUIController);
            resetPasswordViewController.setAccountAndID(account, ID);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示信用记录
     *
     * @param clientID
     */
    public void showCreditOfClient(String clientID) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/manager/ClientManagementCreditView.fxml"));
            ScrollPane view = loader.load();

            ClientManagementCreditViewController clientCreditViewController = loader.getController();
            clientCreditViewController.setClientID(clientID);
            clientCreditViewController.setClientManagementViewController(this);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientManagementListViewController getClientManagementListViewController() {
        return clientManagementListViewController;
    }

    public void setSalesman(boolean salesman) {
        isSalesman = salesman;
    }

}
