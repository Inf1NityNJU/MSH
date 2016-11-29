package ui.componentcontroller.hotel;

import component.circlebutton.CircleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.staff.RoomInfoListViewController;

/**
 * Created by SilverNarcissus on 2016/11/28.
 */
public class RoomInfoShowHeaderPaneController {

    @FXML
    private Label AllRoomLabel;

    @FXML
    private Label AvailableRoomLabel;

    @FXML
    private CircleButton addRoomButton;

    //上层组件
    private RoomInfoListViewController roomInfoListViewController;

    public void setRoomInfoListViewController(RoomInfoListViewController roomInfoListViewController) {
        this.roomInfoListViewController = roomInfoListViewController;
    }

    @FXML
    public void initialize() {

    }

    /**
     * 显示可用房间列表
     */
    @FXML
    public void checkAvailableRoomInfo() {
        roomInfoListViewController.showEditRoomView();
    }

    /**
     * 显示添加房间面板
     */
    @FXML
    public void showAddRoomPane(){
        roomInfoListViewController.showAddRoomPane();
    }
}
