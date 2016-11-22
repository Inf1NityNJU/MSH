package dataimpl.promotiondataimpl;

import datahelper.DataHelper;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;
import util.ResultMessage;

/**
 * Created by vivian on 16/11/22.
 */
public class PromotionDataServiceImpl implements PromotionDataService{
    private DataHelper dataHelper;

    protected PromotionDataServiceImpl(DataHelper dataHelper){
        this.dataHelper = dataHelper;
    }


    @Override
    public ResultMessage addPromotion(PromotionPO promotionpo) {
        return dataHelper.save(promotionpo);
    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        return null;
    }

    @Override
    public ResultMessage updatePromotion(String promotionID, PromotionPO newpropo) {
        return null;
    }

    @Override
    public PromotionPO searchByPromotionID(String promotionID) {
        return null;
    }
}
