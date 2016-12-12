package ui.viewcontroller.salesman;

import bl.userbl.UserBLFactory;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_ClientGradeVO;

/**
 * Created by vivian on 16/12/6.
 */
public class WebPromotion_ClientGradeAddViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService;

    private boolean isEdit = false;
    private String promotionID = null;

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

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;

        typeButton.setText(PromotionType.Web_ClientGrade.getType());
        typeButton.setColorProperty(PromotionType.Web_ClientGrade.getColor());

        ObservableList observableList = FXCollections.observableArrayList();
        UserBLInfo userBLInfo = UserBLFactory.getUserBLServiceImpl_Salesman();
        for(int i=0;i<userBLInfo.getAllLevel().size();i++){
            observableList.add(i+1);
        }

        levelChoiceBox.setItems(observableList);
    }

    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    @FXML
    public void clickCancelButton(){
        webPromotionViewController.refreshWebPromotionList();
        if(isEdit){
            webPromotionViewController.back();
        }
        webPromotionViewController.back();
    }

    @FXML
    public void clickSaveButton(){
        int clientGrade = (int)levelChoiceBox.getValue();
        promotionVO = new Promotion_ClientGradeVO(nameTextField.getText(),PromotionType.Web_ClientGrade, Double.valueOf(discountTextField.getText()),
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()),
                clientGrade);
        if(isEdit){
            promotionVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotionVO);
            System.out.println("update successfully!");
        }else {
            promotionBLService.addPromotion(promotionVO);
            System.out.println("save successfully!");
        }


    }

    @FXML
    public void showEditView(PromotionVO promotionVO){
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount+"");
        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
