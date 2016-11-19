package vo;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_SpecialPlaceVO extends Promotion_WebVO{

    /**
     * 策略涉及的商圈
     */
    public Place place;


    /**
     *
     * @param promotionID 策略编号
     * @param promotionType 策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate 策略起始日期
     * @param endDate 策略截止日期
     * @param place 策略涉及的商圈
     */
    public Promotion_SpecialPlaceVO(String promotionID, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, Place place) {
        super(promotionID, promotionType, promotionDiscount, startDate, endDate);
        this.place = place;
    }

    public boolean equals(Object o){
        if (o instanceof Promotion_SpecialPlaceVO) {
            Promotion_SpecialPlaceVO promotion_SpecialPlaceVO = (Promotion_SpecialPlaceVO) o;
            return compareData(promotion_SpecialPlaceVO);
        }
        return false;
    }

    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_SpecialPlaceVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount)
                && judgeEqual(pvo.place,this.place);
    }

    public PromotionPO toPO(Promotion_SpecialPlaceVO pvo) {
        return new PromotionPO(pvo.promotionID, pvo.promotionType, pvo.promotionDiscount,
                pvo.startDate.toString(), pvo.endDate.toString(),
                null, null, 0, pvo.place.toString(), 0);
    }
}
