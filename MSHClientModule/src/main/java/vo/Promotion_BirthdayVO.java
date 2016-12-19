package vo;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/22.
 */
public class Promotion_BirthdayVO extends Promotion_HotelVO {
    public Promotion_BirthdayVO(String promotionName, PromotionType promotionType, double promotionDiscount, String hotelID) {
        super(promotionName, promotionType, promotionDiscount, hotelID);
    }

    public Promotion_BirthdayVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount, String hotelID) {
        super(promotionID, promotionName, promotionType, promotionDiscount, hotelID);
    }

    public Promotion_BirthdayVO(PromotionPO promotionPO){
        super(promotionPO);
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
                && judgeEqual(pvo.promotionName, this.promotionName)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.promotionDiscount, this.promotionDiscount);
    }

    @Override
    public PromotionPO toPO() {
        if(this.promotionID==null){
            return new PromotionPO(this.promotionName, this.promotionType, this.promotionDiscount,
                    "", "",
                    "", this.hotelID, 0, null, null, 0);
        }else {
            return new PromotionPO(this.promotionID, this.promotionName, this.promotionType, this.promotionDiscount,
                    "", "",
                    "", this.hotelID, 0, null, null, 0);
        }
    }
}
