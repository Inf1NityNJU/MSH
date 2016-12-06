package ui.viewcontroller.salesman;

import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_WebSpecialDateVO;

/**
 * Created by vivian on 16/12/6.
 */
public class WebPromotion_SpecialDateAddViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
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

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;

        typeButton.setText(PromotionType.Web_SpecilaDate.getType());
        typeButton.setColorProperty(PromotionType.Web_SpecilaDate.getColor());
    }

    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    public void clickCancelButton(){
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
    }

    public void clickSaveButton(){
        promotionVO = new Promotion_WebSpecialDateVO(nameTextField.getText(),PromotionType.Web_SpecilaDate, Double.valueOf(discountTextField.getText()),
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()));
        promotionBLService.addPromotion(promotionVO);

    }
}
