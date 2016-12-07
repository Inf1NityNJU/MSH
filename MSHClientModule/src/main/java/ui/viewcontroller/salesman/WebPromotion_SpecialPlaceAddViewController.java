package ui.viewcontroller.salesman;

import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import util.City;
import util.DateUtil;
import util.Place;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_SpecialPlaceVO;

/**
 * Created by vivian on 16/12/6.
 */
public class WebPromotion_SpecialPlaceAddViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService;

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

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;

        typeButton.setText(PromotionType.Web_SpecilaDate.getType());
        typeButton.setColorProperty(PromotionType.Web_SpecilaDate.getColor());

    }

    public void setPromotionBLService(PromotionBLService promotionBLService) {
        this.promotionBLService = promotionBLService;
    }

    public void clickCancelButton() {
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
    }

    public void clickSaveButton() {
        promotionVO = new Promotion_SpecialPlaceVO(nameTextField.getText(), PromotionType.Web_SpecilPlace,
                Double.valueOf(discountTextField.getText()), new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()),
                (Place) placeChoiceBox.getValue());
        promotionBLService.addPromotion(promotionVO);

    }

    public void clickPlaceChoiceBox() {
        ObservableList observableList = FXCollections.observableArrayList();
        City city = null;
        switch ((String) cityChoiceBox.getValue()) {
            case "NanJing":
                city = City.NanJing;
                break;
            case "ShangHai":
                city = City.ShangHai;
            case "BeiJing":
                city = City.BeiJing;
                break;
            case "GuangZhou":
                city = City.GuangZhou;
                break;
            case "LanZhou":
                city = City.LanZhou;
                break;
            case "GuiYang":
                city = City.GuiYang;
                break;
        }
        for (int i = 0; i < city.getPlaces().length; i++) {
            observableList.add(city.getPlaces()[i]);
        }
        placeChoiceBox.setItems(observableList);
    }
}
