package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import ui.componentcontroller.hotel.ClientHotelRoomCellController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/12/9.
 */
public class HotelInfoViewController {


    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public HotelInfoViewController(BorderPane rootPane) {
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
     * 展示酒店详情
     */
    public void showHotelDetail() {
        if (initNode != null) {
            stack.empty();
            rootPane.setCenter(initNode);
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

            rootPane.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
