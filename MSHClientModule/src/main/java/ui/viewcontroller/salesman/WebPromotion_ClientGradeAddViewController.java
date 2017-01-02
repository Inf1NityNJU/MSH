package ui.viewcontroller.salesman;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.UserBLFactory;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_ClientGradeVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by vivian on 16/12/6.
 */
public class WebPromotion_ClientGradeAddViewController  {
    private Promotion_ClientGradeVO promotion_clientGradeVO;
    private WebPromotionViewController webPromotionViewController;

    private boolean isEdit = false;
    private String promotionID = null;

    private PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();

    private MainUIController mainUIController;

    @FXML
    private CommonTextField nameTextField;

    @FXML
    private StateButton typeButton;

    @FXML
    private MyChoiceBox levelChoiceBox;

    @FXML
    private CommonTextField discountTextField;

    @FXML
    private MyDatePicker startTime;

    @FXML
    private MyDatePicker endTime;

    @FXML
    private Label alertLabel;

    @FXML
    public void initialize() {
        typeButton.setText(PromotionType.Web_ClientGrade.getType());
        typeButton.setColorProperty(PromotionType.Web_ClientGrade.getColor());

        ObservableList observableList = FXCollections.observableArrayList();
        UserBLInfo userBLInfo = UserBLFactory.getUserBLServiceImpl_Salesman();

        for (int i = 0; i < userBLInfo.getAllLevel().size(); i++) {
            observableList.add(i + 1);
        }

        levelChoiceBox.setItems(observableList);

        startTime.setDate(LocalDate.now());
        endTime.setDate(LocalDate.now().plusDays(30));
        levelChoiceBox.getSelectionModel().selectFirst();
        alertLabel.setText("");
    }

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    @FXML
    public void clickCancelButton() {
        webPromotionViewController.back();
    }

    @FXML
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

    public void sureSave() {

        String name = nameTextField.getText();
        double discount = Double.valueOf(discountTextField.getText());
        DateUtil startDate = new DateUtil(startTime.getDate());
        DateUtil endDate = new DateUtil(endTime.getDate());
        int clientGrade = (int)levelChoiceBox.getValue();

        promotion_clientGradeVO = new Promotion_ClientGradeVO(name, PromotionType.Web_ClientGrade,
                discount, startDate, endDate,
                clientGrade);
        if (isEdit) {
            promotion_clientGradeVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotion_clientGradeVO);

            webPromotionViewController.refreshWebPromotionDetail(promotion_clientGradeVO);
            System.out.println("update successfully!");
        } else {
            promotionBLService.addPromotion(promotion_clientGradeVO);
            System.out.println("save successfully!");
        }
        mainUIController.hidePop();
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
    }

    public void cancelSave() {
        mainUIController.hidePop();
    }

    @FXML
    public void showEditView(PromotionVO promotionVO) {
        promotion_clientGradeVO = (Promotion_ClientGradeVO)promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount + "");
        startTime.setDate(LocalDate.parse(promotion_clientGradeVO.startDate.toString()));
        endTime.setDate(LocalDate.parse(promotion_clientGradeVO.endDate.toString()));
        levelChoiceBox.getSelectionModel().select(promotion_clientGradeVO.clientGrade-1);
        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
