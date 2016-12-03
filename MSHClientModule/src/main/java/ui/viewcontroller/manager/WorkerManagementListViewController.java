package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import po.StaffPO;
import ui.componentcontroller.user.SalesmanManagementCellController;
import ui.componentcontroller.user.StaffManagementCellController;
import ui.componentcontroller.user.WorkerManagementSearchPaneController;
import vo.SalesmanVO;
import vo.StaffVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by Kray on 2016/11/26.
 */
public class WorkerManagementListViewController {

    private static final int ROW_IN_PANE = 4;

    private WorkerManagementViewController workerManagementViewController;

    private UserBLService userBLService;

    private ArrayList<StaffVO> staffVOs;
    private ArrayList<SalesmanVO> salesmanVOs;

    private ArrayList<StaffVO> tmpStaffVOs;
    private ArrayList<SalesmanVO> tmpSalesmanVOs;

    // tmp
    private FXMLLoader[] cellLoaders = new FXMLLoader[ROW_IN_PANE];
    private Node[] cells = new Node[ROW_IN_PANE];

    private int type;

    private int currentPage;

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
        currentPage = 1;

        staffVOs = new ArrayList<StaffVO>();
        salesmanVOs = new ArrayList<SalesmanVO>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/user/WorkerManagementSearchPane.fxml"));
            VBox pane = loader.load();

            WorkerManagementSearchPaneController controller = loader.getController();
            controller.setWorkerManagementListViewController(this);

            contentVBox.getChildren().add(pane);

            //TODO
            controller.showAllWorkers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示酒店工作人员
     */
    public void showStaff() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        staffVOs = userBLService.search("");

        if (staffVOs.size() > 0) {

            cellLoaders = new FXMLLoader[staffVOs.size()];
            cells = new Node[staffVOs.size()];

            try {
                for (int i = 0; i < staffVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/user/StaffInfoCell.fxml"));
                    HBox clientCell = cellLoader.load();

                    cellLoaders[i] = cellLoader;
                    cells[i] = clientCell;

                    StaffVO staffVO = staffVOs.get(i);
                    FXMLLoader loader = cellLoaders[i];

                    StaffManagementCellController staffManagementCellController = loader.getController();
                    staffManagementCellController.setWorkerManagementListViewController(this);
                    staffManagementCellController.setStaffVO(staffVO);

                    contentVBox.getChildren().add(clientCell);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no staff");
        }

    }

    /**
     * 展示网站营销人员
     */
    public void showSalesman() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        salesmanVOs = userBLService.search("");

        if (salesmanVOs.size() > 0) {

            cellLoaders = new FXMLLoader[salesmanVOs.size()];
            cells = new Node[salesmanVOs.size()];

            try {
                for (int i = 0; i < salesmanVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/user/SalesmanInfoCell.fxml"));
                    HBox clientCell = cellLoader.load();

                    cellLoaders[i] = cellLoader;
                    cells[i] = clientCell;

                    SalesmanVO salesmanVO = salesmanVOs.get(i);
                    FXMLLoader loader = cellLoaders[i];

                    SalesmanManagementCellController salesmanManagementCellController = loader.getController();
                    salesmanManagementCellController.setWorkerManagementListViewController(this);
                    salesmanManagementCellController.setSalesmanVO(salesmanVO);

                    contentVBox.getChildren().add(clientCell);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no salesman");
        }

    }

    //TODO

    /**
     * 展示所有工作人员
     */
    public void showAllWorkers() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        staffVOs = userBLService.search("");

        userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        salesmanVOs = userBLService.search("");

        if (staffVOs.size() + salesmanVOs.size() > 0) {

            cellLoaders = new FXMLLoader[staffVOs.size() + salesmanVOs.size()];
            cells = new Node[staffVOs.size() + salesmanVOs.size()];

            System.out.println(staffVOs.size());
            System.out.println(salesmanVOs.size());

            try {
                for (int i = 0; i < staffVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/user/StaffInfoCell.fxml"));
                    HBox clientCell = cellLoader.load();

                    cellLoaders[i] = cellLoader;
                    cells[i] = clientCell;

                    StaffVO staffVO = staffVOs.get(i);
                    FXMLLoader loader = cellLoaders[i];

                    StaffManagementCellController staffManagementCellController = loader.getController();
                    staffManagementCellController.setWorkerManagementListViewController(this);
                    staffManagementCellController.setStaffVO(staffVO);

                    contentVBox.getChildren().add(clientCell);
                }
                for (int i = staffVOs.size(); i < staffVOs.size() + salesmanVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/user/SalesmanInfoCell.fxml"));
                    HBox clientCell = cellLoader.load();

                    cellLoaders[i] = cellLoader;
                    cells[i] = clientCell;

                    SalesmanVO salesmanVO = salesmanVOs.get(i - staffVOs.size());
                    FXMLLoader loader = cellLoaders[i];

                    SalesmanManagementCellController salesmanManagementCellController = loader.getController();
                    salesmanManagementCellController.setWorkerManagementListViewController(this);
                    salesmanManagementCellController.setSalesmanVO(salesmanVO);

                    contentVBox.getChildren().add(clientCell);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no worker");
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
//        int fromIndex = (page - 1) * ROW_IN_PANE;
//        int toIndex = Math.min(page * ROW_IN_PANE, tmpStaffVOs.size() + tmpSalesmanVOs.size());
//        List<ClientVO> tmpClientVOs = this.tmpVOs.subList(fromIndex, toIndex);
//        setCells(tmpClientVOs);
    }
}
