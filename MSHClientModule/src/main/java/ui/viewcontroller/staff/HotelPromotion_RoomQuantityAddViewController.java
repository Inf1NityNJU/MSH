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
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_RoomQuantityVO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_RoomQuantityAddViewController{
    private Promotion_RoomQuantityVO promotion_roomQuantityVO;
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
    private CommonTextField roomQuantityTextField;


    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;

        typeButton.setText(PromotionType.Hotel_RoomQuantity.getType());
        typeButton.setColorProperty(PromotionType.Hotel_RoomQuantity.getColor());
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void clickCancelButton() {
        hotelPromotionViewController.back();
    }

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

    public void sureSave() {
        String hotelID = userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID());
        String name = nameTextField.getText();
        double discount = Double.valueOf(discountTextField.getText());
        DateUtil startDate = new DateUtil(startTime.getDate());
        DateUtil endDate = new DateUtil(endTime.getDate());
        int roomQuantity = Integer.valueOf(roomQuantityTextField.getText());

        promotion_roomQuantityVO = new Promotion_RoomQuantityVO(name, PromotionType.Hotel_RoomQuantity,
                discount, startDate, endDate, hotelID, roomQuantity);

        if (isEdit) {
            promotion_roomQuantityVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotion_roomQuantityVO);
            hotelPromotionViewController.refreshHotelPromotionDetail(promotion_roomQuantityVO);
            System.out.println("update successfully!");

        } else {
            promotionBLService.addPromotion(promotion_roomQuantityVO);
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
        promotion_roomQuantityVO = (Promotion_RoomQuantityVO) promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount + "");
        roomQuantityTextField.setText(promotion_roomQuantityVO.roomQuantity + "");
        startTime.setDate(LocalDate.parse(promotion_roomQuantityVO.startDate.toString()));
        endTime.setDate(LocalDate.parse(promotion_roomQuantityVO.endDate.toString()));
        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
