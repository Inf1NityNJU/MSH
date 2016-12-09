package ui.componentcontroller.promotion;

import component.circlebutton.CircleButton;
import component.mychoicebox.MyChoiceBox;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import ui.viewcontroller.staff.HotelPromotionListViewController;
import util.PromotionType;

/**
 * Created by vivian on 16/12/8.
 */
public class HotelPromotionSearchPaneController {
    private HotelPromotionListViewController hotelPromotionListViewController;

    @FXML
    private StateButton allButton;

    @FXML
    private StateButton birthdayButton;

    @FXML
    private StateButton roomQuantityButton;

    @FXML
    private StateButton companyButton;

    @FXML
    private StateButton specialDateButton;

    @FXML
    private CircleButton addButton;

    @FXML
    private MyChoiceBox typeChoiceBox;

    private StateButton[] buttons;

    public void setHotelPromotionListViewController(HotelPromotionListViewController hotelPromotionListViewController) {
        this.hotelPromotionListViewController = hotelPromotionListViewController;

        buttons = new StateButton[]{allButton, birthdayButton, roomQuantityButton, companyButton, specialDateButton};
    }

    @FXML
    public void showAllPromotions() {
        setButtonsInactive();
        allButton.setIsActiveProperty(true);
        hotelPromotionListViewController.showAllHotelPromotions();
    }

    @FXML
    public void showBirthdayPromotions() {
        setButtonsInactive();
        birthdayButton.setIsActiveProperty(true);
        hotelPromotionListViewController.showHotelPromotionsByType(PromotionType.Hotel_Birthday);
    }

    @FXML
    public void showRoomQuantityPromotions() {
        setButtonsInactive();
        roomQuantityButton.setIsActiveProperty(true);
        hotelPromotionListViewController.showHotelPromotionsByType(PromotionType.Hotel_RoomQuantity);
    }

    @FXML
    public void showCompanyPromotions() {
        setButtonsInactive();
        companyButton.setIsActiveProperty(true);
        hotelPromotionListViewController.showHotelPromotionsByType(PromotionType.Hotel_Enterprise);
    }

    @FXML
    public void showSpecialDatePromotions() {
        setButtonsInactive();
        specialDateButton.setIsActiveProperty(true);
        hotelPromotionListViewController.showHotelPromotionsByType(PromotionType.Hotel_SpecilaDate);
    }
//
//    @FXML
//    public void clickAddButton() {
//        PromotionType promotionType = null;
//        setButtonsInactive();
//        switch ((String) typeChoiceBox.getValue()) {
//            case "会员等级折扣":
//                promotionType = PromotionType.Web_ClientGrade;
//                break;
//            case "会员商圈折扣":
//                promotionType = PromotionType.Web_SpecilPlace;
//                break;
//            case "特定期间折扣":
//                promotionType = PromotionType.Web_SpecilaDate;
//                break;
//        }
//        webPromotionListViewController.addPromotion(promotionType);
//    }

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }
}
