package ui.viewcontroller.salesman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import main.Main;
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

    public WebPromotionViewController(BorderPane rootPane) {
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
     * @param promotionVO
     */
    public void showPromotionDetail(PromotionVO promotionVO){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../view/salesman/WebPromotionDetailView.fxml"));
                ScrollPane view = loader.load();

                WebPromotionDetailViewController webPromotionDetailViewController = loader.getController();
                webPromotionDetailViewController.setWebPromotionViewController(this);
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
    public void refreshWebPromotionList(){
        webPromotionListViewController.showAllWebPromotions();
    }

    /**
     * 编辑策略
     */
    public void showPromotionDetailEditView(String type){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/salesman/WebPromotionDetailEditView.fxml"));
            ScrollPane view = loader.load();

            WebPromotionDetailEditViewController webPromotionDetailEditViewController = loader.getController();
            webPromotionDetailEditViewController.setWebPromotionViewController(this);
            webPromotionDetailEditViewController.show(type);

            Node node = rootPane.getCenter();
            stack.push(node);

            rootPane.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
