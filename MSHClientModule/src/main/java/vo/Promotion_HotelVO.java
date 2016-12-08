package vo;

import po.PromotionPO;
import util.DateUtil;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class Promotion_HotelVO extends PromotionVO{

    public String hotelID;

    public Promotion_HotelVO(String promotionName, PromotionType promotionType, double promotionDiscount,String hotelID) {
        super(promotionName, promotionType, promotionDiscount);
        this.hotelID = hotelID;
    }

    public Promotion_HotelVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount,String hotelID) {
        super(promotionID, promotionName, promotionType, promotionDiscount);
        this.hotelID = hotelID;
    }

    public Promotion_HotelVO(PromotionPO promotionPO){
        super(promotionPO);
        this.hotelID = promotionPO.getHotelID();
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Promotion_HotelVO) {
            Promotion_HotelVO promotionHotelVO = (Promotion_HotelVO) o;
            return compareData(promotionHotelVO);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_HotelVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                &&judgeEqual(pvo.promotionName, this.promotionName)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount);
    }
}
