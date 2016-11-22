package ui.viewcontroller.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/11/22.
 */
public class ClientViewController {

    private BorderPane rootPane;

    private Stack<Node> stack = new Stack<Node>();

    public ClientViewController(BorderPane rootPane) {
        this.rootPane = rootPane;

        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(Main.class.getResource("../view/client/ClientNavbar.fxml"));
            Pane navbar = navLoader.load();

            ClientNavbarController clientNavbarController = navLoader.getController();
            clientNavbarController.setClientViewController(this);

            rootPane.setLeft(navbar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.showClientOrderList();
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
     * 客户订单列表
     */
    public void showClientOrderList() {
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/client/ClientOrderListView.fxml"));
            ScrollPane list = listLoader.load();

            ClientOrderListViewController clientOrderListViewController = listLoader.getController();
            clientOrderListViewController.setClientViewController(this);

            stack.push(list);

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
            orderLoader.setLocation(Main.class.getResource("../view/client/ClientOrderDetailView.fxml"));
            ScrollPane view = orderLoader.load();

            ClientOrderDetailViewController clientOrderDetailViewController = orderLoader.getController();
            clientOrderDetailViewController.setClientViewController(this);
            clientOrderDetailViewController.showOrder(order);

//            Node node = rootPane.getCenter();
//            stack.push(node);

            stack.push(view);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
