package ui.componentcontroller.promotion;

import bl.hotelbl.Hotel;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.staff.HotelPromotionListViewController;
import vo.PromotionVO;

/**
 * Created by vivian on 16/12/8.
 */
public class HotelPromotionCellController {
    private PromotionVO promotionVO;

    private HotelPromotionListViewController hotelPromotionListViewController;

    @FXML
    private Label promotionNameLabel;

    @FXML
    private StateButton promotionType;

    @FXML
    private Label promotionDiscountLabel;

    @FXML
    private RectButton detailButton;

    public void setHotelPromotionListViewController(HotelPromotionListViewController hotelPromotionListViewController) {
        this.hotelPromotionListViewController = hotelPromotionListViewController;
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
        hotelPromotionListViewController.showPromotionDetail(promotionVO);
    }
}
