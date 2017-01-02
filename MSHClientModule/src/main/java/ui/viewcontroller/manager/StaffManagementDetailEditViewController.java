package ui.viewcontroller.manager;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.UserBLFactory;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.rectbutton.RectButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import vo.FilterFlagsVO;
import vo.Hotel_DetailVO;
import vo.StaffVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/26.
 */
public class StaffManagementDetailEditViewController {

    private StaffVO staffVO;

    private WorkerManagementViewController workerManagementViewController;
    private MainUIController mainUIController;

    @FXML
    private Label accountLabel;

    @FXML
    private Label staffIDLabel;

    @FXML
    private CommonTextField staffNameText;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private MyChoiceBox hotelChoiceBox;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    private ArrayList<Hotel_DetailVO> hotel_detailVOs = new ArrayList<>();
    private ObservableList observableList;

    public void setWorkerManagementViewController(WorkerManagementViewController workerManagementViewController) {
        this.workerManagementViewController = workerManagementViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void clickBackButton() {
        workerManagementViewController.back();
        workerManagementViewController.back();
        workerManagementViewController.showStaffDetail(staffVO);
    }

    public void clickSaveButton() {

        if (staffNameText.getText().equals("")) {
            showNotCompleteAlertView();
        } else {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/component/common/AlertView.fxml"));
                AnchorPane pane = loader.load();

                AlertViewController alertViewController = loader.getController();

                alertViewController.setInfoLabel("确定保存信息吗？");
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
    }

    public void showStaffEdit(StaffVO staffVO) {
        this.staffVO = staffVO;

        accountLabel.setText(staffVO.account);
        staffIDLabel.setText(staffVO.staffID);
        staffNameText.setText(staffVO.staffName);


        observableList = FXCollections.observableArrayList();

        HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
        hotel_detailVOs = hotelBLService.searchHotel(new FilterFlagsVO(null, null, "", null, 0, 0, null, null, 0, -1, 0, 0, null));

        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            observableList.add(hotel_detailVO.name);
        }

        hotelChoiceBox.setItems(observableList);

        if (staffVO.hotelID != null) {
            hotelBLService = new BLFactoryImpl().getHotelBLService();
            Hotel_DetailVO hotel_detailVO = hotelBLService.getHotel(staffVO.hotelID);
            String name = hotel_detailVO.name;
            hotelChoiceBox.getSelectionModel().select(name);
        }

    }

    private void sureSave() {
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();

        int index = hotelChoiceBox.getItems().indexOf(hotelChoiceBox.getValue());
        if (index >= 0) {
            String hotelID = this.hotel_detailVOs.get(index).ID;
            staffVO.hotelID = hotelID;
        }

        staffVO.staffName = staffNameText.getText();

        this.clickBackButton();
        mainUIController.hidePop();
        userBLService.update(staffVO);
    }

    private void showNotCompleteAlertView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/common/AlertView.fxml"));
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

    private void cancelSave() {
        mainUIController.hidePop();
    }
}
