package ui.viewcontroller.salesman;

import blimpl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/12/5.
 */
public class WebOrderViewController {

    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private OrderBLService orderBLService;

    private WebOrderListViewController webOrderListViewController;

    public WebOrderViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
        orderBLService = new BLFactoryImpl().getOrderBLService();
    }

    /**
     * 返回上一界面
     */
    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            mainUIController.setCenter(node);

        }
    }

    /**
     * 网站订单列表
     */
    public void showWebOrderList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/salesman/WebOrderListView.fxml"));
            ScrollPane list = listLoader.load();

            webOrderListViewController = listLoader.getController();
            webOrderListViewController.setWebOrderViewController(this);

            initNode = list;

            mainUIController.setCenter(list);

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
            orderLoader.setLocation(getClass().getResource("/view/salesman/WebOrderDetailView.fxml"));
            ScrollPane view = orderLoader.load();

            WebOrderDetailViewController webOrderDetailViewController = orderLoader.getController();
            webOrderDetailViewController.setWebOrderViewController(this);
            webOrderDetailViewController.showOrder(order);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

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
