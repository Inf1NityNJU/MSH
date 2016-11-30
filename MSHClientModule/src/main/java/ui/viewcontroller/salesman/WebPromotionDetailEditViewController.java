package ui.viewcontroller.salesman;

import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import vo.PromotionVO;

/**
 * Created by vivian on 16/11/30.
 */
public class WebPromotionDetailEditViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;

    @FXML
    private Label nameLabel;

    @FXML
    private StateButton typeButton;

    @FXML
    private ChoiceBox levelChoiceBox;

    @FXML
    private ChoiceBox cityChoiceBox;

    @FXML
    private ChoiceBox placeChoiceBox;

    @FXML
    private Label startTimeLabel;

    @FXML
    private Label endTimeLabel;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController){
        this.webPromotionViewController = webPromotionViewController;
    }

}
