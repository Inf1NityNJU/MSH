package bl.promotionbl;

import blservice.promotionblservice.PromotionBLService;
import util.PromotionType;
import util.ResultMessage;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/5.
 */
public class PromotionBLServiceImpl implements PromotionBLService{

    private Promotion promotion;
//    private MinPromotion minPromotion;

    protected PromotionBLServiceImpl(boolean isMock){
//        if(isMock){
//            promotion = new MockPromotion();
//            minPromotion = new MockMinPromotion();
//        }else {
            promotion = new Promotion();
//            minPromotion = new MinPromotion();
//        }
    }
    @Override
    public ResultMessage addPromotion(Promotion_HotelVO pvo) {
        return promotion.add(pvo);
    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        return promotion.delete(promotionID);
    }

    @Override
    public ResultMessage updatePromotion(String promotionID, Promotion_HotelVO newPvo) {
        return promotion.update(promotionID, newPvo);
    }

    @Override
    public Promotion_HotelVO searchByPromotionID(String promotionID) {
        return promotion.searchByID(promotionID);
    }

    @Override
    public ArrayList<Promotion_HotelVO> searchPromotions(PromotionType promotionType) {
        return promotion.search(promotionType);
    }

    @Override
    public ArrayList<Promotion_HotelVO> searchHotelPromotions(String HotelID) {
        return promotion.searchHotelPromotions(HotelID);
    }

    @Override
    public ArrayList<Promotion_WebVO> searchWebPromotions() {
        return promotion.searchWebPromotions();
    }

}
