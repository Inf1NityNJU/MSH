package ui.componentcontroller.user;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.manager.WorkerManagementListViewController;
import vo.SalesmanVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class SalesmanManagementCellController {

    private SalesmanVO salesmanVO;

    private WorkerManagementListViewController workerManagementListViewController;

    @FXML
    private Label salesmanNameLabel;

    @FXML
    private Label salesmanIDLabel;

    @FXML
    private RectButton detailButton;

    @FXML
    public void clickDetailButton() {
        System.out.println("Salesman Detail");

        workerManagementListViewController.showSalesmanDetail(salesmanVO);
    }

    public void setWorkerManagementListViewController(WorkerManagementListViewController workerManagementListViewController) {
        this.workerManagementListViewController = workerManagementListViewController;
    }

    public void setSalesmanVO(SalesmanVO salesmanVO) {
        this.salesmanVO = salesmanVO;

        salesmanNameLabel.setText(salesmanVO.salesmanName);
        salesmanIDLabel.setText(salesmanVO.salesmanID);
    }
}
