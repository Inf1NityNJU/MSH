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

    private Node initNode;
//    private Node showEditList;
//    private Node addRoomPane;
    private Stack<Node> stack = new Stack<Node>();

    /**
     * 浏览全部房间的控制器
     */
    private RoomAllListViewController roomAllListViewController;
    /**
     * 编辑房间信息控制器
     */
    private RoomAvailableListViewController roomAvailableListViewController;

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
    }

    /**
     * 显示全部房间列表
     */
    public void showRoomAllList() {
        if (initNode != null) {
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/staff/RoomAllListView.fxml"));
            ScrollPane list = listLoader.load();

            roomAllListViewController = listLoader.getController();
            roomAllListViewController.setRoomInfoViewController(this);
            roomAllListViewController.showAllRoomList();

            initNode = list;

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示可用房间列表
     */
    public void showRoomAvailableList() {
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/staff/RoomAvailableListView.fxml"));
            ScrollPane list = listLoader.load();

            roomAvailableListViewController = listLoader.getController();
            roomAvailableListViewController.setRoomInfoViewController(this);
            roomAvailableListViewController.showAvailableRoomList();

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示添加房间界面
     */
    public void showAddRoomView() {
//        if (addRoomPane != null) {
//            rootPane.setCenter(addRoomPane);
//            addHotelRoomPaneController.clean();
//            return;
//        }
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../component/hotel/AddRoomInfoPane.fxml"));
            ScrollPane list = listLoader.load();

            addHotelRoomPaneController = listLoader.getController();
            addHotelRoomPaneController.setRoomInfoViewController(this);
            addHotelRoomPaneController.clean();

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
