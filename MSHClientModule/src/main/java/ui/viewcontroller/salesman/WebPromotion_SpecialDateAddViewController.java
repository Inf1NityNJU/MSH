package ui.viewcontroller.salesman;

import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_WebSpecialDateVO;

import java.io.IOException;

/**
 * Created by vivian on 16/12/6.
 */
public class WebPromotion_SpecialDateAddViewController extends WebPromotionAddViewController{
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService;

    private boolean isEdit = false;
    private String promotionID = null;

    private MainUIController mainUIController;
    private AlertViewController alertViewController;

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

    @Override
    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;

        typeButton.setText(PromotionType.Web_SpecilaDate.getType());
        typeButton.setColorProperty(PromotionType.Web_SpecilaDate.getColor());
    }

    @Override
    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    @Override
    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    @Override
    public void clickCancelButton(){
        webPromotionViewController.back();
    }

    @Override
    public void clickSaveButton(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            alertViewController = loader.getController();
            alertViewController.setWebPromotionAddViewController(this);
            alertViewController.setInfoLabel("确定保存该条网站促销策略吗？");
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sureSave(){
        promotionVO = new Promotion_WebSpecialDateVO(nameTextField.getText(),PromotionType.Web_SpecilaDate, Double.valueOf(discountTextField.getText()),
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()));
        if(isEdit){
            promotionVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotionVO);
            System.out.println("update successfully!");
        }else {
            promotionBLService.addPromotion(promotionVO);
            System.out.println("save successfully!");
        }
        mainUIController.hidePop();
        webPromotionViewController.refreshWebPromotionList();
        if (isEdit) {
            webPromotionViewController.back();
        }
        webPromotionViewController.back();
    }

    @Override
    public void cancelSave(){
        mainUIController.hidePop();
    }

    public void showEditView(PromotionVO promotionVO){
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount+"");
        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
