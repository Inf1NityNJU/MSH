package ui.viewcontroller.manager;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vo.SalesmanVO;

/**
 * Created by Kray on 2016/11/26.
 */
public class SalesmanManagementDetailEditViewController {

    private SalesmanVO salesmanVO;

    private WorkerManagementViewController workerManagementViewController;

    @FXML
    private Label salesmanIDLabel;

    @FXML
    private TextField salesmanNameText;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    public void clickBackButton() {
        workerManagementViewController.back();
    }

    //TODO
    public void clickSaveButton() {

    }

    public void showSalesmanEdit(SalesmanVO salesmanVO) {
        this.salesmanVO = salesmanVO;

        salesmanIDLabel.setText(salesmanVO.salesmanID);
        salesmanNameText.setText(salesmanVO.salesmanName);
    }
}
