package dataservice.promotiondataservice;

import po.PromotionPO;
import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;

/**
 * Created by vivian on 16/10/13.
 */
public class PromotionDataService_Stub implements PromotionDataService{
    @Override
    public ResultMessage addPromotion(PromotionPO promotionpo) {
        if(promotionpo.getPromotionID().equals("201610120102")){
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
    public ResultMessage updatePromotion(String promotionID, PromotionPO newpropo) {
        if(promotionID.equals("201610130102")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public PromotionPO searchByPromotionID(String promotionID) {
        return new PromotionPO("201610120102",  PromotionType.Hotel_Birthday, 0.80,"2016-10-01", "2016-10-03","VivianCompany" ,"00000000",1, "XINJIEKOU", 0);
    }
}
