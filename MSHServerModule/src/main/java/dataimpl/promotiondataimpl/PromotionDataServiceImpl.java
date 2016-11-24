package dataimpl.promotiondataimpl;

import datahelper.DataHelper;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/22.
 */
public class PromotionDataServiceImpl implements PromotionDataService{
    private DataHelper<PromotionPO> promotionDataHelper;

    protected PromotionDataServiceImpl(DataHelper<PromotionPO> dataHelper){
        this.promotionDataHelper = dataHelper;
    }


    @Override
    public ResultMessage addPromotion(PromotionPO promotionpo) {
        return promotionDataHelper.save(promotionpo);
    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        return promotionDataHelper.delete("promotionID",promotionID);
    }

    @Override
    public ResultMessage updatePromotion(PromotionPO newPromotionPO) {
        return promotionDataHelper.update(newPromotionPO);
    }

    @Override
    public PromotionPO searchByPromotionID(String promotionID) {
        return promotionDataHelper.exactlyQuery("promotionID", promotionID);
    }

    @Override
    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType) {
        ArrayList<PromotionPO> promotionPOs = promotionDataHelper.prefixMatchQuery("promotionType", promotionType.toString());
        return promotionPOs;
    }

    @Override
    public ArrayList<PromotionPO> searchHotelPromotions(String hotelID) {
        return null;
    }
}
