package ui.componentcontroller.promotion;

import blimpl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import component.pagepane.PagePane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.viewcontroller.salesman.WebPromotionListViewController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivian on 16/12/3.
 */
public class WebPromotionPagePaneController {
    private static final int NUM_OF_CELL = 5;

    @FXML
    private PagePane pagePane;

    @FXML
    private VBox promotionVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[NUM_OF_CELL];
    private Node[] cells = new Node[NUM_OF_CELL];

    private WebPromotionListViewController webPromotionListViewController;
    private ArrayList<PromotionVO> promotionVOs = new ArrayList<>();

    private PromotionBLService promotionBLService = new BLFactoryImpl().getPromotionBLService();

    @FXML
    public void initialize() {

        for (int i = 0; i < NUM_OF_CELL; i++) {
            try {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(getClass().getResource("/component/promotion/WebPromotionCell.fxml"));
                HBox cell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = cell;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void setWebPromotionListViewController(WebPromotionListViewController webPromotionListViewController) {
        this.webPromotionListViewController = webPromotionListViewController;
    }

    @FXML
    private void pageChanged() {
        turnPage(pagePane.getCurrentPage());
    }

    /**
     * 展示策略详情
     */
    public void showPromotionDetail(PromotionVO promotionVO) {
        webPromotionListViewController.showPromotionDetail(promotionVO);
    }

    /**
     * 根据策略类型展示策略
     */
    public void showWebPromotionsByType(PromotionType promotionType) {

        promotionVOs = promotionBLService.searchWebPromotions();

        if (promotionType != null) {
            ArrayList<PromotionVO> newPromotionVOs = new ArrayList<PromotionVO>();
            for (int i = 0; i < promotionVOs.size(); i++) {
                if (promotionVOs.get(i).promotionType == promotionType) {
                    newPromotionVOs.add(promotionVOs.get(i));
                }
            }
            promotionVOs = newPromotionVOs;
        }

        int size = promotionVOs.size();
        pagePane.setCount(size / NUM_OF_CELL + ((size % NUM_OF_CELL == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Promotion");
            clearCells();
        }
    }

    public void turnPage(int page) {
        int fromIndex = (page - 1) * NUM_OF_CELL;
        int toIndex = Math.min(page * NUM_OF_CELL, promotionVOs.size());
        List<PromotionVO> tmpPromotions = promotionVOs.subList(fromIndex, toIndex);
        setCells(tmpPromotions);
    }

    private void clearCells() {
        promotionVBox.getChildren().clear();
    }

    private void setCells(List<PromotionVO> tmpPromotions) {

        if (tmpPromotions.size() > NUM_OF_CELL) {
            System.out.println("ERROR");
            return;
        }

        clearCells();

        for (int i = 0; i < tmpPromotions.size(); i++) {

            PromotionVO promotionVO = tmpPromotions.get(i);
            FXMLLoader loader = cellLoaders[i];
            Node promotionCell = cells[i];

            WebPromotionCellController webPromotionCellController = loader.getController();
            webPromotionCellController.setWebPromotionPagePaneController(this);
            webPromotionCellController.setPromotionVO(promotionVO);

            promotionVBox.getChildren().add(promotionCell);
        }


    }
}
