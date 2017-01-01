package datastubdriver.promotionstubdriver;

import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;
import util.City;
import util.Place;
import util.PromotionType;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/13.
 */
public class PromotionDataService_Stub implements PromotionDataService {
    @Override
    public ResultMessage addPromotion(PromotionPO promotionpo) {
        if(promotionpo.getPromotionID().equals("10001")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }

    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        if(promotionID.equals("10001")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage updatePromotion(PromotionPO newPromotionPO) {
        if(newPromotionPO.getPromotionID().equals("10002")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public PromotionPO searchByPromotionID(String promotionID) {
        return new PromotionPO("10002",  PromotionType.Hotel_Birthday, 0.80,"2016-10-01", "2016-10-03","VivianCompany" ,"00000000",1, City.NanJing, Place.XinJieKou, 0);
    }

    @Override
    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType) {
        ArrayList<PromotionPO> promotionPOs = new ArrayList<PromotionPO>();
        promotionPOs.add(new PromotionPO("10002",  PromotionType.Hotel_Birthday, 0.80,"2016-10-01", "2016-10-03","VivianCompany" ,"00000000",1, City.NanJing, Place.XinJieKou, 0));
        return promotionPOs;
    }

    @Override
    public ArrayList<PromotionPO> searchHotelPromotions(String hotelID) {
        ArrayList<PromotionPO> promotionPOs = new ArrayList<PromotionPO>();
        promotionPOs.add(new PromotionPO("10002",  PromotionType.Hotel_Birthday, 0.80,"2016-10-01", "2016-10-03","VivianCompany" ,"00000000",1, City.NanJing, Place.XinJieKou, 0));
        return promotionPOs;
    }

    @Override
    public ArrayList<PromotionPO> searchWebPromotions() {
        ArrayList<PromotionPO> promotionPOs = new ArrayList<PromotionPO>();
        promotionPOs.add(new PromotionPO("2", PromotionType.Web_SpecilaDate, 0.60, "2016-01-02", "2016-02-02", null, null, 0, null, null, 0));
        return promotionPOs;
    }
}
