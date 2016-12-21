package ui.componentcontroller.promotion;

import component.statebutton.StateButton;
import component.tinybutton.TinyButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.PromotionVO;

/**
 * Created by vivian on 16/12/8.
 */
public class HotelPromotionCellController {
    private PromotionVO promotionVO;

    private HotelPromotionPagePaneController hotelPromotionPagePaneController;

    @FXML
    private Label promotionNameLabel;

    @FXML
    private StateButton promotionType;

    @FXML
    private Label promotionDiscountLabel;

    @FXML
    private TinyButton detailButton;

    public void setHotelPromotionPagePaneController(HotelPromotionPagePaneController hotelPromotionPagePaneController) {
        this.hotelPromotionPagePaneController = hotelPromotionPagePaneController;
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
        hotelPromotionPagePaneController.showPromotionDetail(promotionVO);
    }
}
