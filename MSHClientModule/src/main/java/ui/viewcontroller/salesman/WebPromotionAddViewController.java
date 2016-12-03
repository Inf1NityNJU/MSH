package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import bl.promotionbl.PromotionBLServiceImpl;
import bl.promotionbl.Promotion_WebClientGrade;
import blservice.promotionblservice.PromotionBLService;
import component.commontextfield.CommonTextField;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_ClientGradeVO;

/**
 * Created by vivian on 16/12/2.
 */
public class WebPromotionAddViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService;

    @FXML
    private CommonTextField nameTextField;

    @FXML
    private StateButton typeButton;

    @FXML
    private ChoiceBox levelChoiceBox;

    @FXML
    private ChoiceBox cityChoiceBox;

    @FXML
    private ChoiceBox placeChoiceBox;

    @FXML
    private CommonTextField startTimeTextField;

    @FXML
    private CommonTextField endTimeTextField;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController){
        this.webPromotionViewController = webPromotionViewController;
    }

    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    public void clickCancelButton(){
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
    }

    // TODO: 16/12/1 need to discuss the diferrence between ui and logic, add discount
    public void clickSaveButton(){
        String startTime = startTimeTextField.getText();
        String[] param1 = startTime.split("-");
        int startYear = Integer.parseInt(param1[0]);
        int startMonth = Integer.parseInt(param1[1]);
        int startDay = Integer.parseInt(param1[2]);

        String endTime = endTimeTextField.getText();
        String[] param2 = endTime.split("-");
        int endYear = Integer.parseInt(param2[0]);
        int endMonth = Integer.parseInt(param2[1]);
        int endDay = Integer.parseInt(param2[2]);

        int clientGrade = Integer.valueOf((String)levelChoiceBox.getValue());

        promotionVO = new Promotion_ClientGradeVO(nameTextField.getText(),
                PromotionType.Web_ClientGrade, 0.8,
                new DateUtil(startYear,startMonth,startDay), new DateUtil(endYear,endMonth,endDay),
                clientGrade);
//        promotionBLService = new BLFactoryImpl().getPromotionBLService();
        promotionBLService.addPromotion(promotionVO);

    }

}
