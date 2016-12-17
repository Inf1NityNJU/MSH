package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.Hotel;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
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
import vo.Promotion_HotelSpecialDateVO;

import java.io.IOException;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_SpecialDateAddViewController extends HotelPromotionAddViewController{
    private PromotionVO promotionVO;
    private HotelPromotionViewController hotelPromotionViewController;
    private PromotionBLService promotionBLService;

    private boolean isEdit = false;
    private String promotionID = null;
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();

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
    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;

        typeButton.setText(PromotionType.Hotel_SpecilaDate.getType());
        typeButton.setColorProperty(PromotionType.Hotel_SpecilaDate.getColor());
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
        hotelPromotionViewController.back();
    }

    @Override
    public void clickSaveButton(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            alertViewController = loader.getController();
            alertViewController.setHotelPromotionAddViewController(this);
            alertViewController.setInfoLabel("确定保存该条酒店促销策略吗？");
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sureSave(){
        promotionVO = new Promotion_HotelSpecialDateVO(nameTextField.getText(),PromotionType.Hotel_SpecilaDate, Double.valueOf(discountTextField.getText()),
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()), userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID()));
        if(isEdit){
            promotionVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotionVO);
            System.out.println("update successfully!");
        }else {
            promotionBLService.addPromotion(promotionVO);
            System.out.println("save successfully!");
        }
        mainUIController.hidePop();
        hotelPromotionViewController.refreshHotelPromotionList();
        if(isEdit){
            hotelPromotionViewController.back();
        }
        hotelPromotionViewController.back();
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
