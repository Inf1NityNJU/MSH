package dataimpl.promotion;

import dataimpl.promotiondataimpl.PromotionDataServiceFactory;
import dataservice.promotiondataservice.PromotionDataService;
import org.junit.Test;
import po.PromotionPO;
import util.City;
import util.Place;
import util.PromotionType;
import util.ResultMessage;

import java.util.ArrayList;

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
//        promotionDataService.deletePromotion("201611210101");
//        promotionDataService.deletePromotion("201611210102");
        ResultMessage resultMessage = promotionDataService.addPromotion(new PromotionPO("201611210101", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30","12","34",0, City.NanJing, Place.TangShan,0));
//        promotionDataService.addPromotion(new PromotionPO("201611210102", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30","12","34",0, Place.TangShan,0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deletePromotion() throws Exception{
        promotionDataService.addPromotion(new PromotionPO("201611210103", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30","12","34",0, City.NanJing, Place.TangShan,0));
        ResultMessage resultMessage = promotionDataService.deletePromotion("201611210103");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updatePromotion() throws Exception {
        ResultMessage resultMessage = promotionDataService.updatePromotion(new PromotionPO("201611210101", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30","12","34",0, City.NanJing, Place.TangShan,0));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchPromotionByID() throws Exception{
        PromotionPO promotionPO = promotionDataService.searchByPromotionID("201611210102");
//        PromotionPO promotionPO1 = new PromotionPO("201611210105", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30","12","34",0, Place.TangShan,0);
        assertEquals(new PromotionPO("201611210102", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30","12","34",0, City.NanJing, Place.TangShan,0),promotionPO);
    }

    @Test
    public void searchPromotionsByType() throws Exception {
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchPromotionsByType(PromotionType.Web_ClientGrade);
        ArrayList<PromotionPO> promotionPOsExpected = new ArrayList<PromotionPO>();
        promotionPOsExpected.add(new PromotionPO("201611210101", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30","12","34",0, City.NanJing, Place.TangShan,0));
        promotionPOsExpected.add(new PromotionPO("201611210102", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30","12","34",0, City.NanJing, Place.TangShan,0));
        assertEquals(promotionPOsExpected, promotionPOs);
    }

    @Test
    public void searchHotelPromotions() throws Exception{
        promotionDataService.addPromotion(new PromotionPO("201611240101", PromotionType.Hotel_RoomQuantity, 0.80, "2016-11-01", "2016-11-30", "", "00000000", 2, null, null, 0));
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchHotelPromotions("00000000");
        ArrayList<PromotionPO> promotionPOsExpected = new ArrayList<PromotionPO>();
        promotionPOsExpected.add(new PromotionPO("201611240101", PromotionType.Hotel_RoomQuantity, 0.80, "2016-11-01", "2016-11-30", "", "00000000", 2, null, null, 0));
        assertEquals(promotionPOsExpected, promotionPOs);
    }

    @Test
    public void searchWebPromotions() throws Exception{
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchWebPromotions();
        ArrayList<PromotionPO> promotionPOsExpected = new ArrayList<PromotionPO>();
        promotionPOsExpected.add(new PromotionPO("201611210101", PromotionType.Web_ClientGrade,0.90,"2016-11-01","2016-11-30","12","34",0, City.NanJing, Place.TangShan,0));
        promotionPOsExpected.add(new PromotionPO("201611210102", PromotionType.Web_ClientGrade,0.80,"2016-11-01","2016-11-30","12","34",0, City.NanJing, Place.TangShan,0));
        assertEquals(promotionPOsExpected, promotionPOs);
//        promotionDataService.deletePromotion("201611210101");
//        promotionDataService.deletePromotion("201611210102");
    }


}
