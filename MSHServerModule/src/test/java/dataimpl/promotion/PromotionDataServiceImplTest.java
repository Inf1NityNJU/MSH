package dataimpl.promotion;

import dataimpl.promotiondataimpl.PromotionDataServiceFactory;
import dataservice.promotiondataservice.PromotionDataService;
import org.junit.Test;
import po.PromotionPO;
import util.Place;
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

    @Test
    public void addPromotion() throws Exception {
        ResultMessage resultMessage = promotionDataService.addPromotion(new PromotionPO("201611210106", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30","12","34",0, Place.TangShan,0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deletePromotion() throws Exception{
        ResultMessage resultMessage = promotionDataService.deletePromotion("201611210104");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updatePromotion() throws Exception {
        ResultMessage resultMessage = promotionDataService.updatePromotion(new PromotionPO("201611210105", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30","12","34",0, Place.TangShan,0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchPromotionByID() throws Exception{
        PromotionPO promotionPO = promotionDataService.searchByPromotionID("201611210105");
        PromotionPO promotionPO1 = new PromotionPO("201611210105", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30","12","34",0, Place.TangShan,0);
    }

//    @Test
//    public void searchPromotionsByType() throws Exception {
//        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchPromotionsByType(PromotionType.Web_ClientGrade);
//        ArrayList<PromotionPO> promotionPOsExpected = new ArrayList<PromotionPO>();
//        promotionPOsExpected.add(new PromotionPO("201611210105", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30","12","34",0, Place.TangShan,0));
//        promotionPOsExpected.add(new PromotionPO("201611210106", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30","12","34",0, Place.TangShan,0));
//        assertEquals(2,promotionPOs.size());
//    }
}
