package ui.viewcontroller.salesman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/29.
 */
public class LevelManagementViewController {
    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    /**
     * 返回上一界面
     */
    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            mainUIController.setCenter(node);

        }
    }

    public LevelManagementViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    /**
     * 展示等级列表
     */
    public void showLevelList(){
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/salesman/LevelListView.fxml"));
            ScrollPane list = listLoader.load();

            LevelListViewController levelListViewController = listLoader.getController();
            levelListViewController.setLevelManagementViewController(this);

            initNode = list;
//            stack.push(list);

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
