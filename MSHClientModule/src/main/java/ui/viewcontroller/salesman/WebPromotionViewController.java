package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by vivian on 16/11/27.
 */
public class WebPromotionViewController {
    private BorderPane rootPane;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private WebPromotionListViewController webPromotionListViewController;

    private PromotionBLService promotionBLService;

    public WebPromotionViewController(BorderPane rootPane) {
        this.promotionBLService = new BLFactoryImpl().getPromotionBLService();
        this.rootPane = rootPane;
    }

    /**
     * 返回上一界面
     */
    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            rootPane.setCenter(node);

        }
    }

    /**
     * 网站促销策略列表
     */
    public void showWebPromotionList() {
        if (initNode != null) {
            stack.clear();
            rootPane.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(Main.class.getResource("../view/salesman/WebPromotionListView.fxml"));
            ScrollPane list = listLoader.load();

            webPromotionListViewController = listLoader.getController();
            webPromotionListViewController.setWebPromotionViewController(this);

            initNode = list;

            rootPane.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示策略详情
     *
     * @param promotionVO
     */
    public void showPromotionDetail(PromotionVO promotionVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/salesman/WebPromotionDetailView.fxml"));
            ScrollPane view = loader.load();

            WebPromotionDetailViewController webPromotionDetailViewController = loader.getController();
            webPromotionDetailViewController.setWebPromotionViewController(this);
            webPromotionDetailViewController.setPromotionBLService(promotionBLService);
            webPromotionDetailViewController.showWebPromotionDetail(promotionVO);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新策略列表
     */
    public void refreshWebPromotionList() {
        webPromotionListViewController.showAllWebPromotions();
    }

    /**
     * 编辑策略
     */
    public void showPromotionDetailEditView(PromotionVO promotionVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/salesman/WebPromotionDetailEditView.fxml"));
            ScrollPane view = loader.load();

            WebPromotionDetailEditViewController webPromotionDetailEditViewController = loader.getController();
            webPromotionDetailEditViewController.setWebPromotionViewController(this);
            webPromotionDetailEditViewController.show(promotionVO);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加策略
     */
    public void addWebPromotion(PromotionType promotionType) {
        switch (promotionType) {
            case Web_ClientGrade:
                this.addWeb_ClientGradePromotion();
                break;
            case Web_SpecilaDate:
                this.addWeb_SpecialDatePromotion();
                break;

        }
    }

    /**
     * 增加网站会员等级策略
     */
    public void addWeb_ClientGradePromotion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/salesman/WebPromotion_ClientGradeAddView.fxml"));
            ScrollPane view = loader.load();

            WebPromotion_ClientGradeAddViewController webPromotion_clientGradeAddViewController = loader.getController();
            webPromotion_clientGradeAddViewController.setWebPromotionViewController(this);
            webPromotion_clientGradeAddViewController.setPromotionBLService(promotionBLService);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加网站特殊期间折扣策略
     */
    public void addWeb_SpecialDatePromotion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/salesman/WebPromotion_SpecialDateAddView.fxml"));
            ScrollPane view = loader.load();

            WebPromotion_SpecialDateAddViewController webPromotion_specialDateAddViewController = loader.getController();
            webPromotion_specialDateAddViewController.setWebPromotionViewController(this);
            webPromotion_specialDateAddViewController.setPromotionBLService(promotionBLService);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
