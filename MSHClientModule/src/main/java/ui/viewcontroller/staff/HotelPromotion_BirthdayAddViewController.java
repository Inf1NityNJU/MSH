package ui.viewcontroller.staff;

import blimpl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.statebutton.StateButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_BirthdayVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_BirthdayAddViewController{
    private Promotion_BirthdayVO promotion_birthdayVO;
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
    private Label alertLabel;

    @FXML
    public void initialize() {
        typeButton.setText(PromotionType.Hotel_Birthday.getType());
        typeButton.setColorProperty(PromotionType.Hotel_Birthday.getColor());

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

        promotion_birthdayVO = new Promotion_BirthdayVO(name, PromotionType.Hotel_Birthday,
                discount, hotelID);

        if (isEdit) {
            promotion_birthdayVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotion_birthdayVO);
            hotelPromotionViewController.refreshHotelPromotionDetail(promotion_birthdayVO);
            System.out.println("update successfully!");
        } else {
            promotionBLService.addPromotion(promotion_birthdayVO);
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
        promotion_birthdayVO = (Promotion_BirthdayVO)promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount + "");

        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
