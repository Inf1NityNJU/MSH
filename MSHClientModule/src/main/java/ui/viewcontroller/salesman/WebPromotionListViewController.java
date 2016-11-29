package ui.viewcontroller.salesman;

import blservice.promotionblservice.PromotionBLService;
import blservice.promotionblservice.PromotionBLService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.promotion.WebPromotionSearchPaneController;

import java.io.IOException;

/**
 * Created by vivian on 16/11/29.
 */
public class WebPromotionListViewController {
    private WebPromotionViewController webPromotionViewController;

    @FXML
    private VBox contentVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[6];
    private Node[] cells = new Node[6];

    private PromotionBLService promotionBLService;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        promotionBLService = new PromotionBLService_Stub();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/promotion/WebPromotionSearchPane.fxml"));
            VBox pane = loader.load();

            WebPromotionSearchPaneController controller = loader.getController();
            controller.setWebPromotionListViewController(this);

            contentVBox.getChildren().add(pane);

            for (int i = 0; i < 6; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/promotion/WebPromotionCell.fxml"));
                HBox ordercell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = ordercell;
            }

            controller.showAllPromotions();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController) {
        this.webPromotionViewController = webPromotionViewController;
    }
}
