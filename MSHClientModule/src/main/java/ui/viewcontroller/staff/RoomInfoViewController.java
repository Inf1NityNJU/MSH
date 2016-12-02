package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import ui.componentcontroller.hotel.AddHotelRoomPaneController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by SilverNarcissus on 2016/11/27.
 */
public class RoomInfoViewController {
    private BorderPane rootPane;

    private Node showAllList;
    private Node showEditList;
    private Node addRoomPane;
    private Stack<Node> stack = new Stack<Node>();

    /**
     * 浏览全部房间的控制器
     */
    private RoomInfoListViewController roomInfoListViewController;
    /**
     * 编辑房间信息控制器
     */
    private EditRoomInfoListViewController editRoomInfoListViewController;

    /**
     * 添加房间信息控制器
     */
    private AddHotelRoomPaneController addHotelRoomPaneController;

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
        showRoomInfoList();
    }

    /**
     * 显示房全部房间列表
     */
    public void showRoomInfoList() {
        if (showAllList != null) {
            rootPane.setCenter(showAllList);
            roomInfoListViewController.showAllRoomList();
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/staff/RoomInfoListView.fxml"));
            ScrollPane list = listLoader.load();

            roomInfoListViewController = listLoader.getController();
            roomInfoListViewController.setRoomInfoViewController(this);

            showAllList = list;
            stack.push(list);

            rootPane.setCenter(list);

            roomInfoListViewController.showAllRoomList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示编辑房间信息列表
     */
    public void showEditRoomView() {
        if (showEditList != null) {
            rootPane.setCenter(showEditList);
            editRoomInfoListViewController.showEditRoomList();
            return;
        }
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/staff/EditRoomInfoListView.fxml"));
            ScrollPane list = listLoader.load();

            editRoomInfoListViewController = listLoader.getController();
            editRoomInfoListViewController.setRoomInfoViewController(this);

            showEditList = list;
            //stack.push(list);

            rootPane.setCenter(list);

            editRoomInfoListViewController.showEditRoomList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示添加房间界面
     */
    public void showAddRoomView() {
        if (addRoomPane != null) {
            rootPane.setCenter(addRoomPane);
            addHotelRoomPaneController.clean();
            return;
        }
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../component/hotel/AddRoomInfoPane.fxml"));
            ScrollPane list = listLoader.load();

            addHotelRoomPaneController = listLoader.getController();
            addHotelRoomPaneController.setRoomInfoViewController(this);
            addHotelRoomPaneController.clean();

            addRoomPane = list;
            //stack.push(list);

            rootPane.setCenter(list);

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
