package vo;

import po.PromotionPO;
import util.DateUtil;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/22.
 */
public class Promotion_BirthdayVO extends Promotion_HotelVO {
    /**
     * 客户生日
     */
    public DateUtil bithday;

    /**
     * @param promotionID       策略编号
     * @param promotionType     策略类型
     * @param promotionDiscount 策略折扣
     * @param bithday           客户生日
     */
    public Promotion_BirthdayVO(String promotionID, PromotionType promotionType, double promotionDiscount,String hotelID, DateUtil bithday) {
        super(promotionID, promotionType, promotionDiscount, hotelID);
        this.bithday = bithday;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Promotion_BirthdayVO) {
            Promotion_BirthdayVO promotion_BirthdayVO = (Promotion_BirthdayVO) o;
            return compareData(promotion_BirthdayVO);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_BirthdayVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.promotionDiscount, this.promotionDiscount)
                && judgeEqual(pvo.bithday, this.bithday);
    }

    @Override
    public PromotionPO toPO() {
        return new PromotionPO(this.promotionID, this.promotionType, this.promotionDiscount,
                null, null,
                null, null, 0, null, 0, this.bithday.toString());
    }
}
