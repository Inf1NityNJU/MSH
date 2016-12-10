package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.Hotel;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.promotion.HotelPromotionCellController;
import ui.componentcontroller.promotion.HotelPromotionPagePaneController;
import ui.componentcontroller.promotion.HotelPromotionSearchPaneController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivian on 16/12/8.
 */
public class HotelPromotionListViewController {
    private HotelPromotionViewController hotelPromotionViewController;

    private static final int NUM_OF_CELL = 5;

    private ArrayList<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();

    @FXML
    private VBox contentVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[NUM_OF_CELL];
    private Node[] cells = new Node[NUM_OF_CELL];

    private Node pagePane;
    private HotelPromotionPagePaneController hotelPromotionPagePaneController;

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
            loader.setLocation(Main.class.getResource("../component/promotion/HotelPromotionSearchPane.fxml"));
            VBox pane = loader.load();

            HotelPromotionSearchPaneController controller = loader.getController();
            controller.setHotelPromotionListViewController(this);

            contentVBox.getChildren().add(pane);

            tilePane = new TilePane();
            tilePane.setPrefColumns(5);
            tilePane.setHgap(20);
            tilePane.setVgap(10);

            contentVBox.getChildren().add(tilePane);

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(Main.class.getResource("../component/promotion/HotelPromotionPagePane.fxml"));
            pagePane = pageLoader.load();

            hotelPromotionPagePaneController = pageLoader.getController();
            hotelPromotionPagePaneController.setHotelPromotionListViewController(this);

            for (int i = 0; i < NUM_OF_CELL; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/promotion/HotelPromotionCell.fxml"));
                HBox webpromotioncell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = webpromotioncell;
            }

            controller.showAllPromotions();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;
    }

    /**
     * 展示所有策略列表
     */
    public void showAllHotelPromotions() {
        promotionVOs = promotionBLService.searchHotelPromotions(userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID()));
        showHotelPromotions();
    }

    /**
     * 根据策略类型展示策略
     */
    public void showHotelPromotionsByType(PromotionType promotionType) {
        ArrayList<PromotionVO> tempPromotionVOs = new ArrayList<PromotionVO>();
        for(int i=0;i<promotionVOs.size();i++){
            if(promotionVOs.get(i).promotionType==promotionType){
                tempPromotionVOs.add(promotionVOs.get(i));
            }
        }
        promotionVOs = tempPromotionVOs;
        showHotelPromotions();
        promotionVOs = promotionBLService.searchHotelPromotions(userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID()));
    }

    public void showHotelPromotions() {
        int size = promotionVOs.size();
        hotelPromotionPagePaneController.setPageCount(size / NUM_OF_CELL + ((size % NUM_OF_CELL == 0) ? 0 : 1));
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

            HotelPromotionCellController hotelPromotionCellController = loader.getController();
            hotelPromotionCellController.setHotelPromotionListViewController(this);
            hotelPromotionCellController.setPromotionVO(promotionVO);

            tilePane.getChildren().add(promotionCell);
        }

        contentVBox.getChildren().add(pagePane);
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
