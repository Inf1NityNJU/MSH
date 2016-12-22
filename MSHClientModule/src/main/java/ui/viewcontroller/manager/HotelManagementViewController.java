package ui.viewcontroller.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;
import vo.Hotel_DetailVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/12/19.
 */
public class HotelManagementViewController {

    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private HotelManagementListViewController hotelManagementListViewController;

    public HotelManagementViewController(MainUIController mainUIController) {
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
     * 酒店列表
     */
    public void showHotelList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            hotelManagementListViewController.refreshHotels();
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/manager/HotelManagementListView.fxml"));
            ScrollPane list = listLoader.load();

            hotelManagementListViewController = listLoader.getController();
            hotelManagementListViewController.setHotelManagementViewController(this);

            initNode = list;

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加酒店
     */
    public void showHotelDetail(Hotel_DetailVO hotel) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/manager/HotelManagementDetailView.fxml"));
            ScrollPane pane = loader.load();

            HotelManagementDetailViewController hotelManagementDetailViewController = loader.getController();
            hotelManagementDetailViewController.setHotelManagementViewController(this);
            hotelManagementDetailViewController.setMainUIController(mainUIController);
            hotelManagementDetailViewController.setHotel(hotel);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 增加酒店
     */
    public void showAddHotel() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/manager/HotelManagementAddView.fxml"));
            ScrollPane pane = loader.load();

            HotelManagementAddViewController hotelManagementAddViewController = loader.getController();
            hotelManagementAddViewController.setHotelManagementViewController(this);
            hotelManagementAddViewController.setMainUIController(mainUIController);
            hotelManagementAddViewController.addHotel();

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
