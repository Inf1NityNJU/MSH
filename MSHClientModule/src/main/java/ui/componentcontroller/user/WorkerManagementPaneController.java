package ui.componentcontroller.user;

import component.pagepane.PagePane;
import javafx.fxml.FXML;
import ui.viewcontroller.manager.WorkerManagementListViewController;

/**
 * Created by Kray on 2016/12/3.
 */
public class WorkerManagementPaneController {

    private WorkerManagementListViewController workerManagementListViewController;

    public void setWorkerManagementListViewController(WorkerManagementListViewController workerManagementListViewController) {
        this.workerManagementListViewController = workerManagementListViewController;
    }

    @FXML
    private PagePane pagePane;

    @FXML
    private void pageChanged() {
        workerManagementListViewController.turnPage(pagePane.getCurrentPage());
    }

    public void setPageCount(int count) {
        pagePane.setCount(count);
    }
}
