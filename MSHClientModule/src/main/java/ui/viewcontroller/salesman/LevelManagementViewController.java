package ui.viewcontroller.salesman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.viewcontroller.client.ClientCreditListViewController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/29.
 */
public class LevelManagementViewController {
    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    /**
     * 返回上一界面
     */
    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            rootPane.setCenter(node);

        }
    }

    public LevelManagementViewController(BorderPane rootPane) {
        this.rootPane = rootPane;
    }

    /**
     * 展示等级列表
     */
    public void showLevelList(){
        if (initNode != null) {
            stack.clear();
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/salesman/LevelListView.fxml"));
            ScrollPane list = listLoader.load();

            LevelListViewController levelListViewController = listLoader.getController();
            levelListViewController.setLevelManagementViewController(this);

            initNode = list;
//            stack.push(list);

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
