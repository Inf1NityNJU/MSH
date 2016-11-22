package ui.viewcontroller.order;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/11/22.
 */
public class OrderViewController {

    private BorderPane rootPane;

    private Stack<Node> stack = new Stack<Node>();

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;
    }

    /**
     *
     */
    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            rootPane.setCenter(node);
        }
    }

    /**
     * 客户订单列表
     */
    public void showClientOrderList() {
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/order/OrderListView.fxml"));
            ScrollPane list = listLoader.load();

            OrderListViewController orderListViewController = listLoader.getController();
            orderListViewController.setOrderViewController(this);

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户订单详情
     */
    public void showClientOrderDetail(OrderVO order) {
        try {
            FXMLLoader orderLoader = new FXMLLoader();
            orderLoader.setLocation(Main.class.getResource("../view/order/OrderDetailView.fxml"));
            ScrollPane view = orderLoader.load();

            OrderDetailViewController orderDetailViewController = orderLoader.getController();
            orderDetailViewController.setOrderViewController(this);
            orderDetailViewController.showOrder(order);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
