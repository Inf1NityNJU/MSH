package ui.viewcontroller.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
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

    public ClientManagementViewController(BorderPane rootPane) {
        this.rootPane = rootPane;
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

            initNode = list;
//            stack.push(list);

            rootPane.setCenter(list);

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

//            stack.push(view);
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

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetPassword(String account, String ID){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/user/ResetPasswordView.fxml"));
            ScrollPane view = loader.load();

            resetPasswordViewController = loader.getController();
            resetPasswordViewController.setClientManagementViewController(this);
            resetPasswordViewController.setAccountAndID(account, ID);

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
