package bl.promotionbl;

import dataservice.promotiondataservice.PromotionDataService;
import dataservice.promotiondataservice.PromotionDataService_Stub;
import util.DateUtil;
import util.Place;
import util.PromotionType;
import vo.OrderRoomVO;
import vo.PromotionVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/2.
 */
public class MinPromotion {
    private double minWebProm = 0;
    private double minHotelProm = 0;
    private Promotion promotion = new Promotion();
    private ConcretePromotion concretePromotion;

    /**
     * 获得网站最小促销策略
     * @param date
     * @param clientGrade
     * @param place
     * @return
     */
    public double getMinWebProm(DateUtil date, int clientGrade, Place place) {
        double currentDiscount = 0;
        ArrayList<PromotionVO> promotionVOs = promotion.searchWebPromotions();
        for (int i = 0; i < promotionVOs.size(); i++) {
            switch (promotionVOs.get(i).promotionType) {
                case Web_ClientGrade:
                    concretePromotion = new Promotion_WebClientGrade(date);
                    currentDiscount = concretePromotion.getPromotionDiscount(promotionVOs.get(i), clientGrade);
                    break;
                case Web_SpecilaDate:
                    concretePromotion = new Promotion_WebSpecialDate(date);
                    currentDiscount = concretePromotion.getPromotionDiscount(promotionVOs.get(i), date);
                    break;
                default:
                    concretePromotion = new Promotion_WebSpecialPlace(date);
                    currentDiscount = concretePromotion.getPromotionDiscount(promotionVOs.get(i), place);
            }
            if(minWebProm>currentDiscount||currentDiscount==0){
                minWebProm = currentDiscount;
            }
        }
        return minWebProm;

    }

    /**
     * 获得最小酒店促销策略
     *
     * @param date
     * @param rvo
     * @param clientID
     * @param hotelID
     * @return 符合条件的最小酒店促销策略
     */
    public double getMinHotelProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID) {
        return 0.80;
    }


}
