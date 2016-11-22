package dataservice.promotiondataservice;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
import util.PromotionType;
import util.ResultMessage;

/**
 * Created by vivian on 16/10/13.
 */
public class PromotionDataService_Driver {

    public void drive(PromotionDataService promotionDataService){
        ResultMessage result = promotionDataService.addPromotion(new PromotionPO("201610120102", PromotionType.Hotel_Birthday, 0.80, "2016-10-01", "2016-10-03","VivianCompany" ,"00000000",1, Place.XINJIEKOU, 0));
        if(result== ResultMessage.SUCCESS){
            System.out.println("Add Success");
        }else{
            System.out.println("Add Failed");
        }

        result = promotionDataService.deletePromotion("201610130101");
        if(result== ResultMessage.SUCCESS){
            System.out.println("Delete Success");
        }else{
            System.out.println("Delete Failed");
        }

        result = promotionDataService.updatePromotion("201610130102", new PromotionPO("201610120102", PromotionType.Hotel_Birthday, 0.80,"2016-10-01", "2016-10-03", "VivianCompany" ,"00000000",1, Place.XINJIEKOU, 0));
        if(result== ResultMessage.SUCCESS){
            System.out.println("Update Success");
        }else{
            System.out.println("Update Failed");
        }

        PromotionPO ppo = promotionDataService.searchByPromotionID("201610130102");
        if(ppo!=null){
            System.out.println("Get Success");
        }else{
            System.out.println("Get Failed");
        }


    }
}
