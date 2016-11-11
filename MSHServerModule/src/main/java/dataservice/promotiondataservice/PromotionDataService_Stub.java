package dataservice.promotiondataservice;

import po.PromotionHotelPO;
import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;

/**
 * Created by vivian on 16/10/13.
 */
public class PromotionDataService_Stub implements PromotionDataService{
    @Override
    public ResultMessage addPromotion(PromotionHotelPO promotionpo) {
        if(promotionpo.getPromptionID().equals("201610120102")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }

    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        if(promotionID.equals("201610130101")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage updatePromotion(String promotionID, PromotionHotelPO newpropo) {
        if(promotionID.equals("201610130102")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public PromotionHotelPO searchByPromotionID(String promotionID) {
        return new PromotionHotelPO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, 0);
    }
}
