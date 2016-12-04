package bl.promotionbl;

import blservice.promotionblservice.PromotionBLInfo;
import blservice.promotionblservice.PromotionBLService;
import util.DateUtil;
import util.Place;
import util.PromotionType;
import util.ResultMessage;
import vo.PromotionVO;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/5.
 */
public class PromotionBLServiceImpl implements PromotionBLService, PromotionBLInfo {

    private Promotion promotion;
    private MinPromotion minPromotion;

    public PromotionBLServiceImpl() {
        promotion = new Promotion();
        minPromotion = new MinPromotion();
    }


    @Override
    public ResultMessage addPromotion(PromotionVO pvo) {
        return promotion.add(pvo);
    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        return promotion.delete(promotionID);
    }

    @Override
    public ResultMessage updatePromotion(PromotionVO newPvo) {
        return promotion.update(newPvo);
    }

    @Override
    public PromotionVO searchByPromotionID(String promotionID) {
        return promotion.searchByID(promotionID);
    }

    @Override
    public ArrayList<PromotionVO> searchPromotions(PromotionType promotionType) {
        return promotion.search(promotionType);
    }

    @Override
    public ArrayList<PromotionVO> searchHotelPromotions(String HotelID) {
        return promotion.searchHotelPromotions(HotelID);
    }

    @Override
    public ArrayList<PromotionVO> searchWebPromotions() {
        return promotion.searchWebPromotions();
    }

    @Override
    public double getMinWebProm(DateUtil date, int clientGrade, Place place) {
        return minPromotion.getMinWebProm(date, clientGrade, place);
    }

    @Override
    public double getMinHotelProm(String hotelID, DateUtil date, DateUtil birthday, String enterpriseName, int roomQuantity) {
        return minPromotion.getMinHotelProm(hotelID,date,birthday, enterpriseName,roomQuantity);
    }
}
