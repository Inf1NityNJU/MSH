package ui.componentcontroller.promotion;

import component.circlebutton.CircleButton;
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

    private StateButton[] buttons;

    public void setWebPromotionListViewController(WebPromotionListViewController webPromotionListViewController) {
        this.webPromotionListViewController = webPromotionListViewController;

        buttons = new StateButton[]{allButton, clientGradeButton, placeButton, specialDateButton};
    }

    @FXML
    public void showAllPromotions(){
        setButtonsInactive();
        allButton.setIsActiveProperty(true);
        webPromotionListViewController.showAllWebPromotions();
    }

    @FXML
    public void showClientGradePromotions(){
        setButtonsInactive();
        clientGradeButton.setIsActiveProperty(true);
        webPromotionListViewController.showWebPromotionsByType(PromotionType.Web_ClientGrade);
    }

    @FXML
    public void showPlacePromotions(){
        setButtonsInactive();
        placeButton.setIsActiveProperty(true);
        webPromotionListViewController.showWebPromotionsByType(PromotionType.Web_SpecilPlace);
    }

    @FXML
    public void showSpecialDatePromotions(){
        setButtonsInactive();
        specialDateButton.setIsActiveProperty(true);
        webPromotionListViewController.showWebPromotionsByType(PromotionType.Web_SpecilaDate);
    }

    @FXML
    public void addPromotions(){
        setButtonsInactive();
        webPromotionListViewController.addPromotion();
    }

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }
}
