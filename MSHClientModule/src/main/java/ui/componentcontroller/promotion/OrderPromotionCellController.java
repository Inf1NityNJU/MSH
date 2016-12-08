package ui.componentcontroller.promotion;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.PromotionVO;

/**
 * Created by Sorumi on 16/12/2.
 */
public class OrderPromotionCellController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label discountLabel;

    public void setPromotion(PromotionVO promotion) {
        nameLabel.setText(promotion.promotionName);
        discountLabel.setText(promotion.promotionDiscount*10 + " 折");
    }

}
