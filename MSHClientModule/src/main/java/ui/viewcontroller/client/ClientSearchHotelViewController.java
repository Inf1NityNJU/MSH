package ui.viewcontroller.client;

import blimpl.hotelblimpl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;
import vo.Hotel_DetailVO;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/11/24.
 */
public class ClientSearchHotelViewController {


    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();


    private HotelBLService hotelBLService;

    private  ClientHotelDetailViewController clientHotelDetailViewController;

    public ClientSearchHotelViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
        hotelBLService = HotelBLFactory.getHotelBLService();
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
     * 酒店列表
     */
    public void showClientHotelList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/client/ClientHotelListView.fxml"));
            ScrollPane list = listLoader.load();

            ClientHotelListViewController clientHotelListViewController = listLoader.getController();
            clientHotelListViewController.setClientSearchHotelViewController(this);

            initNode = list;

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 酒店详情
     */
    public void showHotelDetail(String hotelID) {

        Hotel_DetailVO hotel = hotelBLService.getHotel(hotelID);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/client/ClientHotelDetailView.fxml"));
            ScrollPane pane = loader.load();

            clientHotelDetailViewController = loader.getController();
            clientHotelDetailViewController.setClientSearchHotelViewController(this);
            clientHotelDetailViewController.setMainUIController(mainUIController);
            clientHotelDetailViewController.setHotel(hotel);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 预定订单
     */
    public void showBookOrder(OrderVO order) {

//        System.out.print("!!!");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/client/ClientBookOrderView.fxml"));
            ScrollPane pane = loader.load();

            ClientBookOrderViewController clientBookOrderViewController = loader.getController();
            clientBookOrderViewController.setClientSearchHotelViewController(this);
            clientBookOrderViewController.setMainUIController(mainUIController);
            clientBookOrderViewController.setOrder(order);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshHotel() {
        clientHotelDetailViewController.newOrder();
    }

}
