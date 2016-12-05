package ui.componentcontroller.promotion;

import bl.promotionbl.Promotion;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.salesman.WebPromotionListViewController;
import vo.PromotionVO;

/**
 * Created by vivian on 16/11/29.
 */
public class WebPromotionCellController {
    private PromotionVO promotionVO;

    private WebPromotionListViewController webPromotionListViewController;

    @FXML
    private Label promotionNameLabel;

    @FXML
    private StateButton promotionType;

    @FXML
    private Label promotionDiscountLabel;

    @FXML
    private RectButton detailButton;

    public void setWebPromotionListViewController(WebPromotionListViewController webPromotionListViewController) {
        this.webPromotionListViewController = webPromotionListViewController;
    }

    public void setPromotionVO(PromotionVO promotionVO){
        this.promotionVO = promotionVO;

        promotionNameLabel.setText(promotionVO.promotionName);
        promotionType.setText(promotionVO.promotionType.getType());
        promotionType.setColorProperty(promotionVO.promotionType.getColor());
        promotionDiscountLabel.setText(Double.toString(promotionVO.promotionDiscount));
    }

    @FXML
    public void clickDetailButton(){
        webPromotionListViewController.showPromotionDetail(promotionVO);
    }
}
