package ui.viewcontroller.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/26.
 */
public class WorkerManagementViewController {

    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public WorkerManagementViewController(BorderPane rootPane) {
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

    public void showWorkerList() {
        if (initNode != null) {
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/manager/WorkerManagementListView.fxml"));
            ScrollPane list = listLoader.load();

            WorkerManagementListViewController workerManagementListViewController = listLoader.getController();
            workerManagementListViewController.setWorkerManagementViewController(this);

            initNode = list;
//            stack.push(list);

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
