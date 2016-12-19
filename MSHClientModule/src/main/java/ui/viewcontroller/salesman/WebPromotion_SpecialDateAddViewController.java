package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
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
import vo.Promotion_WebSpecialDateVO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by vivian on 16/12/6.
 */
public class WebPromotion_SpecialDateAddViewController extends WebPromotionAddViewController {
    private Promotion_WebSpecialDateVO promotion_webSpecialDateVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();

    private boolean isEdit = false;
    private String promotionID = null;

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
    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;

        typeButton.setText(PromotionType.Web_SpecilaDate.getType());
        typeButton.setColorProperty(PromotionType.Web_SpecilaDate.getColor());
    }

    @Override
    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    @Override
    public void clickCancelButton() {
        webPromotionViewController.back();
    }

    @FXML
    public void clickSaveButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/common/AlertView.fxml"));
            AnchorPane pane = loader.load();

            AlertViewController alertViewController = loader.getController();
            alertViewController.setInfoLabel("确定保存该条网站促销策略吗？");
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
        String name = nameTextField.getText();
        double discount = Double.valueOf(discountTextField.getText());
        DateUtil startDate = new DateUtil(startTime.getDate());
        DateUtil endDate = new DateUtil(endTime.getDate());

        promotion_webSpecialDateVO = new Promotion_WebSpecialDateVO(name,
                PromotionType.Web_SpecilaDate, discount, startDate, endDate);
        if (isEdit) {
            promotion_webSpecialDateVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotion_webSpecialDateVO);

            webPromotionViewController.refreshWebPromotionDetail(promotion_webSpecialDateVO);
            System.out.println("update successfully!");
        } else {
            promotionBLService.addPromotion(promotion_webSpecialDateVO);
            System.out.println("save successfully!");
        }
        mainUIController.hidePop();
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
    }

    @Override
    public void cancelSave() {
        mainUIController.hidePop();
    }

    public void showEditView(PromotionVO promotionVO) {
        promotion_webSpecialDateVO = (Promotion_WebSpecialDateVO) promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount + "");
        startTime.setDate(LocalDate.parse(promotion_webSpecialDateVO.startDate.toString()));
        endTime.setDate(LocalDate.parse(promotion_webSpecialDateVO.endDate.toString()));

        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
