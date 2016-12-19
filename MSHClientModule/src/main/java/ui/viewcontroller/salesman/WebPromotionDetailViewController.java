package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
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
import util.PromotionType;
import vo.*;

import java.io.IOException;

/**
 * Created by vivian on 16/11/30.
 */
public class WebPromotionDetailViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;

    private boolean isEdit = false;
    private String promotionID = null;

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

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void showWebPromotionDetail(PromotionVO promotionVO) {
        this.promotionVO = promotionVO;

        nameLabel.setText(promotionVO.promotionName);
        typeButton.setText(promotionVO.promotionType.getType());
        typeButton.setColorProperty(promotionVO.promotionType.getColor());
        discountLabel.setText(promotionVO.promotionDiscount + "");

        Promotion_WebVO promotion_webVO = (Promotion_WebVO) promotionVO;
        timeLabel.setText(promotion_webVO.startDate.toString() + " - " + promotion_webVO.endDate.toString());

        clientGradePane.setVisible(false);
        clientGradePane.setManaged(false);
        placePane.setVisible(false);
        placePane.setManaged(false);

        switch (promotionVO.promotionType) {
            case Web_ClientGrade:
                Promotion_ClientGradeVO promotion_clientGradeVO = (Promotion_ClientGradeVO) promotionVO;
                clientGradeLabel.setText(Integer.toString(promotion_clientGradeVO.clientGrade));
                clientGradePane.setVisible(true);
                clientGradePane.setManaged(true);
                break;
            case Web_SpecilPlace:
                Promotion_SpecialPlaceVO promotion_specialPlaceVO = (Promotion_SpecialPlaceVO) promotionVO;
                placeLabel.setText(promotion_specialPlaceVO.city.getName() + " " + promotion_specialPlaceVO.place.getName());
                placePane.setVisible(true);
                placePane.setManaged(true);
                break;
            case Web_SpecilaDate:
                break;
        }

    }

    @FXML
    public void clickBackButton() {
        webPromotionViewController.back();
    }

    @FXML
    public void clickDeleteButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();
            alertViewController.setInfoLabel("确认删除该条网站促销策略吗？");
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
        webPromotionViewController.showPromotionDetailEditView(promotionVO);
    }

    public void sureDelete() {
        promotionBLService.deletePromotion(promotionVO.promotionID);
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
        mainUIController.hidePop();
    }

    public void cancelDelete() {
        mainUIController.hidePop();
    }
}
