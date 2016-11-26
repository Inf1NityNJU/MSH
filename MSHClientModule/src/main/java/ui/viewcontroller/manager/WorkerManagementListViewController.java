package ui.viewcontroller.manager;

import bl.userbl.Salesman;
import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import blservice.userblservice.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
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
    private FXMLLoader[] cellLoaders = new FXMLLoader[4];
    private Node[] cells = new Node[4];

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
//        System.out.println("Init ClientManagementViewController");

        //From DB
        userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        //From Stub
        userBLService = new UserBLService_Stub();
        staffVOs = new ArrayList<StaffVO>();
        salesmanVOs = new ArrayList<SalesmanVO>();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/manager/WorkerManagementListPane.fxml"));
            VBox pane = loader.load();

            WorkerManagementListPaneController controller = loader.getController();
            controller.setWorkerManagementListViewController(this);

            contentVBox.getChildren().add(pane);

            for (int i = 0; i < 4; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/manager/StaffInfoCell.fxml"));
                HBox clientCell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = clientCell;
            }

//            controller.showAllWorkers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStaff() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        //TODO
        staffVOs = userBLService.search("000");

        if (staffVOs.size() > 0) {

            for (int i = 0; i < staffVOs.size(); i++) {
                StaffVO staffVO = staffVOs.get(i);
                FXMLLoader loader = cellLoaders[i];
                Node clientCell = cells[i];

                /*
                ClientManagementCellController clientManagementCellController = loader.getController();
                clientManagementCellController.setClientManagementListViewController(this);
                clientManagementCellController.setClientVO(staffVO);
                */

                contentVBox.getChildren().add(clientCell);
            }
        } else {
            System.out.println("no staff");
        }

    }

    public void showSalesman() {
        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        //TODO
        salesmanVOs = userBLService.search("000");

        if (salesmanVOs.size() > 0) {

            for (int i = 0; i < salesmanVOs.size(); i++) {
                SalesmanVO salesmanVO = salesmanVOs.get(i);
                FXMLLoader loader = cellLoaders[i];
                Node clientCell = cells[i];

                /*
                ClientManagementCellController clientManagementCellController = loader.getController();
                clientManagementCellController.setClientManagementListViewController(this);
                clientManagementCellController.setClientVO(staffVO);
                */

//                contentVBox.getChildren().add(clientCell);
            }
        } else {
            System.out.println("no salesman");
        }

    }
}
