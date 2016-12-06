package ui.viewcontroller.salesman;

import blservice.promotionblservice.PromotionBLService;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.PromotionType;
import vo.*;

/**
 * Created by vivian on 16/11/30.
 */
public class WebPromotionDetailViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService;

    @FXML
    private Label nameLabel;

    @FXML
    private StateButton typeButton;

    @FXML
    private Label clientGradeLabel;

    @FXML
    private Label placeLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private RectButton backButton;

    @FXML
    private RectButton deleteButton;

    @FXML
    private RectButton editButton;


    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController){
        this.webPromotionViewController = webPromotionViewController;
    }

    public void showWebPromotionDetail(PromotionVO promotionVO){
        this.promotionVO = promotionVO;

        nameLabel.setText(promotionVO.promotionName);
        typeButton.setText(promotionVO.promotionType.toString());

        Promotion_WebVO promotion_webVO = (Promotion_WebVO)promotionVO;
        timeLabel.setText(promotion_webVO.startDate.toString()+" - "+promotion_webVO.endDate.toString());

        if(promotionVO.promotionType== PromotionType.Web_ClientGrade){
            Promotion_ClientGradeVO promotion_clientGradeVO = (Promotion_ClientGradeVO)promotionVO;
            clientGradeLabel.setText(Integer.toString(promotion_clientGradeVO.clientGrade));
        }else{
            clientGradeLabel.setText("0");
        }

        if(promotionVO.promotionType== PromotionType.Web_SpecilPlace){
            Promotion_SpecialPlaceVO promotion_specialPlaceVO = (Promotion_SpecialPlaceVO)promotionVO;
            placeLabel.setText(promotion_specialPlaceVO.place.toString());
        }else{
            placeLabel.setText("No special place");
        }

    }

    @FXML
    public void clickBackButton(){
            webPromotionViewController.back();
    }

    @FXML
    public void clickDeleteButton(){
        promotionBLService.deletePromotion(promotionVO.promotionID);
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
    }

    @FXML
    public void clickEditButton(){
        webPromotionViewController.showPromotionDetailEditView(promotionVO);
    }
}
