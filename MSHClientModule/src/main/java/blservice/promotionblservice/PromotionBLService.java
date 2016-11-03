package blservice.promotionblservice;


import util.DateUtil;
import util.ResultMessage;
import vo.OrderRoomVO;
import vo.PromotionVO;

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
    public ResultMessage addPromotion(PromotionVO pvo);

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
    public ResultMessage updatePromotion(String promotionID, PromotionVO newPvo);

    /**
     * 查找某条酒店/网站促销策略
     * @param promotionID
     * @return
     */
    public PromotionVO searchByPromotionID(String promotionID);

    /**
     * 查看某个酒店的所有促销策略
     * @param HotelID
     * @return
     */
    public ArrayList<PromotionVO> searchHotelPromotionsByHotelID(String HotelID);

    /**
     * 查看网站的所有促销策略
     * @return
     */
    public ArrayList<PromotionVO> searchWebPromotions();

    /**
     * 得到该订单可以得到的最低酒店促销折扣
     * @param date
     * @param rvo
     * @param clientID
     * @param hotelID
     * @return
     */
    public double getMinHotelProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID);

    /**
     * 得到该订单可以得到的最低网站促销折扣
     * @param date
     * @param rvo
     * @param clientID
     * @param hotelID
     * @return
     */
    public double getMinWebProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID);
}
