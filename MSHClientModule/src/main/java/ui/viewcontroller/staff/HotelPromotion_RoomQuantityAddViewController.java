package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
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

    private boolean isEdit = false;
    private String promotionID = null;
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();

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
        hotelPromotionViewController.refreshHotelPromotionList();
        if(isEdit){
            hotelPromotionViewController.back();
        }
        hotelPromotionViewController.back();
    }

    public void clickSaveButton(){
        promotionVO = new Promotion_RoomQuantityVO(nameTextField.getText(),PromotionType.Hotel_RoomQuantity, Double.valueOf(discountTextField.getText()),
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()),
                userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID()), Integer.valueOf(roomQuantityTextField.getText()));
        if(isEdit){
            promotionVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotionVO);
            System.out.println("update successfully!");
        }else {
            promotionBLService.addPromotion(promotionVO);
            System.out.println("save successfully!");
        }

    }

    public void showEditView(PromotionVO promotionVO){
        Promotion_RoomQuantityVO promotion_roomQuantityVO = (Promotion_RoomQuantityVO)promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount+"");
        roomQuantityTextField.setText(promotion_roomQuantityVO.roomQuantity+"");
        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
