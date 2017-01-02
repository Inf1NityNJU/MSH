package ui.viewcontroller.salesman;

import blimpl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;
import ui.componentcontroller.common.AlertViewController;
import ui.viewcontroller.common.MainUIController;
import util.City;
import util.DateUtil;
import util.Place;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_SpecialPlaceVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by vivian on 16/12/6.
 */
public class WebPromotion_SpecialPlaceAddViewController{
    private Promotion_SpecialPlaceVO promotion_specialPlaceVO;
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
    private MyChoiceBox cityChoiceBox;

    @FXML
    private MyChoiceBox placeChoiceBox;

    @FXML
    private MyDatePicker startTime;

    @FXML
    private MyDatePicker endTime;

    @FXML
    private Label alertLabel;

    @FXML
    public void initialize() {
        typeButton.setText(PromotionType.Web_SpecilPlace.getType());
        typeButton.setColorProperty(PromotionType.Web_SpecilPlace.getColor());

        cityChoiceBox.setItems(FXCollections.observableArrayList(City.getNames(City.values())));

        cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        City city = City.getCityByName((String)newValue);
                        placeChoiceBox.setItems(FXCollections.observableArrayList(Place.getNames(city.getPlaces())));
                        placeChoiceBox.getSelectionModel().selectFirst();
                    }
                }
        );

        cityChoiceBox.getSelectionModel().selectFirst();

        startTime.setDate(LocalDate.now());
        endTime.setDate(LocalDate.now().plusDays(30));
        alertLabel.setText("");
    }

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;
    }

    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void clickCancelButton() {
        webPromotionViewController.back();
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
        City city = City.getCityByName((String)cityChoiceBox.getSelectionModel().getSelectedItem());
        Place place = Place.getPlaceByName((String)placeChoiceBox.getSelectionModel().getSelectedItem());

        promotion_specialPlaceVO = new Promotion_SpecialPlaceVO(name, PromotionType.Web_SpecilPlace,
                discount, startDate, endDate, city, place);
        if (isEdit) {
            promotion_specialPlaceVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotion_specialPlaceVO);
            webPromotionViewController.refreshWebPromotionDetail(promotion_specialPlaceVO);
            System.out.println("update successfully!");
        } else {
            promotionBLService.addPromotion(promotion_specialPlaceVO);
            System.out.println("save successfully!");
        }
        mainUIController.hidePop();
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
    }

    public void cancelSave() {
        mainUIController.hidePop();
    }

    public void showEditView(PromotionVO promotionVO) {
        promotion_specialPlaceVO = (Promotion_SpecialPlaceVO)promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount + "");
        startTime.setDate(LocalDate.parse(promotion_specialPlaceVO.startDate.toString()));
        endTime.setDate(LocalDate.parse(promotion_specialPlaceVO.endDate.toString()));

        cityChoiceBox.getSelectionModel().select(promotion_specialPlaceVO.city.getName());
        placeChoiceBox.getSelectionModel().select(promotion_specialPlaceVO.place.getName());


        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
