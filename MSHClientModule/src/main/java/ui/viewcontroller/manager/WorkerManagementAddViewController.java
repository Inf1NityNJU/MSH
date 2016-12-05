package ui.viewcontroller.manager;

import bl.blfactory.BLFactoryImpl;
import bl.userbl.UserBLFactory;
import blservice.hotelblservice.HotelBLService;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import po.StaffPO;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/28.
 */
public class WorkerManagementAddViewController {

    private WorkerManagementViewController workerManagementViewController;

    @FXML
    private CommonTextField accountText;

    @FXML
    private CommonTextField passwordText;

    @FXML
    private CommonTextField checkPasswordText;

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

        for(Hotel_DetailVO hotel_detailVO : hotel_detailVOs){
            observableList.add(hotel_detailVO.name);
        }

        hotelChoiceBox.setItems(observableList);
    }

    @FXML
    public void clickBackButton() {
        workerManagementViewController.back();
        workerManagementViewController.getWorkerManagementListViewController().showAllWorkers();
    }

    @FXML
    public void clickSaveButton() {
        if (staffButton.getIsActiveProperty()) {
            //存酒店工作人员
            if (accountText.getText().equals("") || nameText.getText().equals("")
                    || passwordText.getText().equals("") || checkPasswordText.getText().equals("")
                    || hotelChoiceBox.getValue() == null) {
                System.out.println("Not complete");
            } else {
                int index = hotelChoiceBox.getItems().indexOf(hotelChoiceBox.getValue());
                String hotelID = this.hotel_detailVOs.get(index).ID;
                UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
                userBLService.add(new StaffVO_Register(nameText.getText(), hotelID,
                        accountText.getText(), passwordText.getText()));

                clickBackButton();
            }
        } else if (salesmanButton.getIsActiveProperty()) {
            //存网站营销人员
            if (accountText.getText().equals("") || nameText.getText().equals("")
                    || passwordText.getText().equals("") || checkPasswordText.getText().equals("")) {
                System.out.println("Not complete");
            } else {
                UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
                userBLService.add(new SalesmanVO_Register(nameText.getText(), accountText.getText(), passwordText.getText()));

                clickBackButton();
            }
        } else {
            System.out.println("Not complete");
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
}
