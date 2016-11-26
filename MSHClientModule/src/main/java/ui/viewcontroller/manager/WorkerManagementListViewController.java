package ui.viewcontroller.manager;

import bl.userbl.Salesman;
import bl.userbl.UserBLFactory;
import bl.userbl.UserBLServiceImpl;
import blservice.userblservice.UserBLService;
import blservice.userblservice.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.order.manager.SalesmanManagementCellController;
import ui.componentcontroller.order.manager.StaffManagementCellController;
import ui.componentcontroller.order.manager.WorkerManagementListPaneController;
import vo.SalesmanVO;
import vo.StaffVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/26.
 */
public class WorkerManagementListViewController {

    private WorkerManagementViewController workerManagementViewController;

    private UserBLService userBLService;

    private ArrayList<StaffVO> staffVOs;
    private ArrayList<SalesmanVO> salesmanVOs;

    // tmp
    private FXMLLoader[] cellLoaders = new FXMLLoader[]{};
    private Node[] cells = new Node[]{};

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
            loader.setLocation(Main.class.getResource("../component/manager/WorkerManagementListPane.fxml"));
            VBox pane = loader.load();

            WorkerManagementListPaneController controller = loader.getController();
            controller.setWorkerManagementListViewController(this);

            contentVBox.getChildren().add(pane);

            //TODO
            controller.showAllWorkers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStaff() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        //TODO
        staffVOs = userBLService.search("3");

        if (staffVOs.size() > 0) {

            cellLoaders = new FXMLLoader[staffVOs.size()];
            cells = new Node[staffVOs.size()];

            try {
                for (int i = 0; i < staffVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/manager/StaffInfoCell.fxml"));
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

    public void showSalesman() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        //TODO
        salesmanVOs = userBLService.search("1");

        if (salesmanVOs.size() > 0) {

            cellLoaders = new FXMLLoader[salesmanVOs.size()];
            cells = new Node[salesmanVOs.size()];

            try {
                for (int i = 0; i < salesmanVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/manager/SalesmanInfoCell.fxml"));
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
    public void showAllWorkers(){
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        //TODO
        staffVOs = userBLService.search("3");

        userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        //TODO
        salesmanVOs = userBLService.search("1");

        if (staffVOs.size() + salesmanVOs.size() > 0) {

            cellLoaders = new FXMLLoader[staffVOs.size() + salesmanVOs.size()];
            cells = new Node[staffVOs.size() + salesmanVOs.size()];

            System.out.println(staffVOs.size());
            System.out.println(salesmanVOs.size());

            try {
                for (int i = 0; i < staffVOs.size(); i++) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(Main.class.getResource("../component/manager/StaffInfoCell.fxml"));
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
                    cellLoader.setLocation(Main.class.getResource("../component/manager/SalesmanInfoCell.fxml"));
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

}
