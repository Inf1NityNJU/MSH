package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.Hotel;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import java.time.LocalDate;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_SpecialDateAddViewController extends HotelPromotionAddViewController {
    private Promotion_HotelSpecialDateVO promotion_hotelSpecialDateVO;
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

    @Override
    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;

        typeButton.setText(PromotionType.Hotel_SpecilaDate.getType());
        typeButton.setColorProperty(PromotionType.Hotel_SpecilaDate.getColor());
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

    @Override
    public void sureSave() {
        String hotelID = userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID());
        String name = nameTextField.getText();
        double discount = Double.valueOf(discountTextField.getText());
        DateUtil startDate = new DateUtil(startTime.getDate());
        DateUtil endDate = new DateUtil(endTime.getDate());

        promotion_hotelSpecialDateVO = new Promotion_HotelSpecialDateVO(name, PromotionType.Hotel_SpecilaDate,
                discount, startDate, endDate, hotelID);
        if (isEdit) {
            promotion_hotelSpecialDateVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotion_hotelSpecialDateVO);
            hotelPromotionViewController.refreshHotelPromotionDetail(promotion_hotelSpecialDateVO);
            System.out.println("update successfully!");
        } else {
            promotionBLService.addPromotion(promotion_hotelSpecialDateVO);
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
        promotion_hotelSpecialDateVO = (Promotion_HotelSpecialDateVO) promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount + "");
        startTime.setDate(LocalDate.parse(promotion_hotelSpecialDateVO.startDate.toString()));
        endTime.setDate(LocalDate.parse(promotion_hotelSpecialDateVO.endDate.toString()));
        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
