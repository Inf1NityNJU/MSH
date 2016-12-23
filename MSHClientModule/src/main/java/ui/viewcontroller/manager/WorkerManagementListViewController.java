package ui.viewcontroller.manager;

import blimpl.userblimpl.UserBLFactory;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.componentcontroller.user.WorkerManagementCellController;
import ui.componentcontroller.user.WorkerManagementPaneController;
import ui.componentcontroller.user.WorkerManagementSearchPaneController;
import vo.SalesmanVO;
import vo.StaffVO;
import vo.UserVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kray on 2016/11/26.
 */
public class WorkerManagementListViewController {

    private static final int ROW_IN_PANE = 4;

    private WorkerManagementViewController workerManagementViewController;
    private WorkerManagementSearchPaneController workerManagementSearchPaneController;
    private WorkerManagementPaneController workerManagementPaneController;

    private UserBLService userBLService;

    private ArrayList<StaffVO> staffVOs;
    private ArrayList<SalesmanVO> salesmanVOs;

    private ArrayList<UserVO> tmpVOs = new ArrayList<>();

    private FXMLLoader[] cellLoaders = new FXMLLoader[ROW_IN_PANE];
    private Node[] cells = new Node[ROW_IN_PANE];

    private Node pagePane;

    @FXML
    private VBox contentVBox;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    /**
     * 自动被调用
     */
    @FXML
    public void initialize() {

        staffVOs = new ArrayList<StaffVO>();
        salesmanVOs = new ArrayList<SalesmanVO>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/user/WorkerManagementSearchPane.fxml"));
            VBox pane = loader.load();

            workerManagementSearchPaneController = loader.getController();
            workerManagementSearchPaneController.setWorkerManagementListViewController(this);

            contentVBox.getChildren().add(pane);

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/component/user/WorkerManagementPagePane.fxml"));
            pagePane = pageLoader.load();

            workerManagementPaneController = pageLoader.getController();
            workerManagementPaneController.setWorkerManagementListViewController(this);

            for (int i = 0; i < ROW_IN_PANE; i++) {

                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/user/WorkerInfoCell.fxml"));
                HBox clientCell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = clientCell;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchSearchPane() {
        workerManagementSearchPaneController.showAllWorkers();
    }

    /**
     * 展示酒店工作人员
     */
    public void showStaff() {
        tmpVOs.clear();

        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        this.staffVOs = userBLService.search("");

        if (staffVOs.size() > 0) {
            tmpVOs.addAll(staffVOs);
        } else {
            System.out.println("no staff");
        }

        int size = this.staffVOs.size();
        workerManagementPaneController.setPageCount(size / ROW_IN_PANE + ((size % ROW_IN_PANE == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Staff");
        }
    }

    /**
     * 展示网站营销人员
     */
    public void showSalesman() {
        tmpVOs.clear();

        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        salesmanVOs = userBLService.search("");

        if (salesmanVOs.size() > 0) {
            tmpVOs.addAll(salesmanVOs);
        } else {
            System.out.println("no salesman");
        }

        int size = this.salesmanVOs.size();
        workerManagementPaneController.setPageCount(size / ROW_IN_PANE + ((size % ROW_IN_PANE == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Salesman");
        }

    }

    /**
     * 展示所有工作人员
     */
    public void showAllWorkers() {
        tmpVOs.clear();

        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        staffVOs = userBLService.search("");
        tmpVOs.addAll(staffVOs);

        userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        salesmanVOs = userBLService.search("");
        tmpVOs.addAll(salesmanVOs);

        int size = this.tmpVOs.size();
        workerManagementPaneController.setPageCount(size / ROW_IN_PANE + ((size % ROW_IN_PANE == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Worker");
        }
    }

    /**
     * 展示酒店工作人员详细信息
     *
     * @param staffVO
     */
    public void showStaffDetail(StaffVO staffVO) {
        workerManagementViewController.showStaffDetail(staffVO);
    }

    /**
     * 展示网站营销人员详细信息
     *
     * @param salesmanVO
     */
    public void showSalesmanDetail(SalesmanVO salesmanVO) {
        workerManagementViewController.showSalesmanDetail(salesmanVO);
    }

    /**
     * 添加工作人员
     */
    public void addWorker() {
        workerManagementViewController.addWorker();
    }

    public void turnPage(int page) {
        int fromIndex = (page - 1) * ROW_IN_PANE;
        int toIndex = Math.min(page * ROW_IN_PANE, tmpVOs.size());
        List<UserVO> tmpUserVOs = this.tmpVOs.subList(fromIndex, toIndex);
        setCells(tmpUserVOs);
    }

    private void setCells(List<UserVO> userVOs) {

        if (userVOs.size() > ROW_IN_PANE) {
            System.out.println("ERROR");
            return;
        }

        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        contentVBox.getChildren().remove(pagePane);

        for (int i = 0; i < userVOs.size(); i++) {

            UserVO userVO = userVOs.get(i);
            FXMLLoader loader = cellLoaders[i];
            Node ordercell = cells[i];

            WorkerManagementCellController workerManagementCellController = loader.getController();
            workerManagementCellController.setWorkerManagementListViewController(this);

            if (userVO instanceof StaffVO) {
                workerManagementCellController.setStaffVO((StaffVO) userVO);
            } else if (userVO instanceof SalesmanVO) {
                workerManagementCellController.setSalesmanVO((SalesmanVO) userVO);
            } else {
                continue;
            }

            contentVBox.getChildren().add(ordercell);
        }

        contentVBox.getChildren().add(pagePane);
    }
}
