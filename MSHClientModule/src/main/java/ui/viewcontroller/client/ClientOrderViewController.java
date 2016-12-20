package ui.viewcontroller.client;

import bl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import blservice.orderblservice.OrderBLService_Stub;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import ui.viewcontroller.common.MainUIController;
import vo.OrderVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/11/24.
 */
public class ClientOrderViewController {

    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private ClientOrderListViewController clientOrderListViewController;

    public ClientOrderViewController(MainUIController mainUIController) {
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
     * 客户订单列表
     */
    public void showClientOrderList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/client/ClientOrderListView.fxml"));
            ScrollPane list = listLoader.load();

            clientOrderListViewController = listLoader.getController();
            clientOrderListViewController.setClientOrderViewController(this);

            initNode = list;

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户订单详情
     */
    public void showClientOrderDetail(OrderVO order) {

        try {
            FXMLLoader orderLoader = new FXMLLoader();
            orderLoader.setLocation(getClass().getResource("/view/client/ClientOrderDetailView.fxml"));
            ScrollPane view = orderLoader.load();

            ClientOrderDetailViewController clientOrderDetailViewController = orderLoader.getController();
            clientOrderDetailViewController.setClientViewController(this);
            clientOrderDetailViewController.setMainUIController(mainUIController);
            clientOrderDetailViewController.showOrder(order);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 评分评价
     */
    public void showAssessmentEditView(OrderVO order) {

        try {
            FXMLLoader orderLoader = new FXMLLoader();
            orderLoader.setLocation(getClass().getResource("/view/client/ClientAssessmentEditView.fxml"));
            ScrollPane view = orderLoader.load();

            ClientAssessmentEditView clientAssessmentEditView = orderLoader.getController();
            clientAssessmentEditView.setClientViewController(this);
            clientAssessmentEditView.setMainUIController(mainUIController);
            clientAssessmentEditView.setOrder(order);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshHotelOrderList() {
        if (clientOrderListViewController != null) {
            clientOrderListViewController.refreshShowOrders();
        }
    }

}
