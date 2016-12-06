package ui.componentcontroller.promotion;

import component.circlebutton.CircleButton;
import component.mychoicebox.MyChoiceBox;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import ui.viewcontroller.salesman.WebPromotionListViewController;
import util.PromotionType;

import java.util.Stack;

/**
 * Created by vivian on 16/11/29.
 */
public class WebPromotionSearchPaneController {
    private WebPromotionListViewController webPromotionListViewController;

    @FXML
    private StateButton allButton;

    @FXML
    private StateButton clientGradeButton;

    @FXML
    private StateButton placeButton;

    @FXML
    private StateButton specialDateButton;

    @FXML
    private CircleButton addButton;

    @FXML
    private MyChoiceBox typeChoiceBox;

    private StateButton[] buttons;

    public void setWebPromotionListViewController(WebPromotionListViewController webPromotionListViewController) {
        this.webPromotionListViewController = webPromotionListViewController;

        buttons = new StateButton[]{allButton, clientGradeButton, placeButton, specialDateButton};
    }

    @FXML
    public void showAllPromotions() {
        setButtonsInactive();
        allButton.setIsActiveProperty(true);
        webPromotionListViewController.showAllWebPromotions();
    }

    @FXML
    public void showClientGradePromotions() {
        setButtonsInactive();
        clientGradeButton.setIsActiveProperty(true);
        webPromotionListViewController.showWebPromotionsByType(PromotionType.Web_ClientGrade);
    }

    @FXML
    public void showPlacePromotions() {
        setButtonsInactive();
        placeButton.setIsActiveProperty(true);
        webPromotionListViewController.showWebPromotionsByType(PromotionType.Web_SpecilPlace);
    }

    @FXML
    public void showSpecialDatePromotions() {
        setButtonsInactive();
        specialDateButton.setIsActiveProperty(true);
        webPromotionListViewController.showWebPromotionsByType(PromotionType.Web_SpecilaDate);
    }

    @FXML
    public void clickAddButton() {
        PromotionType promotionType = null;
        setButtonsInactive();
        switch ((String) typeChoiceBox.getValue()) {
            case "会员等级折扣":
                promotionType = PromotionType.Web_ClientGrade;
                break;
            case "会员商圈折扣":
                promotionType = PromotionType.Web_SpecilPlace;
                break;
            case "特定期间折扣":
                promotionType = PromotionType.Web_SpecilaDate;
                break;
        }
        webPromotionListViewController.addPromotion(promotionType);
    }

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }
}
