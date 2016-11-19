package blservice.promotionblservice;


import util.PromotionType;
import util.ResultMessage;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/12.
 */
public interface PromotionBLService {
    /**
     * 增加一种新的营销策略
     * @param pvo
     * @return
     */
    public ResultMessage addPromotion(Promotion_HotelVO pvo);

    /**
     * 删除制定营销策略
     * @param promotionID
     * @return
     */
    public ResultMessage deletePromotion(String promotionID);

    /**
     * 更新某条酒店/网站促销策略的信息
     * @param promotionID
     * @param newPvo
     * @return
     */
    public ResultMessage updatePromotion(String promotionID, Promotion_HotelVO newPvo);

    /**
     * 查找某条酒店/网站促销策略
     * @param promotionID
     * @return
     */
    public Promotion_HotelVO searchByPromotionID(String promotionID);

    /**
     * 搜索某个种类的所有促销策略
     * @param promotionType
     * @return
     */
    public ArrayList<Promotion_HotelVO> searchPromotions(PromotionType promotionType);

    /**
     * 查看某个酒店的所有促销策略
     * @param HotelID
     * @return
     */
    public ArrayList<Promotion_HotelVO> searchHotelPromotions(String HotelID);

    /**
     * 查看网站的所有促销策略
     * @return
     */
    public ArrayList<Promotion_WebVO> searchWebPromotions();


}
