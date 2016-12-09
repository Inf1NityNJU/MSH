package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import ui.viewcontroller.client.ClientOrderDetailViewController;
import ui.viewcontroller.client.ClientOrderListViewController;
import vo.OrderVO;

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

    private WebOrderListViewController webOrderListViewController;

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

            webOrderListViewController = listLoader.getController();
            webOrderListViewController.setWebOrderViewController(this);

            initNode = list;

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 网站订单详情
     */
    public void showWebOrderDetail(OrderVO order) {
        try {
            FXMLLoader orderLoader = new FXMLLoader();
            orderLoader.setLocation(Main.class.getResource("../view/salesman/WebOrderDetailView.fxml"));
            ScrollPane view = orderLoader.load();

            WebOrderDetailViewController webOrderDetailViewController = orderLoader.getController();
            webOrderDetailViewController.setWebOrderViewController(this);
            webOrderDetailViewController.showOrder(order);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshHotelOrderList() {
        if (webOrderListViewController != null) {
            webOrderListViewController.refreshShowOrders();
        }
    }
}
