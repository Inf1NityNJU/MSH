package ui.viewcontroller.staff;

import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_RoomQuantityVO;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_RoomQuantityAddViewController {
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
    private CommonTextField roomQuantityTextField;


    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;

        typeButton.setText(PromotionType.Hotel_RoomQuantity.getType());
        typeButton.setColorProperty(PromotionType.Hotel_RoomQuantity.getColor());
    }

    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    public void clickCancelButton(){
        hotelPromotionViewController.refreshWebPromotionList();
        hotelPromotionViewController.back();
    }

    public void clickSaveButton(){
        promotionVO = new Promotion_RoomQuantityVO(nameTextField.getText(),PromotionType.Hotel_Birthday, Double.valueOf(discountTextField.getText()),
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()),
                DataClass.hotelID, Integer.valueOf(roomQuantityTextField.getText()));
        promotionBLService.addPromotion(promotionVO);

    }

    public void showEditView(PromotionVO promotionVO){
        Promotion_RoomQuantityVO promotion_roomQuantityVO = (Promotion_RoomQuantityVO)promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount+"");
        roomQuantityTextField.setText(promotion_roomQuantityVO.roomQuantity+"");
    }
}
