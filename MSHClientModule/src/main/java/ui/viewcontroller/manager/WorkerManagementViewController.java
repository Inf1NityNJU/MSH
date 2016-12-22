package ui.viewcontroller.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import ui.viewcontroller.common.MainUIController;
import vo.SalesmanVO;
import vo.StaffVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Kray on 2016/11/26.
 */
public class WorkerManagementViewController {

    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private WorkerManagementListViewController workerManagementListViewController;

    public WorkerManagementViewController(MainUIController mainUIController) {
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
     * 展示所有工作人员
     */
    public void showWorkerList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/manager/WorkerManagementListView.fxml"));
            ScrollPane list = listLoader.load();

            workerManagementListViewController = listLoader.getController();
            workerManagementListViewController.setWorkerManagementViewController(this);

            initNode = list;

            mainUIController.setCenter(list);

            workerManagementListViewController.launchSearchPane();

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
            loader.setLocation(getClass().getResource("/view/manager/StaffManagementDetailView.fxml"));
            ScrollPane view = loader.load();

            StaffManagementDetailViewController staffManagementDetailViewController = loader.getController();
            staffManagementDetailViewController.setWorkerManagementViewController(this);
            staffManagementDetailViewController.showStaff(staffVO);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

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
            loader.setLocation(getClass().getResource("/view/manager/SalesmanManagementDetailView.fxml"));
            ScrollPane view = loader.load();

            SalesmanManagementDetailViewController staffManagementDetailViewController = loader.getController();
            staffManagementDetailViewController.setWorkerManagementViewController(this);
            staffManagementDetailViewController.showSalesman(salesmanVO);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

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
            loader.setLocation(getClass().getResource("/view/manager/StaffManagementDetailEditView.fxml"));
            ScrollPane view = loader.load();

            StaffManagementDetailEditViewController staffManagementDetailEditViewController = loader.getController();
            staffManagementDetailEditViewController.setWorkerManagementViewController(this);
            staffManagementDetailEditViewController.setMainUIController(mainUIController);
            staffManagementDetailEditViewController.showStaffEdit(staffVO);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

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
            loader.setLocation(getClass().getResource("/view/manager/SalesmanManagementDetailEditView.fxml"));
            ScrollPane view = loader.load();

            SalesmanManagementDetailEditViewController salesmanManagementDetailEditViewController = loader.getController();
            salesmanManagementDetailEditViewController.setWorkerManagementViewController(this);
            salesmanManagementDetailEditViewController.setMainUIController(mainUIController);
            salesmanManagementDetailEditViewController.showSalesmanEdit(salesmanVO);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加工作人员
     */
    public void addWorker() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/manager/WorkerManagementAddView.fxml"));
            ScrollPane view = loader.load();

            WorkerManagementAddViewController workerManagementAddViewController = loader.getController();
            workerManagementAddViewController.setWorkerManagementViewController(this);
            workerManagementAddViewController.setMainUIController(mainUIController);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     *
     * @param workerAccount
     */
    public void resetPassword(String workerAccount, String workerID) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/manager/ResetPasswordView.fxml"));
            ScrollPane view = loader.load();

            ResetPasswordViewController resetPasswordViewController = loader.getController();
            resetPasswordViewController.setWorkerManagementViewController(this);
            resetPasswordViewController.setMainUIController(mainUIController);
            resetPasswordViewController.setAccountAndID(workerAccount, workerID);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WorkerManagementListViewController getWorkerManagementListViewController() {
        return workerManagementListViewController;
    }
}
