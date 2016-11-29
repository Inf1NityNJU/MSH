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

    private RoomInfoListViewController roomInfoListViewController;

    public void setRoomInfoListViewController(RoomInfoListViewController roomInfoListViewController) {
        this.roomInfoListViewController = roomInfoListViewController;
    }

    @FXML
    public void initialize() {

    }

}
