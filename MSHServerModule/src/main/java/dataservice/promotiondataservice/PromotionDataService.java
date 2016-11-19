package dataservice.promotiondataservice;

import po.PromotionPO;
import util.ResultMessage;

/**
 * Created by vivian on 16/10/13.
 */
public interface PromotionDataService {
    public ResultMessage addPromotion(PromotionPO promotionpo);

    public ResultMessage deletePromotion(String promotionID);

    public ResultMessage updatePromotion(String promotionID, PromotionPO newpropo);

    public PromotionPO searchByPromotionID(String promotionID);
}
