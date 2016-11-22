package ui.viewcontroller.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.Main;
import ui.viewcontroller.order.OrderDetailViewController;
import ui.viewcontroller.order.OrderListViewController;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/11/17.
 */
public class MainUIController {

    private BorderPane rootPane;

    private Stack<Node> stack = new Stack<Node>();

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;
    }

    public void showMainView() {
        try {
            FXMLLoader navLoader = new FXMLLoader();
            navLoader.setLocation(Main.class.getResource("../component/common/Navbar.fxml"));
            Pane navbar = navLoader.load();

            FXMLLoader headerLoader = new FXMLLoader();
            headerLoader.setLocation(Main.class.getResource("../component/common/Header.fxml"));
            HBox header = headerLoader.load();

            FXMLLoader sectionLoader = new FXMLLoader();
            sectionLoader.setLocation(Main.class.getResource("../component/common/Section.fxml"));
            ScrollPane section = sectionLoader.load();

            rootPane.setTop(header);
            rootPane.setLeft(navbar);
            rootPane.setCenter(section);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 订单列表
     */
    public void showOrderList() {
        if (!stack.empty()) {
            Node node = stack.pop();
            rootPane.setCenter(node);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/order/OrderListView.fxml"));
            ScrollPane list = listLoader.load();

            OrderListViewController orderListViewController = listLoader.getController();
            orderListViewController.setMainUIController(this);

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单详情
     */
    public void showOrderDetail(OrderVO order) {
        try {
            FXMLLoader orderLoader = new FXMLLoader();
            orderLoader.setLocation(Main.class.getResource("../view/order/OrderDetailView.fxml"));
            ScrollPane view = orderLoader.load();

            OrderDetailViewController orderDetailViewController = orderLoader.getController();
            orderDetailViewController.setMainUIController(this);
            orderDetailViewController.showOrder(order);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
