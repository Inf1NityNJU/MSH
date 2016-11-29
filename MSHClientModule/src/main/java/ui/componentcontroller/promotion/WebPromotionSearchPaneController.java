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

    public void setWebPromotionListViewController(WebPromotionListViewController webPromotionListViewController) {
        this.webPromotionListViewController = webPromotionListViewController;

//        buttons = new StateButton[]{allButton, normalClientButton, enterpriseClientButton};
    }

    @FXML
    public void showAllPromotions(){

    }
}
