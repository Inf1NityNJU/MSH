package dataimpl.promotiondataimpl;

import dataservice.promotiondataservice.PromotionDataService;
import org.junit.Test;
import po.PromotionPO;
import util.City;
import util.Place;
import util.PromotionType;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/13.
 */
public class PromotionDataService_Driver {

    @Test
    public void test() {
        PromotionDataService promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
        driver(promotionDataService);
    }

    public void driver(PromotionDataService promotionDataService){
        ResultMessage result = promotionDataService.addPromotion(new PromotionPO("10001", PromotionType.Hotel_Birthday, 0.80, "2016-10-01", "2016-10-03","VivianCompany" ,"00000000",1, City.NanJing, Place.XinJieKou, 0));
        if(result== ResultMessage.SUCCESS){
            System.out.println("Add Promotion Success");
        }else{
            System.out.println("Add Promotion Failed");
        }

        result = promotionDataService.deletePromotion("10001");
        if(result== ResultMessage.SUCCESS){
            System.out.println("Delete Promotion Success");
        }else{
            System.out.println("Delete Promotion Failed");
        }

        promotionDataService.addPromotion(new PromotionPO("10002", PromotionType.Hotel_Birthday, 0.80, "2016-10-01", "2016-10-03","VivianCompany" ,"00000000",1, City.NanJing, Place.XinJieKou, 0));
        result = promotionDataService.updatePromotion(new PromotionPO("10001", "10002", PromotionType.Hotel_Birthday, 0.90,"2016-10-01", "2016-10-03", "VivianCompany" ,"00000000",1, City.NanJing, Place.XinJieKou, 0));
        if(result== ResultMessage.SUCCESS){
            System.out.println("Update Promotion Success");
        }else{
            System.out.println("Update Promotion Failed");
        }

        PromotionPO ppo = promotionDataService.searchByPromotionID("10002");
        if(ppo!=null){
            System.out.println("Get Promotion Success");
        }else{
            System.out.println("Get Promotion Failed");
        }

        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchPromotionsByType(PromotionType.Hotel_Birthday);
        if(promotionPOs!=null){
            System.out.println("Get Promotions By Type Success");
        }else{
            System.out.println("Get Promotions By Type Failed");
        }

        promotionPOs = promotionDataService.searchHotelPromotions("00000000");
        if(promotionPOs!=null){
            System.out.println("Get Hotel Promotions Success");
        }else{
            System.out.println("Get Hotel Promotions Failed");
        }

        promotionPOs = promotionDataService.searchWebPromotions();
        if(promotionPOs!=null){
            System.out.println("Get Web Promotions Success");
        }else{
            System.out.println("Get Web Promotions Failed");
        }

        promotionDataService.deletePromotion("10001");
    }
}
