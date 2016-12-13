package ui.viewcontroller.staff;

import bl.hotelbl.Hotel;
import bl.promotionbl.Promotion_HotelSpecialDate;
import blservice.promotionblservice.PromotionBLService;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.PromotionType;
import vo.*;

import java.io.IOException;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotionDetailViewController {
    private PromotionVO promotionVO;
    private HotelPromotionViewController hotelPromotionViewController;
    private PromotionBLService promotionBLService;

    private AlertViewController alertViewController;
    private MainUIController mainUIController;
    @FXML
    private Label nameLabel;

    @FXML
    private StateButton typeButton;

    @FXML
    private Label clientGradeLabel;

    @FXML
    private Label placeLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label roomQuantityLabel;

    @FXML
    private Label enterpriseLabel;

    @FXML
    private RectButton backButton;

    @FXML
    private RectButton deleteButton;

    @FXML
    private RectButton editButton;


    public void setPromotionBLService(PromotionBLService promotionBLService) {
        this.promotionBLService = promotionBLService;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;
    }

    public void showHotelPromotionDetail(PromotionVO promotionVO) {
        this.promotionVO = promotionVO;

        nameLabel.setText(promotionVO.promotionName);
        typeButton.setText(promotionVO.promotionType.getType());
        typeButton.setColorProperty(promotionVO.promotionType.getColor());


        switch (promotionVO.promotionType) {
            case Hotel_Birthday:
                timeLabel.setText("According to client's birthday");
                break;
            case Hotel_Enterprise:
                Promotion_EnterpriseVO promotion_enterpriseVO = (Promotion_EnterpriseVO) promotionVO;
                timeLabel.setText(promotion_enterpriseVO.startDate.toString() + " - " + promotion_enterpriseVO.endDate.toString());
                break;
            case Hotel_RoomQuantity:
                Promotion_RoomQuantityVO promotion_roomQuantityVO = (Promotion_RoomQuantityVO) promotionVO;
                timeLabel.setText(promotion_roomQuantityVO.startDate.toString() + " - " + promotion_roomQuantityVO.endDate.toString());
                break;
            case Hotel_SpecilaDate:
                Promotion_HotelSpecialDateVO promotion_hotelSpecialDateVO = (Promotion_HotelSpecialDateVO) promotionVO;
                timeLabel.setText(promotion_hotelSpecialDateVO.startDate.toString() + " - " + promotion_hotelSpecialDateVO.endDate.toString());
                break;
        }


        clientGradeLabel.setText("1");

        placeLabel.setText("No special place");

        if (promotionVO.promotionType == PromotionType.Hotel_RoomQuantity) {
            Promotion_RoomQuantityVO promotion_roomQuantityVO = (Promotion_RoomQuantityVO) promotionVO;
            roomQuantityLabel.setText(promotion_roomQuantityVO.roomQuantity + "");
        } else {
            roomQuantityLabel.setText("No special requirement");
        }

        if (promotionVO.promotionType == PromotionType.Hotel_Enterprise) {
            Promotion_EnterpriseVO promotion_enterpriseVO = (Promotion_EnterpriseVO) promotionVO;
            enterpriseLabel.setText(promotion_enterpriseVO.enterpriseName);
        } else {
            enterpriseLabel.setText("No special requirement");
        }

    }

    @FXML
    public void clickBackButton() {
        hotelPromotionViewController.back();
    }

    @FXML
    public void clickDeleteButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            alertViewController = loader.getController();
            alertViewController.setHotelPromotionDetailViewController(this);
            alertViewController.setInfoLabel("确认删除该条酒店促销策略吗？");
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
//        promotionBLService.deletePromotion(promotionVO.promotionID);
//        hotelPromotionViewController.refreshHotelPromotionList();
//        hotelPromotionViewController.back();
    }

    @FXML
    public void clickEditButton() {
        hotelPromotionViewController.showPromotionDetailEditView(promotionVO);
    }

    public void sureDelete(){
        promotionBLService.deletePromotion(promotionVO.promotionID);
        hotelPromotionViewController.refreshHotelPromotionList();
        hotelPromotionViewController.back();
        mainUIController.hidePop();
    }

    public void cancelDelete(){
        mainUIController.hidePop();
    }
}
