package dataservice.promotiondataservice;

import po.PromotionHotelPO;
import util.ResultMessage;

/**
 * Created by vivian on 16/10/13.
 */
public interface PromotionDataService {
    public ResultMessage addPromotion(PromotionHotelPO promotionpo);

    public ResultMessage deletePromotion(String promotionID);

    public ResultMessage updatePromotion(String promotionID, PromotionHotelPO newpropo);

    public PromotionHotelPO searchByPromotionID(String promotionID);
}
