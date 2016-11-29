package ui.componentcontroller.hotel;

import component.mychoicebox.MyChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.staff.EditRoomInfoListViewController;
import util.DateUtil;

/**
 * Created by SilverNarcissus on 2016/11/29.
 */
public class EditRoomInfoPaneController {

    @FXML
    private Label allRoomLabel;

    @FXML
    private Label availableRoomLabel;

    @FXML
    private MyChoiceBox dateChoiceBox;


    //上层组件
    private EditRoomInfoListViewController editRoomInfoListViewController;

    public void setRoomInfoListViewController(EditRoomInfoListViewController editRoomInfoListViewController) {
        this.editRoomInfoListViewController = editRoomInfoListViewController;
    }

    //得到选择的日期
    public DateUtil getDate() {
        // TODO: 2016/11/29  日期没有得到
        return new DateUtil(2016, 12, 2);
    }

    public void backToAllRoomList(){
        editRoomInfoListViewController.backToAllRoomList();
    }
}
