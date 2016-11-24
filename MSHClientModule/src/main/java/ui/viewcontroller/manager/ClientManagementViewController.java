package ui.viewcontroller.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.Main;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementViewController {

    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    /**
     * 整个的 manager 的 VC
     * @param rootPane
     */
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

    public void showClientList(){
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

}
