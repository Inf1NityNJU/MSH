package ui.viewcontroller.staff;

import component.navbutton.NavButton;
import javafx.fxml.FXML;
import ui.viewcontroller.client.ClientViewController;

/**
 * Created by SilverNarcissus on 2016/11/27.
 *
 */
public class StaffNavbarController {
    private StaffViewController staffViewController;

    @FXML
    private NavButton hotelInfoButton;

    @FXML
    private NavButton roomInfoButton;

    @FXML
    private NavButton hotelOrderButton;

    @FXML
    private NavButton promotionButton;

    public void setStaffViewController(StaffViewController staffViewController) {
        this.staffViewController = staffViewController;
    }

    @FXML
    public void clickHotelInfoButton() {
        hotelInfoButton.setIsCurrentProperty(true);
        roomInfoButton.setIsCurrentProperty(false);
        hotelOrderButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(false);
    }

    @FXML
    public void clickRoomInfoButton() {
        hotelInfoButton.setIsCurrentProperty(false);
        roomInfoButton.setIsCurrentProperty(true);
        hotelOrderButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(false);
    }

    @FXML
    public void clickHotelOrderButton() {
        hotelInfoButton.setIsCurrentProperty(false);
        roomInfoButton.setIsCurrentProperty(false);
        hotelOrderButton.setIsCurrentProperty(true);
        promotionButton.setIsCurrentProperty(false);
    }

    @FXML
    public void clickPromotionButton() {
        hotelInfoButton.setIsCurrentProperty(false);
        roomInfoButton.setIsCurrentProperty(false);
        hotelOrderButton.setIsCurrentProperty(false);
        promotionButton.setIsCurrentProperty(true);
    }
}
