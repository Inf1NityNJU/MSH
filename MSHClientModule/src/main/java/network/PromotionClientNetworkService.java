package network;

import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/13.
 */
public interface PromotionClientNetworkService {
    public ResultMessage addPromotion(PromotionPO promotionpo);

    public ResultMessage deletePromotion(String promotionID);

    public ResultMessage updatePromotion(PromotionPO newPromotionPO);

    public PromotionPO searchByPromotionID(String promotionID);

    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType);

    public ArrayList<PromotionPO> searchHotelPromotions(String hotelID);

    public ArrayList<PromotionPO> searchWebPromotions();
}
