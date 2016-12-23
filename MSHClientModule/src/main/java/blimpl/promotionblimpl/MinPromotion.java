package blimpl.promotionblimpl;

import util.City;
import util.DateUtil;
import util.Place;
import vo.PromotionVO;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/2.
 */
public class MinPromotion {
//    private double minWebPromDiscount = 0;
//    private double minHotelPromDiscount = 0;
//    private Promotion_WebVO promotion_webVO;
//    private Promotion_HotelVO promotion_hotelVO;
    private Promotion promotion = new Promotion();
//    private ConcretePromotion concretePromotion;

    /**
     * 获得网站最小促销策略
     *
     * @param date        下订单日期
     * @param clientGrade 客户等级
     * @param place       所在商圈
     * @return
     */
    public Promotion_WebVO getMinWebProm(DateUtil date, int clientGrade, City city, Place place) {
        Promotion_WebVO promotion_webVO = null;
        double minWebPromDiscount = 1;
        double currentDiscount = 1;
        ArrayList<PromotionVO> promotionVOs = promotion.searchWebPromotions();
        for (int i = 0; i < promotionVOs.size(); i++) {
            ConcretePromotion concretePromotion;

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
                    currentDiscount = concretePromotion.getPromotionDiscount(promotionVOs.get(i), city, place);
            }
            if (currentDiscount != 0 && minWebPromDiscount > currentDiscount || minWebPromDiscount == 0) {
                promotion_webVO = (Promotion_WebVO) promotionVOs.get(i);
                minWebPromDiscount = currentDiscount;
            }
        }
        return promotion_webVO;

    }

    /**
     * 获得最小酒店促销策略
     *
     * @param hotelID        酒店编号
     * @param date           下订单日期
     * @param birthday       客户生日
     * @param enterpriseName 客户所属企业名称
     * @param roomQuantity   所定房间数量
     * @return
     */
    public Promotion_HotelVO getMinHotelProm(String hotelID, DateUtil date, DateUtil birthday, String enterpriseName, int roomQuantity) {
        Promotion_HotelVO promotion_hotelVO = null;
        double minHotelPromDiscount = 1;
        double currentDiscount = 1;
        ArrayList<PromotionVO> promotionVOs = promotion.searchHotelPromotions(hotelID);
        for (int i = 0; i < promotionVOs.size(); i++) {
            ConcretePromotion concretePromotion;

            switch (promotionVOs.get(i).promotionType) {
                case Hotel_Birthday:
                    concretePromotion = new Promotion_HotelBirthday(date);
                    currentDiscount = concretePromotion.getPromotionDiscount(promotionVOs.get(i), birthday);
                    break;
                case Hotel_Enterprise:
                    concretePromotion = new Promotion_HotelEnterprise(date);
                    currentDiscount = concretePromotion.getPromotionDiscount(promotionVOs.get(i), enterpriseName);
                    break;
                case Hotel_RoomQuantity:
                    concretePromotion = new Promotion_HotelRoomQuantity(date);
                    currentDiscount = concretePromotion.getPromotionDiscount(promotionVOs.get(i), roomQuantity);
                    break;
                default:
                    concretePromotion = new Promotion_HotelSpecialDate(date);
                    currentDiscount = concretePromotion.getPromotionDiscount(promotionVOs.get(i), date);
            }
            if (currentDiscount != 0 && minHotelPromDiscount > currentDiscount || minHotelPromDiscount == 0) {
                promotion_hotelVO = (Promotion_HotelVO) promotionVOs.get(i);
                minHotelPromDiscount = currentDiscount;
            }
        }
        return promotion_hotelVO;
    }


}
