package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by SilverNarcissus on 2016/11/27.
 */
public class RoomInfoViewController {
    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private RoomAllListViewController roomAllListViewController;

    public RoomInfoViewController(MainUIController mainUIController) {
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
     * 显示全部房间列表
     */
    public void showRoomAllList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            roomAllListViewController.showAllRoomList();
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/staff/RoomAllListView.fxml"));
            ScrollPane list = listLoader.load();

            roomAllListViewController = listLoader.getController();
            roomAllListViewController.setRoomInfoViewController(this);
            roomAllListViewController.showAllRoomList();

            initNode = list;

            mainUIController.setCenter(list);

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
            listLoader.setLocation(getClass().getResource("/view/staff/RoomAvailableListView.fxml"));
            ScrollPane list = listLoader.load();

            RoomAvailableListViewController roomAvailableListViewController = listLoader.getController();
            roomAvailableListViewController.setRoomInfoViewController(this);
            roomAvailableListViewController.showAvailableRoomList();

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示添加房间界面
     */
    public void showAddRoomView() {
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/staff/RoomAddView.fxml"));
            ScrollPane list = listLoader.load();

            RoomAddViewController roomAddViewController = listLoader.getController();
            roomAddViewController.setRoomInfoViewController(this);
            roomAddViewController.setMainUIController(mainUIController);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
