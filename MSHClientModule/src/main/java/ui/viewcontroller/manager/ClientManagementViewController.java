package ui.viewcontroller.manager;

import bl.blfactory.BLFactoryImpl;
import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import ui.viewcontroller.client.ClientCreditListViewController;
import ui.viewcontroller.client.ClientDetailEditViewController;
import ui.viewcontroller.client.ClientDetailViewController;
import vo.ClientVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementViewController {

    private BorderPane rootPane;

    private ClientManagementListViewController clientManagementListViewController;
    private ClientDetailViewController clientDetailViewController;
    private ClientDetailEditViewController clientDetailEditViewController;
    private ResetPasswordViewController resetPasswordViewController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();
    private UserBLService userBLService;

    public ClientManagementViewController(BorderPane rootPane) {
        this.rootPane = rootPane;
        this.userBLService = new BLFactoryImpl().getClientBLService();
    }

    /**
     * 返回上一界面
     */
    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            rootPane.setCenter(node);

        }
    }

    /**
     * 展示客户列表
     */
    public void showClientList() {
        if (initNode != null) {
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/user/ClientManagementListView.fxml"));
            ScrollPane list = listLoader.load();

            clientManagementListViewController = listLoader.getController();
            clientManagementListViewController.setClientManagementViewController(this);
            clientManagementListViewController.setUserBLService(this.userBLService);

            initNode = list;

            rootPane.setCenter(list);

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
            loader.setLocation(Main.class.getResource("../view/client/ClientDetailView.fxml"));
            ScrollPane view = loader.load();

            clientDetailViewController = loader.getController();
            clientDetailViewController.setClientManagementViewController(this);
            clientDetailViewController.showClient(clientVO);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

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
            loader.setLocation(Main.class.getResource("../view/client/ClientDetailEditView.fxml"));
            ScrollPane view = loader.load();

            clientDetailEditViewController = loader.getController();
            clientDetailEditViewController.setClientManagementViewController(this);
            clientDetailEditViewController.showClientEdit(clientVO);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

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
            loader.setLocation(Main.class.getResource("../view/user/ResetPasswordView.fxml"));
            ScrollPane view = loader.load();

            resetPasswordViewController = loader.getController();
            resetPasswordViewController.setClientManagementViewController(this);
            resetPasswordViewController.setAccountAndID(account, ID);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

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
            loader.setLocation(Main.class.getResource("../view/client/ClientCreditListView.fxml"));
            ScrollPane view = loader.load();

            ClientCreditListViewController clientCreditViewController = loader.getController();
            clientCreditViewController.setClientID(clientID);
            clientCreditViewController.setClientManagementViewController(this);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientManagementListViewController getClientManagementListViewController() {
        return clientManagementListViewController;
    }
}
