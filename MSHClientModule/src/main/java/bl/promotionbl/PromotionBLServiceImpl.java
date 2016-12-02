package bl.promotionbl;

import blservice.promotionblservice.PromotionBLService;
import util.PromotionType;
import util.ResultMessage;
import vo.PromotionVO;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/5.
 */
public class PromotionBLServiceImpl implements PromotionBLService{

    private Promotion promotion;
//    private MinPromotion minPromotion;

    public PromotionBLServiceImpl(){
            promotion = new Promotion();
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

}
