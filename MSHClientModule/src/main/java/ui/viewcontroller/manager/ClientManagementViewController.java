package ui.viewcontroller.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.Main;
import vo.ClientVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementViewController {

    private BorderPane rootPane;

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
            listLoader.setLocation(Main.class.getResource("../view/manager/ClientManagementListView.fxml"));
            ScrollPane list = listLoader.load();

            ClientManagementListViewController clientManagementListViewController = listLoader.getController();
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
            loader.setLocation(Main.class.getResource("../view/manager/ClientManagementDetailView.fxml"));
            ScrollPane view = loader.load();

            ClientManagementDetailViewController clientManagementDetailViewController = loader.getController();
            clientManagementDetailViewController.setClientManagementViewController(this);
            clientManagementDetailViewController.showClient(clientVO);

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
            loader.setLocation(Main.class.getResource("../view/manager/ClientManagementDetailEditView.fxml"));
            ScrollPane view = loader.load();

            ClientManagementDetailEditViewController clientManagementDetailEditViewController = loader.getController();
            clientManagementDetailEditViewController.setClientManagementViewController(this);
            clientManagementDetailEditViewController.showClientEdit(clientVO);

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
