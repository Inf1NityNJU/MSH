package ui.viewcontroller.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import vo.SalesmanVO;
import vo.StaffVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/26.
 */
public class WorkerManagementViewController {

    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    public WorkerManagementViewController(BorderPane rootPane) {
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
     * 展示所有工作人员
     */
    public void showWorkerList() {
        if (initNode != null) {
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/user/WorkerManagementListView.fxml"));
            ScrollPane list = listLoader.load();

            WorkerManagementListViewController workerManagementListViewController = listLoader.getController();
            workerManagementListViewController.setWorkerManagementViewController(this);

            initNode = list;
//            stack.push(list);

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示酒店工作人员详情
     *
     * @param staffVO
     */
    public void showStaffDetail(StaffVO staffVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/user/StaffManagementDetailView.fxml"));
            ScrollPane view = loader.load();

            StaffManagementDetailViewController staffManagementDetailViewController = loader.getController();
            staffManagementDetailViewController.setWorkerManagementViewController(this);
            staffManagementDetailViewController.showStaff(staffVO);

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示网站营销人员详情
     *
     * @param salesmanVO
     */
    public void showSalesmanDetail(SalesmanVO salesmanVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/user/SalesmanManagementDetailView.fxml"));
            ScrollPane view = loader.load();

            SalesmanManagementDetailViewController staffManagementDetailViewController = loader.getController();
            staffManagementDetailViewController.setWorkerManagementViewController(this);
            staffManagementDetailViewController.showSalesman(salesmanVO);

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编辑酒店工作人员信息
     *
     * @param staffVO
     */
    public void editStaffDetail(StaffVO staffVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/user/StaffManagementDetailEditView.fxml"));
            ScrollPane view = loader.load();

            StaffManagementDetailEditViewController staffManagementDetailEditViewController = loader.getController();
            staffManagementDetailEditViewController.setWorkerManagementViewController(this);
            staffManagementDetailEditViewController.showStaffEdit(staffVO);

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编辑网站营销人员信息
     *
     * @param salesmanVO
     */
    public void editSalesmanDetail(SalesmanVO salesmanVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/user/SalesmanManagementDetailEditView.fxml"));
            ScrollPane view = loader.load();

            SalesmanManagementDetailEditViewController salesmanManagementDetailEditViewController = loader.getController();
            salesmanManagementDetailEditViewController.setWorkerManagementViewController(this);
            salesmanManagementDetailEditViewController.showSalesmanEdit(salesmanVO);

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加工作人员
     */
    public void addWorker(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/user/WorkerManagementAddView.fxml"));
            ScrollPane view = loader.load();

            WorkerManagementAddViewController workerManagementAddViewController = loader.getController();
            workerManagementAddViewController.setWorkerManagementViewController(this);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     * @param workerAccount
     */
    public void resetPassword(String workerAccount, String workerID){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/user/ResetPasswordView.fxml"));
            ScrollPane view = loader.load();

            ResetPasswordViewController resetPasswordViewController = loader.getController();
            resetPasswordViewController.setWorkerManagementViewController(this);
            resetPasswordViewController.setAccountAndID(workerAccount, workerID);

//            stack.push(view);
            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
