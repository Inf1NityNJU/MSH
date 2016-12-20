package ui.viewcontroller.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;
import ui.viewcontroller.manager.ResetPasswordViewController;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Sorumi on 16/12/17.
 */
public class ClientInfoViewController {

    private MainUIController mainUIController;

    private ClientInfoDetailViewController clientInfoDetailViewController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public ClientInfoViewController(MainUIController mainUIController) {
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
     * 个人信息
     */
    public void showClientInfo() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            clientInfoDetailViewController.showClient();
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/client/ClientInfoDetailView.fxml"));
            ScrollPane list = listLoader.load();

            clientInfoDetailViewController = listLoader.getController();
            clientInfoDetailViewController.setClientInfoViewController(this);
            clientInfoDetailViewController.showClient();

            initNode = list;

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编辑个人信息
     */
    public void editClientInfo() {
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/client/ClientInfoDetailEditView.fxml"));
            ScrollPane list = listLoader.load();

            ClientInfoDetailEditViewController clientInfoDetailEditViewController = listLoader.getController();
            clientInfoDetailEditViewController.setClientInfoViewController(this);
            clientInfoDetailEditViewController.setMainUIController(mainUIController);
            clientInfoDetailEditViewController.showClientEdit();

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 个人信用记录
     */
    public void showCredit() {
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/client/ClientInfoCreditView.fxml"));
            ScrollPane list = listLoader.load();

            ClientInfoCreditViewController clientInfoCreditViewController = listLoader.getController();
            clientInfoCreditViewController.setClientInfoViewController(this);
            clientInfoCreditViewController.showCredit();

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     *
     * @param account
     * @param clientID
     */
    public void resetPassword(String account, String clientID) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/manager/ResetPasswordView.fxml"));
            ScrollPane view = loader.load();

            ResetPasswordViewController resetPasswordViewController = loader.getController();
            resetPasswordViewController.setClientInfoViewController(this);
            resetPasswordViewController.setMainUIController(mainUIController);
            resetPasswordViewController.setAccountAndID(account, clientID);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
