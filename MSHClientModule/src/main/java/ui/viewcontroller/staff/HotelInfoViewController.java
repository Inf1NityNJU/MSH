package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.componentcontroller.hotel.ClientHotelRoomCellController;
import ui.viewcontroller.common.MainUIController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/12/9.
 */
public class HotelInfoViewController {


    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public HotelInfoViewController(MainUIController mainUIController) {
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
     * 展示酒店详情
     */
    public void showHotelDetail() {
        if (initNode != null) {
            stack.empty();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/staff/HotelDetailView.fxml"));
            ScrollPane pane = loader.load();

            HotelDetailViewController hotelDetailViewController = loader.getController();
            hotelDetailViewController.setHotelInfoViewController(this);
            hotelDetailViewController.setHotel();

            initNode = pane;

            mainUIController.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
