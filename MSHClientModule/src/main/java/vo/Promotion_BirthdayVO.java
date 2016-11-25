package vo;

import po.PromotionPO;
import util.DateUtil;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/22.
 */
public class Promotion_BirthdayVO extends Promotion_HotelVO {


    public Promotion_BirthdayVO(String promotionID, PromotionType promotionType, double promotionDiscount, String hotelID) {
        super(promotionID, promotionType, promotionDiscount, hotelID);
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
                && judgeEqual(pvo.promotionDiscount, this.promotionDiscount);
    }

    @Override
    public PromotionPO toPO() {
        return new PromotionPO(this.promotionID, this.promotionType, this.promotionDiscount,
                null, null,
                null, null, 0, null, 0);
    }
}
