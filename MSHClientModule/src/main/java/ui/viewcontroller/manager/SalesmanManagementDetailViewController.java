package ui.viewcontroller.manager;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.SalesmanVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class SalesmanManagementDetailViewController {

    private SalesmanVO salesmanVO;

    private WorkerManagementViewController workerManagementViewController;

    @FXML
    private Label salesmanIDLabel;

    @FXML
    private Label salesmanNameLabel;

    @FXML
    private RectButton passwordButton;

    @FXML
    private RectButton backButton;

    @FXML
    private RectButton deleteButton;

    @FXML
    private RectButton editButton;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    public void showSalesman(SalesmanVO salesmanVO){
        this.salesmanVO = salesmanVO;

        salesmanIDLabel.setText(salesmanVO.salesmanID);
        salesmanNameLabel.setText(salesmanVO.salesmanName);
    }

    public void clickBackButton() {
        workerManagementViewController.back();
    }

    //TODO
    public void clickPasswordButton() {
        System.out.println("CHANGE PW");
    }

    public void clickDeleteButton() {
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        userBLService.delete(salesmanVO.salesmanID);

        clickBackButton();
    }

    public void clickEditButton() {
        workerManagementViewController.editSalesmanDetail(salesmanVO);
    }

}
