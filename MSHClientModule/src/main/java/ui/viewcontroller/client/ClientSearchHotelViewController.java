package ui.viewcontroller.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import vo.OrderVO;

import java.io.IOException;
import java.util.Date;
import java.util.Stack;

/**
 * Created by Sorumi on 16/11/24.
 */
public class ClientSearchHotelViewController {


    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public ClientSearchHotelViewController(BorderPane rootPane) {
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
     * 酒店列表
     */
    public void showClientHotelList() {
        if (initNode != null) {
            stack.empty();
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/client/ClientHotelListView.fxml"));
            ScrollPane list = listLoader.load();

            ClientHotelListViewController clientHotelListViewController = listLoader.getController();
            clientHotelListViewController.setClientSearchHotelViewController(this);

            initNode = list;

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 酒店详情
     */
    public void showHotelDetail(String hotelID) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/client/ClientHotelDetailView.fxml"));
            ScrollPane pane = loader.load();

            ClientHotelDetailViewController clientHotelDetailViewController = loader.getController();
            clientHotelDetailViewController.setClientSearchHotelViewController(this);
            clientHotelDetailViewController.setHotel(hotelID);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 预定订单
     */
    public void showBookOrder(OrderVO order) {

        System.out.print("!!!");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/client/ClientBookOrderView.fxml"));
            ScrollPane pane = loader.load();

            ClientBookOrderViewController clientBookOrderViewController = loader.getController();
            clientBookOrderViewController.setClientSearchHotelViewController(this);
            clientBookOrderViewController.setOrder(order);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
