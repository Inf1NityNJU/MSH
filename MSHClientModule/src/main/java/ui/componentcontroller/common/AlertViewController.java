package ui.componentcontroller.common;

import bl.blfactory.BLFactoryImpl;
import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.promotion.WebPromotionSearchPaneController;
import ui.viewcontroller.common.MainUIController;
import ui.viewcontroller.salesman.WebPromotionDetailViewController;
import ui.viewcontroller.staff.HotelPromotionDetailViewController;

import java.io.IOException;

/**
 * Created by vivian on 16/12/10.
 */
public class AlertViewController {

    private WebPromotionDetailViewController webPromotionDetailViewController;
    private HotelPromotionDetailViewController hotelPromotionDetailViewController;

    @FXML
    private Label infoLabel;

    @FXML
    private RectButton sureButton;

    @FXML
    private RectButton cancelButton;

    public void setWebPromotionDetailViewController(WebPromotionDetailViewController webPromotionDetailViewController) {
        this.webPromotionDetailViewController = webPromotionDetailViewController;
    }

    public void setHotelPromotionDetailViewController(HotelPromotionDetailViewController hotelPromotionDetailViewController) {
        this.hotelPromotionDetailViewController = hotelPromotionDetailViewController;
    }

    public void setInfoLabel(String info){
        this.infoLabel.setText(info);
    }

    public void clickSureButton(){
        if(infoLabel.getText().equals("确认删除该条网站促销策略吗？")){
            webPromotionDetailViewController.sureDelete();
        }else if(infoLabel.getText().equals("确认删除该条酒店促销策略吗？")){
            hotelPromotionDetailViewController.sureDelete();
        }
    }

    public void clickCancelButton(){
        if(infoLabel.getText().equals("确认删除该条网站促销策略吗？")){
            webPromotionDetailViewController.cancelDelete();
        }else if(infoLabel.getText().equals("确认删除该条酒店促销策略吗？")){
            hotelPromotionDetailViewController.cancelDelete();
        }
    }
}
