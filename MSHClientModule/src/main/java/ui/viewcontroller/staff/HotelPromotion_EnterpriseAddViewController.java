package ui.viewcontroller.staff;

import blimpl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_EnterpriseVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_EnterpriseAddViewController{
    private Promotion_EnterpriseVO promotion_enterpriseVO;
    private HotelPromotionViewController hotelPromotionViewController;

    private boolean isEdit = false;
    private String promotionID = null;

    private PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();

    private MainUIController mainUIController;

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

    @FXML
    private Label alertLabel;

    @FXML
    public void initialize() {
        typeButton.setText(PromotionType.Hotel_Enterprise.getType());
        typeButton.setColorProperty(PromotionType.Hotel_Enterprise.getColor());

        startTime.setDate(LocalDate.now());
        endTime.setDate(LocalDate.now().plusDays(30));
        alertLabel.setText("");
    }

    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void clickCancelButton() {
        hotelPromotionViewController.back();
    }

    public void clickSaveButton() {

        String name = nameTextField.getText();
        if (name.equals("")) {
            alertLabel.setText("请填写折扣名称");
            return;
        }

        Pattern pricePattern = Pattern.compile("^0.[0-9]([0-9]?)$");
        boolean isDiscount = pricePattern.matcher(discountTextField.getText()).matches();
        if (!isDiscount) {
            alertLabel.setText("折扣数额格式应为 0.XX ！");
            return;
        }

        double discount = Double.valueOf(discountTextField.getText());
        if (discount < 0 || discount > 1) {
            alertLabel.setText("请填写正确的折扣数额 0 - 1");
            return;
        }

        String enterpriseName = enterpriseTextField.getText();
        if (enterpriseName.equals("")) {
            alertLabel.setText("请填写企业名称");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();
            alertViewController.setInfoLabel("确定保存该条酒店促销策略吗？");
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    sureSave();
                }
            });
            alertViewController.setOnClickCancelButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    cancelSave();
                }
            });
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
