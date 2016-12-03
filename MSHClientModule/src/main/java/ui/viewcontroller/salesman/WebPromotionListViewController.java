package ui.viewcontroller.salesman;

import bl.promotionbl.PromotionBLServiceImpl;
import blservice.promotionblservice.PromotionBLService;
import blservice.promotionblservice.PromotionBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.promotion.WebPromotionCellController;
import ui.componentcontroller.promotion.WebPromotionSearchPaneController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by vivian on 16/11/29.
 */
public class WebPromotionListViewController {
    private WebPromotionViewController webPromotionViewController;

    private static final int ROW_IN_PANE = 6;

    private int currentPage;

    private ArrayList<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();

    @FXML
    private VBox contentVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[ROW_IN_PANE];
    private Node[] cells = new Node[ROW_IN_PANE];

    private PromotionBLService promotionBLService;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        promotionBLService = new PromotionBLServiceImpl();
        currentPage = 1;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/promotion/WebPromotionSearchPane.fxml"));
            VBox pane = loader.load();

            WebPromotionSearchPaneController controller = loader.getController();
            controller.setWebPromotionListViewController(this);

            contentVBox.getChildren().add(pane);

            for (int i = 0; i < ROW_IN_PANE; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/promotion/WebPromotionCell.fxml"));
                HBox webpromotioncell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = webpromotioncell;
                contentVBox.getChildren().add(webpromotioncell);

                WebPromotionCellController webPromotionCellController = cellLoaders[i].getController();
                webPromotionCellController.setWebPromotionListViewController(this);
            }

            controller.showAllPromotions();
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
    public void showAllWebPromotions(){
        promotionVOs = promotionBLService.searchWebPromotions();
        try {
            for (int i = (currentPage - 1) * ROW_IN_PANE; i < currentPage * ROW_IN_PANE; i++) {
                if (i < promotionVOs.size()) {
                    contentVBox.getChildren().get(1 + i - (currentPage - 1) * ROW_IN_PANE).setVisible(true);
                    FXMLLoader tmpLoader = cellLoaders[i];
                    WebPromotionCellController webPromotionCellController = tmpLoader.getController();
                    webPromotionCellController.setPromotionVO(promotionVOs.get(i));
                } else {
                    contentVBox.getChildren().get(1 + i - (currentPage - 1) * ROW_IN_PANE).setVisible(false);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据策略类型展示策略
     */
    public void showWebPromotionsByType(PromotionType promotionType){
        promotionVOs = promotionBLService.searchPromotions(promotionType);
        try {
            for (int i = (currentPage - 1) * ROW_IN_PANE; i < currentPage * ROW_IN_PANE; i++) {
                if (i < promotionVOs.size()) {
                    contentVBox.getChildren().get(1 + i - (currentPage - 1) * ROW_IN_PANE).setVisible(true);
                    FXMLLoader tmpLoader = cellLoaders[i];
                    WebPromotionCellController webPromotionCellController = tmpLoader.getController();
                    webPromotionCellController.setPromotionVO(promotionVOs.get(i));
                } else {
                    contentVBox.getChildren().get(1 + i - (currentPage - 1) * ROW_IN_PANE).setVisible(false);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加新的策略
     */
    public void addPromotion(){
        webPromotionViewController.addWebPromotion();
    }

    /**
     * 展示策略详细信息
     */
    public void showPromotionDetail(PromotionVO promotionVO){
        webPromotionViewController.showPromotionDetail(promotionVO);
    }
}
