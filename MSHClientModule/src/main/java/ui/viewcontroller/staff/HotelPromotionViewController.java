package ui.viewcontroller.staff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import ui.viewcontroller.common.MainUIController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by vivian on 16/12/8.
 */
public class HotelPromotionViewController {
    private MainUIController mainUIController;

    private Node initNode;
    private Stack<Node> stack = new Stack<Node>();

    private HotelPromotionListViewController hotelPromotionListViewController;
    private HotelPromotionDetailViewController hotelPromotionDetailViewController;

    private PromotionVO promotionVO;

    public HotelPromotionViewController(MainUIController mainUIController) {
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
    public void showHotelPromotionList() {
        if (initNode != null) {
            stack.clear();
            mainUIController.setCenter(initNode);
            return;
        }

        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("/view/staff/HotelPromotionListView.fxml"));
            ScrollPane list = listLoader.load();

            hotelPromotionListViewController = listLoader.getController();
            hotelPromotionListViewController.setHotelPromotionViewController(this);

            initNode = list;

            mainUIController.setCenter(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新策略列表
     */
    public void refreshHotelPromotionList() {
        hotelPromotionListViewController.refreshHotelPromotions();
    }

    /**
     * 展示策略详情
     *
     * @param promotionVO
     */
    public void showPromotionDetail(PromotionVO promotionVO) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/staff/HotelPromotionDetailView.fxml"));
            ScrollPane view = loader.load();

            hotelPromotionDetailViewController = loader.getController();
            hotelPromotionDetailViewController.setHotelPromotionViewController(this);
            hotelPromotionDetailViewController.setMainUIController(mainUIController);
            hotelPromotionDetailViewController.showHotelPromotionDetail(promotionVO);

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
    public void refreshHotelPromotionDetail(PromotionVO promotionVO) {
        hotelPromotionDetailViewController.showHotelPromotionDetail(promotionVO);
    }

    /**
     * 增加策略
     */
    public void addHotelPromotion(PromotionType promotionType, boolean isEdit) {
        switch (promotionType) {
            case Hotel_Birthday:
                this.addHotel_BirthdayPromotion(isEdit);
                break;
            case Hotel_Enterprise:
                this.addHotel_EnterprisePromotion(isEdit);
                break;
            case Hotel_RoomQuantity:
                this.addHotel_RoomQuantityPromotion(isEdit);
                break;
            case Hotel_SpecilaDate:
                this.addHotel_SpecialDatePromotion(isEdit);
                break;
        }
    }

    /**
     * 增加或编辑酒店生日策略
     */
    private void addHotel_BirthdayPromotion(boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/staff/HotelPromotion_BirthdayAddView.fxml"));
            ScrollPane view = loader.load();

            HotelPromotion_BirthdayAddViewController hotelPromotion_birthdayAddViewController = loader.getController();
            hotelPromotion_birthdayAddViewController.setHotelPromotionViewController(this);
            hotelPromotion_birthdayAddViewController.setMainUIController(mainUIController);
            if(isEdit){
                hotelPromotion_birthdayAddViewController.showEditView(promotionVO);
            }

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加或编辑酒店合作企业折扣策略
     */
    private void addHotel_EnterprisePromotion(boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/staff/HotelPromotion_EnterpriseAddView.fxml"));
            ScrollPane view = loader.load();

            HotelPromotion_EnterpriseAddViewController hotelPromotion_enterpriseAddViewController = loader.getController();
            hotelPromotion_enterpriseAddViewController.setHotelPromotionViewController(this);
            hotelPromotion_enterpriseAddViewController.setMainUIController(mainUIController);
            if(isEdit){
                hotelPromotion_enterpriseAddViewController.showEditView(promotionVO);
            }

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加或编辑房间数量折扣策略
     */
    private void addHotel_RoomQuantityPromotion(boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/staff/HotelPromotion_RoomQuantityAddView.fxml"));
            ScrollPane view = loader.load();

            HotelPromotion_RoomQuantityAddViewController hotelPromotion_roomQuantityAddViewController = loader.getController();
            hotelPromotion_roomQuantityAddViewController.setHotelPromotionViewController(this);
            hotelPromotion_roomQuantityAddViewController.setMainUIController(mainUIController);
            if(isEdit){
                hotelPromotion_roomQuantityAddViewController.showEditView(promotionVO);
            }

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加或编辑特定期间折扣策略
     */
    private void addHotel_SpecialDatePromotion(boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/staff/HotelPromotion_SpecialDateAddView.fxml"));
            ScrollPane view = loader.load();

            HotelPromotion_SpecialDateAddViewController hotelPromotion_specialDateAddViewController = loader.getController();
            hotelPromotion_specialDateAddViewController.setHotelPromotionViewController(this);
            hotelPromotion_specialDateAddViewController.setMainUIController(mainUIController);
            if(isEdit){
                hotelPromotion_specialDateAddViewController.showEditView(promotionVO);
            }

            Node node = mainUIController.getCenter();
            stack.push(node);

            mainUIController.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编辑策略
     */
    public void showPromotionDetailEditView(PromotionVO promotionVO) {
        this.promotionVO = promotionVO;
        this.addHotelPromotion(promotionVO.promotionType, true);
    }
}
