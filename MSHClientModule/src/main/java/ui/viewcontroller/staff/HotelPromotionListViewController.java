package ui.viewcontroller.staff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import ui.componentcontroller.promotion.HotelPromotionPagePaneController;
import ui.componentcontroller.promotion.HotelPromotionSearchPaneController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;

/**
 * Created by vivian on 16/12/8.
 */
public class HotelPromotionListViewController {

    @FXML
    private VBox contentVBox;

    private HotelPromotionViewController hotelPromotionViewController;

    private HotelPromotionPagePaneController hotelPromotionPagePaneController;
    private PromotionType promotionType;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/promotion/HotelPromotionSearchPane.fxml"));
            VBox pane = loader.load();

            HotelPromotionSearchPaneController hotelPromotionSearchPaneController = loader.getController();
            hotelPromotionSearchPaneController.setHotelPromotionListViewController(this);

            contentVBox.getChildren().add(pane);


            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/component/promotion/HotelPromotionPagePane.fxml"));
            VBox pagePane = pageLoader.load();

            hotelPromotionPagePaneController = pageLoader.getController();
            hotelPromotionPagePaneController.setHotelPromotionListViewController(this);

            contentVBox.getChildren().add(pagePane);

            hotelPromotionSearchPaneController.showAllPromotions();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;
    }

    /**
     * 展示策略列表
     * @param promotionType
     */
    public void showHotelPromotionsByType(PromotionType promotionType) {
        this.promotionType = promotionType;
        hotelPromotionPagePaneController.showHotelPromotionsByType(promotionType);
    }

    /**
     * 更新策略列表
     */
    public void refreshHotelPromotions() {
        showHotelPromotionsByType(promotionType);
    }

    /**
     * 展示策略详细信息
     */
    public void showPromotionDetail(PromotionVO promotionVO) {
        hotelPromotionViewController.showPromotionDetail(promotionVO);
    }

    /**
     * 增加新的策略
     */
    public void addPromotion(PromotionType promotionType) {
        hotelPromotionViewController.addHotelPromotion(promotionType, false);
    }
}
