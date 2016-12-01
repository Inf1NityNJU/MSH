package ui.viewcontroller.salesman;

import blservice.promotionblservice.PromotionBLService;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_ClientGradeVO;

import static util.PromotionType.Web_ClientGrade;

/**
 * Created by vivian on 16/11/30.
 */
public class WebPromotionDetailEditViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService;

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

    public void clickCancelButton(){
        webPromotionViewController.back();
    }

    // TODO: 16/12/1 need to discuss the diferrence between ui and logic
    public void clickSaveButton(){
    }

    public void show(String type){
        typeButton.setText(type);
    }

}
