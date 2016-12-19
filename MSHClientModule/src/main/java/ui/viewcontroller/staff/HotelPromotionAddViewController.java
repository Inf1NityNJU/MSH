package ui.viewcontroller.staff;

import blservice.promotionblservice.PromotionBLService;
import ui.viewcontroller.common.MainUIController;

/**
 * Created by vivian on 16/12/14.
 */
public abstract class HotelPromotionAddViewController {
    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController){}

    public void setMainUIController(MainUIController mainUIController){}

    public void clickCancelButton(){}

    public void clickSaveButton(){}

    public void sureSave(){}

    public void cancelSave(){}
}
