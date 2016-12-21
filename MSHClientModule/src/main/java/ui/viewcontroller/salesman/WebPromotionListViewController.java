package ui.viewcontroller.salesman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import ui.componentcontroller.promotion.WebPromotionPagePaneController;
import ui.componentcontroller.promotion.WebPromotionSearchPaneController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;

/**
 * Created by vivian on 16/11/29.
 */
public class WebPromotionListViewController {

    @FXML
    private VBox contentVBox;

    private WebPromotionViewController webPromotionViewController;

    private WebPromotionPagePaneController webPromotionPagePaneController;
    private PromotionType promotionType;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/promotion/WebPromotionSearchPane.fxml"));
            VBox pane = loader.load();

            WebPromotionSearchPaneController webPromotionSearchPaneController = loader.getController();
            webPromotionSearchPaneController.setWebPromotionListViewController(this);

            contentVBox.getChildren().add(pane);

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/component/promotion/WebPromotionPagePane.fxml"));
            VBox pagePane = pageLoader.load();

            webPromotionPagePaneController = pageLoader.getController();
            webPromotionPagePaneController.setWebPromotionListViewController(this);

            contentVBox.getChildren().add(pagePane);

            webPromotionSearchPaneController.showAllPromotions();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;
    }

    /**
     * 展示所有策略列表
     */
    public void showWebPromotionsByType(PromotionType promotionType) {
        this.promotionType = promotionType;
        webPromotionPagePaneController.showWebPromotionsByType(promotionType);
    }

    /**
     * 更新策略列表
     */
    public void refreshHotelPromotions() {
        showWebPromotionsByType(promotionType);
    }


    /**
     * 增加新的策略
     */
    public void addPromotion(PromotionType promotionType) {
        webPromotionViewController.addWebPromotion(promotionType, false);
    }

    /**
     * 展示策略详细信息
     */
    public void showPromotionDetail(PromotionVO promotionVO) {
        webPromotionViewController.showPromotionDetail(promotionVO);
    }
}
