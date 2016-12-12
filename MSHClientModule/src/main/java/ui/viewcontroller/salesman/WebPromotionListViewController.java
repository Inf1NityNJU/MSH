package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import bl.promotionbl.PromotionBLFactory;
import bl.promotionbl.PromotionBLServiceImpl;
import blservice.promotionblservice.PromotionBLService;
import blservice.promotionblservice.PromotionBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.promotion.WebPromotionCellController;
import ui.componentcontroller.promotion.WebPromotionPagePaneController;
import ui.componentcontroller.promotion.WebPromotionSearchPaneController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivian on 16/11/29.
 */
public class WebPromotionListViewController {
    private WebPromotionViewController webPromotionViewController;

    private static final int NUM_OF_CELL = 5;

    private ArrayList<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();

    @FXML
    private VBox contentVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[NUM_OF_CELL];
    private Node[] cells = new Node[NUM_OF_CELL];

    private Node pagePane;
    private WebPromotionPagePaneController webPromotionPagePaneController;

    private PromotionBLService promotionBLService;

    private TilePane tilePane;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        promotionBLService = new BLFactoryImpl().getPromotionBLService();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/component/promotion/WebPromotionSearchPane.fxml"));
            VBox pane = loader.load();

            WebPromotionSearchPaneController controller = loader.getController();
            controller.setWebPromotionListViewController(this);

            contentVBox.getChildren().add(pane);

            tilePane = new TilePane();
            tilePane.setPrefColumns(5);
            tilePane.setHgap(20);
            tilePane.setVgap(10);

            contentVBox.getChildren().add(tilePane);

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource("/component/promotion/WebPromotionPagePane.fxml"));
            pagePane = pageLoader.load();

            webPromotionPagePaneController = pageLoader.getController();
            webPromotionPagePaneController.setWebPromotionListViewController(this);

            for (int i = 0; i < NUM_OF_CELL; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/promotion/WebPromotionCell.fxml"));
                HBox webpromotioncell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = webpromotioncell;
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
    public void showAllWebPromotions() {
        promotionVOs = promotionBLService.searchWebPromotions();
        showWebPromotions();
    }

    /**
     * 根据策略类型展示策略
     */
    public void showWebPromotionsByType(PromotionType promotionType) {
        promotionVOs = promotionBLService.searchPromotions(promotionType);
        showWebPromotions();
    }

    public void showWebPromotions() {
        int size = promotionVOs.size();
        webPromotionPagePaneController.setPageCount(size / NUM_OF_CELL + ((size % NUM_OF_CELL == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Promotion");
        }
    }

    public void turnPage(int page) {
        int fromIndex = (page - 1) * NUM_OF_CELL;
        int toIndex = Math.min(page * NUM_OF_CELL, promotionVOs.size());
        List<PromotionVO> tmpPromotions = promotionVOs.subList(fromIndex, toIndex);
        setCells(tmpPromotions);
    }

    private void setCells(List<PromotionVO> tmpPromotions) {

        if (tmpPromotions.size() > NUM_OF_CELL) {
            System.out.println("ERROR");
            return;
        }

        for (Node cell : cells) {
            tilePane.getChildren().remove(cell);
        }

        contentVBox.getChildren().remove(pagePane);

        for (int i = 0; i < tmpPromotions.size(); i++) {

            PromotionVO promotionVO = tmpPromotions.get(i);
            FXMLLoader loader = cellLoaders[i];
            Node promotionCell = cells[i];

            WebPromotionCellController webPromotionCellController = loader.getController();
            webPromotionCellController.setWebPromotionListViewController(this);
            webPromotionCellController.setPromotionVO(promotionVO);

            tilePane.getChildren().add(promotionCell);
        }

        contentVBox.getChildren().add(pagePane);
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
