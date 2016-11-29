package ui.viewcontroller.salesman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by vivian on 16/11/27.
 */
public class WebPromotionViewController {
    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public WebPromotionViewController(BorderPane rootPane) {
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
     * 网站促销策略列表
     */
    public void showWebPromotionList() {
        if (initNode != null) {
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/salesman/WebPromotionListView.fxml"));
            ScrollPane list = listLoader.load();

            WebPromotionListViewController webPromotionListViewController = listLoader.getController();
            webPromotionListViewController.setWebPromotionViewController(this);

            initNode = list;

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
