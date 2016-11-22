package dataimpl.promotion;

import dataimpl.promotiondataimpl.PromotionDataServiceFactory;
import dataimpl.promotiondataimpl.PromotionDataServiceImpl;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;

import static org.junit.Assert.assertEquals;

/**
 * Created by vivian on 16/11/22.
 */
public class PromotionDataServiceImplTest {
    private PromotionDataService promotionDataService;

    public PromotionDataServiceImplTest(){
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
    }

    public void addPromotion() throws Exception {
        ResultMessage resultMessage = promotionDataService.addPromotion(new PromotionPO("201611210101", PromotionType.Hotel_Birthday,0.80,"2016-11-01","2016-11-30",null,null,0,null,0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);

    }
}
