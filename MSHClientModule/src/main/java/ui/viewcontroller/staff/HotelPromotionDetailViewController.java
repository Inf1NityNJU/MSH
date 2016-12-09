package ui.viewcontroller.staff;

import bl.hotelbl.Hotel;
import blservice.promotionblservice.PromotionBLService;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_EnterpriseVO;
import vo.Promotion_HotelVO;
import vo.Promotion_RoomQuantityVO;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotionDetailViewController {
    private PromotionVO promotionVO;
    private HotelPromotionViewController hotelPromotionViewController;
    private PromotionBLService promotionBLService;

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


    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController){
        this.hotelPromotionViewController = hotelPromotionViewController;
    }

    public void showHotelPromotionDetail(PromotionVO promotionVO){
        this.promotionVO = promotionVO;

        nameLabel.setText(promotionVO.promotionName);
        typeButton.setText(promotionVO.promotionType.getType());
        typeButton.setColorProperty(promotionVO.promotionType.getColor());

        if(promotionVO.promotionType!= PromotionType.Hotel_Birthday){
            Promotion_EnterpriseVO promotion_hotelVO= (Promotion_EnterpriseVO)promotionVO;
            timeLabel.setText(promotion_hotelVO.startDate.toString()+" - "+promotion_hotelVO.endDate.toString());
        }else {
            timeLabel.setText("According to client's birthday");
        }

        clientGradeLabel.setText("1");

            placeLabel.setText("No special place");

        if (promotionVO.promotionType==PromotionType.Hotel_RoomQuantity){
            Promotion_RoomQuantityVO promotion_roomQuantityVO = (Promotion_RoomQuantityVO)promotionVO;
            roomQuantityLabel.setText(promotion_roomQuantityVO.roomQuantity+"");
        }else {
            roomQuantityLabel.setText("No special requirement");
        }

        if (promotionVO.promotionType==PromotionType.Hotel_Enterprise){
            Promotion_EnterpriseVO promotion_enterpriseVO = (Promotion_EnterpriseVO) promotionVO;
            enterpriseLabel.setText(promotion_enterpriseVO.enterpriseName);
        }else {
            enterpriseLabel.setText("No special requirement");
        }

    }

    @FXML
    public void clickBackButton(){
        hotelPromotionViewController.back();
    }

    @FXML
    public void clickDeleteButton(){
        promotionBLService.deletePromotion(promotionVO.promotionID);
        hotelPromotionViewController.refreshWebPromotionList();
        hotelPromotionViewController.back();
    }

    @FXML
    public void clickEditButton(){
//        webPromotionViewController.showPromotionDetailEditView(promotionVO);
    }
}
