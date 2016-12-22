package ui.viewcontroller.staff;

import blimpl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLInfo;
import component.navbutton.NavButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.StaffVO;

/**
 * Created by SilverNarcissus on 2016/11/27.
 *
 */
public class StaffNavbarController {

    private StaffViewController staffViewController;

    @FXML
    private Label nameLabel;

    @FXML
    private Label IDLabel;

    @FXML
    private NavButton hotelInfoButton;

    @FXML
    private NavButton roomInfoButton;

    @FXML
    private NavButton hotelOrderButton;

    @FXML
    private NavButton promotionButton;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();

    @FXML
    public void initialize() {
        String staffID = userBLInfo.getCurrentStaffID();
        if (staffID != null) {
            StaffVO staff = userBLInfo.getStaffByID(staffID);
            nameLabel.setText(staff.staffName);
            IDLabel.setText("编号：" + staffID);
        }

        hotelInfoButton.setIcon("\ue675");
        roomInfoButton.setIcon("\ue677");
        hotelOrderButton.setIcon("\ue673");
        promotionButton.setIcon("\ue676");
    }

    public void setStaffViewController(StaffViewController staffViewController) {
        this.staffViewController = staffViewController;
    }

    @FXML
    public void clickHotelInfoButton() {
        hotelInfoButton.setIsCurrentProperty(true);
        roomInfoButton.setIsCurrentProperty(false);
        hotelOrderButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(false);

        staffViewController.showHotelInfoView();
    }

    @FXML
    public void clickRoomInfoButton() {
        hotelInfoButton.setIsCurrentProperty(false);
        roomInfoButton.setIsCurrentProperty(true);
        hotelOrderButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(false);

        staffViewController.showRoomInfoList();
    }

    @FXML
    public void clickHotelOrderButton() {
        hotelInfoButton.setIsCurrentProperty(false);
        roomInfoButton.setIsCurrentProperty(false);
        hotelOrderButton.setIsCurrentProperty(true);
        promotionButton.setIsCurrentProperty(false);

        staffViewController.showHotelOrderList();
    }

    @FXML
    public void clickPromotionButton() {
        hotelInfoButton.setIsCurrentProperty(false);
        roomInfoButton.setIsCurrentProperty(false);
        hotelOrderButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(true);

        staffViewController.showHotelPromotionList();
    }
}
