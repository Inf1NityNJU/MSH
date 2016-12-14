package ui.viewcontroller.salesman;

import blservice.promotionblservice.PromotionBLService;
import ui.viewcontroller.common.MainUIController;
import vo.PromotionVO;

/**
 * Created by vivian on 16/12/14.
 */
public abstract class WebPromotionAddViewController {
    public void setPromotionBLService(PromotionBLService promotionBLService){}

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController){}

    public void setMainUIController(MainUIController mainUIController){}

    public void clickCancelButton(){}

    public void clickSaveButton(){}

    public void sureSave(){}

    public void cancelSave(){}
}
