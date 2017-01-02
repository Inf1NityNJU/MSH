package ui.viewcontroller.manager;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.UserBLFactory;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLService;
import component.commonpasswordfield.CommonPasswordField;
import component.commontextfield.CommonTextField;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import vo.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/28.
 */
public class WorkerManagementAddViewController {

    private WorkerManagementViewController workerManagementViewController;
    private MainUIController mainUIController;

    @FXML
    private CommonTextField accountText;

    @FXML
    private CommonPasswordField passwordText;

    @FXML
    private CommonPasswordField checkPasswordText;

    @FXML
    private CommonTextField nameText;

    @FXML
    private StateButton staffButton;

    @FXML
    private StateButton salesmanButton;

    @FXML
    private Label hotelLabel;

    @FXML
    private ChoiceBox hotelChoiceBox;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    private ArrayList<Hotel_DetailVO> hotel_detailVOs = new ArrayList<>();
    private ObservableList observableList;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;

        hotelLabel.setVisible(false);
        hotelChoiceBox.setVisible(false);

        observableList = FXCollections.observableArrayList();

        HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
        hotel_detailVOs = hotelBLService.searchHotel(new FilterFlagsVO(null, null, "", null, 0, 0, null, null, 0, -1, 0, 0, null));

        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            observableList.add(hotel_detailVO.name);
        }

        hotelChoiceBox.setItems(observableList);
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    @FXML
    public void clickBackButton() {
        workerManagementViewController.back();
        workerManagementViewController.getWorkerManagementListViewController().refreshList();
    }

    private void showNotCompleteAlertView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();

            alertViewController.setInfoLabel("工作人员信息不完整!");
            alertViewController.hideLeftButton();
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    cancelSave();
                }
            });
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickSaveButton() {
        if (staffButton.getIsActiveProperty()) {
            //存酒店工作人员
            if (accountText.getText().equals("") || nameText.getText().equals("")
                    || passwordText.getText().equals("") || checkPasswordText.getText().equals("")
                    || hotelChoiceBox.getValue() == null) {

                showNotCompleteAlertView();

            } else {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
                    AnchorPane pane = loader.load();

                    AlertViewController alertViewController = loader.getController();

                    alertViewController.setInfoLabel("确定保存新的酒店工作人员信息吗？");
                    alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                        @Override
                        public void handle(Event event) {
                            sureSave();
                        }
                    });
                    alertViewController.setOnClickCancelButton(new EventHandler<Event>() {
                        @Override
                        public void handle(Event event) {
                            cancelSave();
                        }
                    });
                    mainUIController.showPop(pane);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (salesmanButton.getIsActiveProperty()) {
            //存网站营销人员
            if (accountText.getText().equals("") || nameText.getText().equals("")
                    || passwordText.getText().equals("") || checkPasswordText.getText().equals("")) {

                showNotCompleteAlertView();

            } else {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
                    AnchorPane pane = loader.load();

                    AlertViewController alertViewController = loader.getController();

                    alertViewController.setInfoLabel("确定保存新的网站营销人员信息吗？");
                    alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                        @Override
                        public void handle(Event event) {
                            sureSave();
                        }
                    });
                    alertViewController.setOnClickCancelButton(new EventHandler<Event>() {
                        @Override
                        public void handle(Event event) {
                            cancelSave();
                        }
                    });
                    mainUIController.showPop(pane);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            showNotCompleteAlertView();
        }
    }

    @FXML
    public void clickStaffButton() {
        staffButton.setIsActiveProperty(true);
        salesmanButton.setIsActiveProperty(false);

        hotelLabel.setVisible(true);
        hotelChoiceBox.setVisible(true);
    }

    @FXML
    public void clickSalesmanButton() {
        staffButton.setIsActiveProperty(false);
        salesmanButton.setIsActiveProperty(true);

        hotelLabel.setVisible(false);
        hotelChoiceBox.setVisible(false);
    }

    private void sureSave() {
        if (staffButton.getIsActiveProperty()) {
            int index = hotelChoiceBox.getItems().indexOf(hotelChoiceBox.getValue());
            String hotelID = this.hotel_detailVOs.get(index).ID;
            UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
            userBLService.add(new StaffVO_Register(nameText.getText(), hotelID,
                    accountText.getText(), passwordText.getText()));

            clickBackButton();
        } else if (salesmanButton.getIsActiveProperty()) {
            UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
            userBLService.add(new SalesmanVO_Register(nameText.getText(), accountText.getText(), passwordText.getText()));

            clickBackButton();
        }

        mainUIController.hidePop();
    }

    private void cancelSave() {
        mainUIController.hidePop();
    }
}
