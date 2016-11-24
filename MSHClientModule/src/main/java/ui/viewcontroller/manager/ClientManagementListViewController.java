package ui.viewcontroller.manager;

import blservice.userblservice.UserBLService;
import blservice.userblservice.UserBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.order.manager.ClientManagementListPaneController;

import java.io.IOException;

/**
 * Created by Kray on 2016/11/24.
 */
public class ClientManagementListViewController {

    /**
     * 客户列表VC
     */
    private ClientManagementViewController clientManagementViewController;

    private UserBLService userBLService;

    @FXML
    private VBox contentVBox;

    public void setClientManagementViewController(ClientManagementViewController clientManagementViewController) {
        this.clientManagementViewController = clientManagementViewController;
    }

    /**
     * 自动被调用
     */
    @FXML
    public void initialize() {
        System.out.println("Init ClientManagementViewController");

        userBLService = new UserBLService_Stub();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/manager/ClientManagementListPane.fxml"));
            VBox pane = loader.load();

            ClientManagementListPaneController controller = loader.getController();
            controller.setClientManagementListViewController(this);

            contentVBox.getChildren().add(pane);

//            for (int i=0; i<4; i++) {
//                FXMLLoader cellLoader = new FXMLLoader();
//                cellLoader.setLocation(Main.class.getResource("../component/order/OrderCell.fxml"));
//                HBox ordercell = cellLoader.load();
//
//                cellLoaders[i] = cellLoader;
//                cells[i] = ordercell;
//            }
//
//            controller.showAllOrders();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
