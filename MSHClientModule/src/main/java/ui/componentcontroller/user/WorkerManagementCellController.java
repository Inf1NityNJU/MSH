package ui.componentcontroller.user;

import blimpl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLService;
import component.circleimage.CircleImage;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.manager.WorkerManagementListViewController;
import vo.Hotel_DetailVO;
import vo.SalesmanVO;
import vo.StaffVO;

/**
 * Created by Kray on 2016/12/4.
 */
public class WorkerManagementCellController {

    private StaffVO staffVO;
    private SalesmanVO salesmanVO;

    private WorkerManagementListViewController workerManagementListViewController;

    @FXML
    private CircleImage avatarImage;

    @FXML
    private Label workerNameLabel;

    @FXML
    private Label workerIDLabel;

    @FXML
    private Label staffHotelLabel;

    @FXML
    private Label hotelLabel;

    @FXML
    private RectButton detailButton;

    @FXML
    private StateButton typeButton;

    @FXML
    public void clickDetailButton() {
        if(staffVO != null) {
            workerManagementListViewController.showStaffDetail(staffVO);
        }else{
            workerManagementListViewController.showSalesmanDetail(salesmanVO);
        }
    }

    public void setWorkerManagementListViewController(WorkerManagementListViewController workerManagementListViewController) {
        this.workerManagementListViewController = workerManagementListViewController;
    }

    public void setStaffVO(StaffVO staffVO) {
        this.staffVO = staffVO;
        this.salesmanVO = null;

        workerNameLabel.setText(staffVO.staffName);
        workerIDLabel.setText(staffVO.staffID);

        HotelBLService hotelBLService = new BLFactoryImpl().getHotelBLService();
        if (staffVO.hotelID != null) {
            Hotel_DetailVO hotel = hotelBLService.getHotel(staffVO.hotelID);
            if (hotel != null) {
                staffHotelLabel.setText(hotel.name);

            } else {
                staffHotelLabel.setText("无");
            }
        } else {
            staffHotelLabel.setText("无");

        }

        staffHotelLabel.setVisible(true);
        hotelLabel.setVisible(true);

        typeButton.setText("酒店工作人员");
        typeButton.setColorProperty("12B7F3");
    }

    public void setSalesmanVO(SalesmanVO salesmanVO) {
        this.salesmanVO = salesmanVO;
        this.staffVO = null;

        workerNameLabel.setText(salesmanVO.salesmanName);
        workerIDLabel.setText(salesmanVO.salesmanID);

        staffHotelLabel.setText("");
        staffHotelLabel.setVisible(false);
        hotelLabel.setVisible(false);

        typeButton.setText("网站营销人员");
        typeButton.setColorProperty("BC52FD");
    }
}
