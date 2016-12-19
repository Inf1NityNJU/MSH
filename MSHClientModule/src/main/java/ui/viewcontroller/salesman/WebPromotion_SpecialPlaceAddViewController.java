package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

/**
 * Created by vivian on 16/12/6.
 */
public class WebPromotion_SpecialPlaceAddViewController extends WebPromotionAddViewController {
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

    @Override
    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;

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

    }

    @Override
    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    @Override
    public void clickCancelButton() {
        webPromotionViewController.back();
    }

    @Override
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

    @Override
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
