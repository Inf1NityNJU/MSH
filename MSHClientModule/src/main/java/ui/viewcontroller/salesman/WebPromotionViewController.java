package ui.viewcontroller.salesman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by vivian on 16/11/27.
 */
public class WebPromotionViewController {
    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private WebPromotionListViewController webPromotionListViewController;
    private WebPromotionDetailViewController webPromotionDetailViewController;

    private PromotionVO promotionVO;

    public WebPromotionViewController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    /**
     * 返回上一界面
     */
    public void back() {
        if (!stack.empty()) {
            Node node = stack.pop();
            mainUIController.setCenter(node);

        }
    }

    /**
     * 网站促销策略列表
     */
    public void showWebPromotionList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/salesman/WebPromotionListView.fxml"));
            ScrollPane list = listLoader.load();

            webPromotionListViewController = listLoader.getController();
            webPromotionListViewController.setWebPromotionViewController(this);

            initNode = list;

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 更新策略列表
     */
    public void refreshWebPromotionList() {
        webPromotionListViewController.refreshHotelPromotions();
    }

    /**
     * 展示策略详情
     *
     * @param promotionVO
     */
    public void showPromotionDetail(PromotionVO promotionVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/salesman/WebPromotionDetailView.fxml"));
            ScrollPane view = loader.load();

            webPromotionDetailViewController = loader.getController();
            webPromotionDetailViewController.setWebPromotionViewController(this);
            webPromotionDetailViewController.setMainUIController(mainUIController);
            webPromotionDetailViewController.showWebPromotionDetail(promotionVO);

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新策略详情
     */
    public void refreshWebPromotionDetail(PromotionVO promotionVO) {
        webPromotionDetailViewController.showWebPromotionDetail(promotionVO);
    }

    /**
     * 编辑策略
     */
    public void showPromotionDetailEditView(PromotionVO promotionVO) {
        this.promotionVO = promotionVO;
        this.addWebPromotion(promotionVO.promotionType, true);
    }


    /**
     * 增加策略
     */
    public void addWebPromotion(PromotionType promotionType, boolean isEdit) {
        switch (promotionType) {
            case Web_ClientGrade:
                this.addWeb_ClientGradePromotion(isEdit);
                break;
            case Web_SpecilaDate:
                this.addWeb_SpecialDatePromotion(isEdit);
                break;
            case Web_SpecilPlace:
                this.addWeb_SpecialPlacePromotion(isEdit);
                break;
        }
    }

    /**
     * 增加或编辑网站会员等级策略
     */
    public void addWeb_ClientGradePromotion(boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/salesman/WebPromotion_ClientGradeAddView.fxml"));
            ScrollPane view = loader.load();

            WebPromotion_ClientGradeAddViewController webPromotion_clientGradeAddViewController = loader.getController();
            webPromotion_clientGradeAddViewController.setWebPromotionViewController(this);
            webPromotion_clientGradeAddViewController.setMainUIController(mainUIController);
            if(isEdit){
                webPromotion_clientGradeAddViewController.showEditView(promotionVO);
            }

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加或编辑网站特殊期间折扣策略
     */
    public void addWeb_SpecialDatePromotion(boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/salesman/WebPromotion_SpecialDateAddView.fxml"));
            ScrollPane view = loader.load();

            WebPromotion_SpecialDateAddViewController webPromotion_specialDateAddViewController = loader.getController();
            webPromotion_specialDateAddViewController.setWebPromotionViewController(this);
            webPromotion_specialDateAddViewController.setMainUIController(mainUIController);
            if(isEdit){
                webPromotion_specialDateAddViewController.showEditView(promotionVO);
            }

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加或编辑网站特殊商圈折扣策略
     */
    public void addWeb_SpecialPlacePromotion(boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/salesman/WebPromotion_SpecialPlaceAddView.fxml"));
            ScrollPane view = loader.load();

            WebPromotion_SpecialPlaceAddViewController webPromotion_specialPlaceAddViewController = loader.getController();
            webPromotion_specialPlaceAddViewController.setWebPromotionViewController(this);
            webPromotion_specialPlaceAddViewController.setMainUIController(mainUIController);
           if(isEdit){
                webPromotion_specialPlaceAddViewController.showEditView(promotionVO);
            }

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
