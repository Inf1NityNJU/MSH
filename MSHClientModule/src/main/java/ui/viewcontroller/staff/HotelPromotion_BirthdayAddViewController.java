package ui.viewcontroller.staff;

import bl.hotelbl.Hotel;
import bl.userbl.UserBLFactory;
import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_BirthdayVO;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_BirthdayAddViewController {
    private PromotionVO promotionVO;
    private HotelPromotionViewController hotelPromotionViewController;
    private PromotionBLService promotionBLService;

    @FXML
    private CommonTextField nameTextField;

    @FXML
    private StateButton typeButton;

    @FXML
    private CommonTextField discountTextField;

    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;

        typeButton.setText(PromotionType.Hotel_Birthday.getType());
        typeButton.setColorProperty(PromotionType.Hotel_Birthday.getColor());
    }

    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    public void clickCancelButton(){
        hotelPromotionViewController.refreshWebPromotionList();
        hotelPromotionViewController.back();
    }

    public void clickSaveButton(){
        promotionVO = new Promotion_BirthdayVO(nameTextField.getText(),PromotionType.Hotel_Birthday, Double.valueOf(discountTextField.getText()),
                DataClass.hotelID);
        promotionBLService.addPromotion(promotionVO);

    }

    public void showEditView(PromotionVO promotionVO){
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount+"");
    }
}
