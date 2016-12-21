package ui.viewcontroller.staff;

import blimpl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import vo.*;

import java.io.IOException;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotionDetailViewController {
    private PromotionVO promotionVO;
    private HotelPromotionViewController hotelPromotionViewController;
    private PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();

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
    private Label discountLabel;

    @FXML
    private RectButton backButton;

    @FXML
    private RectButton deleteButton;

    @FXML
    private RectButton editButton;

    @FXML
    private HBox clientGradePane;

    @FXML
    private HBox placePane;

    @FXML
    private HBox timePane;

    @FXML
    private HBox roomQuantityPane;

    @FXML
    private HBox enterprisePane;

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
        discountLabel.setText(promotionVO.promotionDiscount + "");

        clientGradePane.setVisible(false);
        clientGradePane.setManaged(false);
        placePane.setVisible(false);
        placePane.setManaged(false);
        timePane.setVisible(false);
        timePane.setManaged(false);
        roomQuantityPane.setVisible(false);
        roomQuantityPane.setManaged(false);
        enterprisePane.setVisible(false);
        enterprisePane.setManaged(false);

        switch (promotionVO.promotionType) {
            case Hotel_Birthday:
                break;

            case Hotel_Enterprise:
                Promotion_EnterpriseVO promotion_enterpriseVO = (Promotion_EnterpriseVO) promotionVO;
                timeLabel.setText(promotion_enterpriseVO.startDate.toString() + " - " + promotion_enterpriseVO.endDate.toString());
                enterpriseLabel.setText(promotion_enterpriseVO.enterpriseName);
                timePane.setVisible(true);
                timePane.setManaged(true);
                enterprisePane.setVisible(true);
                enterprisePane.setManaged(true);
                break;

            case Hotel_RoomQuantity:
                Promotion_RoomQuantityVO promotion_roomQuantityVO = (Promotion_RoomQuantityVO) promotionVO;
                timeLabel.setText(promotion_roomQuantityVO.startDate.toString() + " - " + promotion_roomQuantityVO.endDate.toString());
                roomQuantityLabel.setText(promotion_roomQuantityVO.roomQuantity + "");
                timePane.setVisible(true);
                timePane.setManaged(true);
                roomQuantityPane.setVisible(true);
                roomQuantityPane.setManaged(true);
                break;

            case Hotel_SpecilaDate:
                Promotion_HotelSpecialDateVO promotion_hotelSpecialDateVO = (Promotion_HotelSpecialDateVO) promotionVO;
                timeLabel.setText(promotion_hotelSpecialDateVO.startDate.toString() + " - " + promotion_hotelSpecialDateVO.endDate.toString());
                timePane.setVisible(true);
                timePane.setManaged(true);
                break;
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

            AlertViewController alertViewController = loader.getController();
            alertViewController.setInfoLabel("确认删除该条酒店促销策略吗？");
            alertViewController.setOnClickSureButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    sureDelete();
                }
            });
            alertViewController.setOnClickCancelButton(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    cancelDelete();
                }
            });
            mainUIController.showPop(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickEditButton() {
        hotelPromotionViewController.showPromotionDetailEditView(promotionVO);
    }

    private void sureDelete(){
        promotionBLService.deletePromotion(promotionVO.promotionID);
        hotelPromotionViewController.refreshHotelPromotionList();
        hotelPromotionViewController.back();
        mainUIController.hidePop();
    }

    private void cancelDelete(){
        mainUIController.hidePop();
    }
}
