package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/12/3.
 */
public class HotelOrderViewController {

    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private HotelOrderListViewController hotelOrderListViewController;

    public HotelOrderViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
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
     * 酒店工作人员订单列表
     */
    public void showHotelOrderList() {
        if (initNode != null) {
            stack.empty();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/staff/HotelOrderListView.fxml"));
            ScrollPane list = listLoader.load();

            hotelOrderListViewController = listLoader.getController();
            hotelOrderListViewController.setHotelOrderViewController(this);

            initNode = list;

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 酒店工作人员订单详情
     */
    public void showHotelOrderDetail(OrderVO order) {
//
        try {
            FXMLLoader orderLoader = new FXMLLoader();
            orderLoader.setLocation(getClass().getResource("/view/staff/HotelOrderDetailView.fxml"));
            ScrollPane view = orderLoader.load();

            HotelOrderDetailViewController hotelOrderDetailViewController = orderLoader.getController();
            hotelOrderDetailViewController.setHotelOrderViewController(this);
            hotelOrderDetailViewController.showOrder(order);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshHotelOrderList() {
        if (hotelOrderListViewController != null) {
            hotelOrderListViewController.refreshShowOrders();
        }
    }

}
