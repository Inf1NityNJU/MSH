package dataimpl.promotion;

import dataimpl.promotiondataimpl.PromotionDataServiceFactory;
import dataservice.promotiondataservice.PromotionDataService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import po.PromotionPO;
import util.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by vivian on 16/11/22.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PromotionDataServiceImplTest {
    private PromotionDataService promotionDataService;

    public PromotionDataServiceImplTest(){
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
    }

    @Test
    public void a_addPromotion() throws Exception {
        ResultMessage resultMessage = promotionDataService.addPromotion(new PromotionPO("1", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30",null,null,0, null, null,2));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        promotionDataService.addPromotion(new PromotionPO("2", PromotionType.Web_SpecilaDate, 0.60, "2016-01-02", "2016-02-02", null, null, 0, null, null, 0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = promotionDataService.addPromotion(new PromotionPO("3", PromotionType.Hotel_Birthday, 0.80, null, null, null, "00000000", 0, null, null, 0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
        resultMessage = promotionDataService.addPromotion(new PromotionPO("4", PromotionType.Hotel_RoomQuantity, 0.80, null, null, null, "00000000", 3, null, null, 0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void b_deletePromotion() throws Exception{
        ResultMessage resultMessage = promotionDataService.deletePromotion("10001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
//        promotionDataService.deletePromotion("10002");
//        promotionDataService.deletePromotion("20001");
    }

    @Test
    public void c_updatePromotion() throws Exception {
        ResultMessage resultMessage = promotionDataService.updatePromotion(new PromotionPO("20001", "1", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30",null,null,0, City.NanJing, Place.TangShan,0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void d_searchPromotionByID() throws Exception{
        PromotionPO promotionPO = promotionDataService.searchByPromotionID("20002");
        assertEquals(new PromotionPO("20002", "2", PromotionType.Web_SpecilaDate, 0.60, "2016-01-02", "2016-02-02", null, null, 0, null, null, 0),promotionPO);
    }

    @Test
    public void e_searchPromotionsByType() throws Exception {
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchPromotionsByType(PromotionType.Web_ClientGrade);
        ArrayList<PromotionPO> promotionPOsExpected = new ArrayList<PromotionPO>();
        promotionPOsExpected.add(new PromotionPO("20001", "1", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30",null,null,0, City.NanJing, Place.TangShan,0));
        assertEquals(promotionPOsExpected, promotionPOs);
    }

    @Test
    public void f_searchHotelPromotions() throws Exception{
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchHotelPromotions("00000000");
        ArrayList<PromotionPO> promotionPOsExpected = new ArrayList<PromotionPO>();
        promotionPOsExpected.add(new PromotionPO("10002", "4", PromotionType.Hotel_RoomQuantity, 0.80, null, null, null, "00000000", 3, null, null, 0));
        assertEquals(promotionPOsExpected, promotionPOs);
    }

    @Test
    public void g_searchWebPromotions() throws Exception{
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchWebPromotions();
        ArrayList<PromotionPO> promotionPOsExpected = new ArrayList<PromotionPO>();
        promotionPOsExpected.add(new PromotionPO("20001", "1", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30",null,null,0, City.NanJing, Place.TangShan,0));
        promotionPOsExpected.add(new PromotionPO("20002", "2", PromotionType.Web_SpecilaDate, 0.60, "2016-01-02", "2016-02-02", null, null, 0, null, null, 0));
        assertEquals(promotionPOsExpected, promotionPOs);
        promotionDataService.deletePromotion("20001");
        promotionDataService.deletePromotion("10002");
        promotionDataService.deletePromotion("20002");
    }


}
