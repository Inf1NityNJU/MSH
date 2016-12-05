package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import ui.viewcontroller.client.ClientOrderListViewController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/12/5.
 */
public class WebOrderViewController {

    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private OrderBLService orderBLService;
    public WebOrderViewController(BorderPane rootPane) {
        this.rootPane = rootPane;
        orderBLService = new BLFactoryImpl().getOrderBLService();
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
     * 网站订单列表
     */
    public void showWebOrderList() {
        if (initNode != null) {
            stack.clear();
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/salesman/WebOrderListView.fxml"));
            ScrollPane list = listLoader.load();

            WebOrderListViewController webOrderListViewController = listLoader.getController();
            webOrderListViewController.setWebOrderViewController(this);

            initNode = list;

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
