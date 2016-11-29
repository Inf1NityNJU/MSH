package ui.componentcontroller.promotion;

import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import ui.viewcontroller.salesman.WebPromotionListViewController;

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

    private void setButtonsInactive() {
        for (StateButton button : buttons) {
            button.setIsActiveProperty(false);
        }
    }
}
