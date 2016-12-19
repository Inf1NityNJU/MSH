package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
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
import vo.Promotion_EnterpriseVO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_EnterpriseAddViewController extends HotelPromotionAddViewController {
    private Promotion_EnterpriseVO promotion_enterpriseVO;
    private HotelPromotionViewController hotelPromotionViewController;

    private boolean isEdit = false;
    private String promotionID = null;

    private PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();
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

    @FXML
    private CommonTextField enterpriseTextField;


    @Override
    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;

        typeButton.setText(PromotionType.Hotel_Enterprise.getType());
        typeButton.setColorProperty(PromotionType.Hotel_Enterprise.getColor());
    }

    @Override
    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    @Override
    public void clickCancelButton() {
        hotelPromotionViewController.back();
    }

    @Override
    public void clickSaveButton() {
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
    public void sureSave() {
        String hotelID = userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID());
        String name = nameTextField.getText();
        double discount = Double.valueOf(discountTextField.getText());
        DateUtil startDate = new DateUtil(startTime.getDate());
        DateUtil endDate = new DateUtil(endTime.getDate());
        String enterpriseName = enterpriseTextField.getText();

        promotion_enterpriseVO = new Promotion_EnterpriseVO(name, PromotionType.Hotel_Enterprise,
                discount, startDate, endDate, enterpriseName, hotelID);
        if (isEdit) {
            promotion_enterpriseVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotion_enterpriseVO);

            hotelPromotionViewController.refreshHotelPromotionDetail(promotion_enterpriseVO);
            System.out.println("update successfully!");
        } else {
            promotionBLService.addPromotion(promotion_enterpriseVO);
            System.out.println("save successfully!");
        }
        mainUIController.hidePop();
        hotelPromotionViewController.refreshHotelPromotionList();
        hotelPromotionViewController.back();
    }

    @Override
    public void cancelSave() {
        mainUIController.hidePop();
    }

    public void showEditView(PromotionVO promotionVO) {
        promotion_enterpriseVO = (Promotion_EnterpriseVO) promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount + "");
        enterpriseTextField.setText(promotion_enterpriseVO.enterpriseName + "");
        startTime.setDate(LocalDate.parse(promotion_enterpriseVO.startDate.toString()));
        endTime.setDate(LocalDate.parse(promotion_enterpriseVO.endDate.toString()));
        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
