package ui.viewcontroller.salesman;

import blservice.promotionblservice.PromotionBLService;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.PromotionType;
import vo.*;

import java.io.IOException;

/**
 * Created by vivian on 16/11/30.
 */
public class WebPromotionDetailViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService;

    private MainUIController mainUIController;
    private AlertViewController alertViewController;

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

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void showWebPromotionDetail(PromotionVO promotionVO){
        this.promotionVO = promotionVO;

        nameLabel.setText(promotionVO.promotionName);
        typeButton.setText(promotionVO.promotionType.getType());
        typeButton.setColorProperty(promotionVO.promotionType.getColor());

        Promotion_WebVO promotion_webVO = (Promotion_WebVO)promotionVO;
        timeLabel.setText(promotion_webVO.startDate.toString()+" - "+promotion_webVO.endDate.toString());

        if(promotionVO.promotionType== PromotionType.Web_ClientGrade){
            Promotion_ClientGradeVO promotion_clientGradeVO = (Promotion_ClientGradeVO)promotionVO;
            clientGradeLabel.setText(Integer.toString(promotion_clientGradeVO.clientGrade));
        }else{
            clientGradeLabel.setText("1");
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
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            alertViewController = loader.getController();
            alertViewController.setWebPromotionDetailViewController(this);
            alertViewController.setInfoLabel("确认删除该条网站促销策略吗？");
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickEditButton(){
        webPromotionViewController.showPromotionDetailEditView(promotionVO);
    }

    public void sureDelete(){
        promotionBLService.deletePromotion(promotionVO.promotionID);
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
        mainUIController.hidePop();
    }

    public void cancelDelete(){
        mainUIController.hidePop();
    }
}
