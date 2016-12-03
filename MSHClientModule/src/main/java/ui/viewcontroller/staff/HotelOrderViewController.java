package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/12/3.
 */
public class HotelOrderViewController {


    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private OrderBLService orderBLService;

    public HotelOrderViewController(BorderPane rootPane) {
        this.rootPane = rootPane;
        //TODO
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
     * 酒店工作人员订单列表
     */
    public void showHotelOrderList() {
        if (initNode != null) {
            stack.empty();
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/staff/HotelOrderListView.fxml"));
            ScrollPane list = listLoader.load();

            HotelOrderListViewController hotelOrderListViewController = listLoader.getController();
            hotelOrderListViewController.setHotelOrderViewController(this);
            hotelOrderListViewController.setOrderBLService(orderBLService);

            initNode = list;

            rootPane.setCenter(list);

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
            orderLoader.setLocation(Main.class.getResource("../view/staff/HotelOrderDetailView.fxml"));
            ScrollPane view = orderLoader.load();

            HotelOrderDetailViewController hotelOrderDetailViewController = orderLoader.getController();
            hotelOrderDetailViewController.setHotelOrderViewController(this);
            hotelOrderDetailViewController.showOrder(order);

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
