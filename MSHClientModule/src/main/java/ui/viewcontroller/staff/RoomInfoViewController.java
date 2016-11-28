package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by SilverNarcissus on 2016/11/27.
 *
 */
public class RoomInfoViewController {
    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public RoomInfoViewController(BorderPane rootPane) {
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
     * 客户订单列表
     */
    public void showRoomInfoList() {
        if (initNode != null) {
            rootPane.setCenter(initNode);
            return;
        }

        System.out.print("!!!");
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/staff/RoomInfoListView.fxml"));
            ScrollPane list = listLoader.load();

            RoomInfoListViewController roomInfoListViewController = listLoader.getController();
            roomInfoListViewController.setRoomInfoViewController(this);

            initNode = list;
//            stack.push(list);

            rootPane.setCenter(list);

            roomInfoListViewController.showRooms();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 客户订单详情
//     */
//    public void showClientOrderDetail(OrderVO order) {
//
//        try {
//            FXMLLoader orderLoader = new FXMLLoader();
//            orderLoader.setLocation(Main.class.getResource("../view/client/ClientOrderDetailView.fxml"));
//            ScrollPane view = orderLoader.load();
//
//            ClientOrderDetailViewController clientOrderDetailViewController = orderLoader.getController();
//            clientOrderDetailViewController.setClientViewController(this);
//            clientOrderDetailViewController.showOrder(order);
//
////            stack.push(view);
//            Node node = rootPane.getCenter();
//            stack.push(node);
//
//            rootPane.setCenter(view);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
