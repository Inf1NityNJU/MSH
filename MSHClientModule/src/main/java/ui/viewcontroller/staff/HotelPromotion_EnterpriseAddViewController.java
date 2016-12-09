package ui.viewcontroller.staff;

import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_EnterpriseVO;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_EnterpriseAddViewController {
    private PromotionVO promotionVO;
    private HotelPromotionViewController hotelPromotionViewController;
    private PromotionBLService promotionBLService;

    @FXML
    private CommonTextField nameTextField;

    @FXML
    private StateButton typeButton;

    @FXML
    private CommonTextField discountTextField;

    @FXML
    private MyDatePicker startTime;

    @FXML
    private MyDatePicker endTime;

    @FXML
    private CommonTextField enterpriseTextField;


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
        promotionVO = new Promotion_EnterpriseVO(nameTextField.getText(),PromotionType.Hotel_Birthday, Double.valueOf(discountTextField.getText()),
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()),
                enterpriseTextField.getText(), DataClass.hotelID);
        promotionBLService.addPromotion(promotionVO);

    }

    public void showEditView(PromotionVO promotionVO){
        Promotion_EnterpriseVO promotion_enterpriseVO = (Promotion_EnterpriseVO) promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount+"");
        enterpriseTextField.setText(promotion_enterpriseVO.enterpriseName+"");
    }
}
