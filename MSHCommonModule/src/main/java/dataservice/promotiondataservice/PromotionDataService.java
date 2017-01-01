package dataservice.promotiondataservice;

import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/13.
 */
public interface PromotionDataService {

    /**
     * 增加一种新的营销策略
     * @param pvo
     * @return
     */
    public ResultMessage addPromotion(PromotionPO pvo);

    /**
     * 删除制定营销策略
     * @param promotionID
     * @return
     */
    public ResultMessage deletePromotion(String promotionID);

    /**
     * 更新某条酒店/网站促销策略的信息
     * @param newPvo
     * @return
     */
    public ResultMessage updatePromotion(PromotionPO newPvo);

    /**
     * 查找某条酒店/网站促销策略
     * @param promotionID
     * @return
     */
    public PromotionPO searchByPromotionID(String promotionID);

    /**
     * 搜索某个种类的所有促销策略
     * @param promotionType
     * @return
     */
    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType);

    /**
     * 查看某个酒店的所有促销策略
     * @param HotelID
     * @return
     */
    public ArrayList<PromotionPO> searchHotelPromotions(String HotelID);

    /**
     * 查看网站的所有促销策略
     * @return
     */
    public ArrayList<PromotionPO> searchWebPromotions();
}
