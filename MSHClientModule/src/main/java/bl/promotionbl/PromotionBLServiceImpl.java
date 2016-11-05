package bl.promotionbl;

import blservice.promotionblservice.PromotionBLService;
import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import vo.OrderRoomVO;
import vo.PromotionVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/5.
 */
public class PromotionBLServiceImpl implements PromotionBLService{

    private Promotion promotion;
    private MinPromotion minPromotion;

    protected PromotionBLServiceImpl(boolean isMock){
        if(isMock){
            promotion = new MockPromotion();
            minPromotion = new MockMinPromotion();
        }else {
            promotion = new Promotion();
            minPromotion = new MinPromotion();
        }
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
    public ResultMessage updatePromotion(String promotionID, PromotionVO newPvo) {
        return promotion.update(promotionID, newPvo);
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
    public ArrayList<PromotionVO> searchHotelPromotionsByHotelID(String HotelID) {
        return promotion.searchHotelPromotions(HotelID);
    }

    @Override
    public ArrayList<PromotionVO> searchWebPromotions() {
        return promotion.searchWebPromotions();
    }

    @Override
    public double getMinHotelProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID) {
        return minPromotion.getMinHotelProm(date, rvo, clientID, hotelID);
    }

    @Override
    public double getMinWebProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID) {
        return minPromotion.getMinWebProm(date, rvo, clientID, hotelID);
    }
}
