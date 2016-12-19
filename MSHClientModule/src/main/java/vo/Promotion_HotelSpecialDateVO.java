package vo;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_HotelSpecialDateVO extends Promotion_HotelVO {

    /**
     * 策略执行开始日期
     */
    public DateUtil startDate;

    /**
     * 策略执行结束日期
     */
    public DateUtil endDate;

    /**
     * @param promotionName     策略名称
     * @param promotionType     策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate         策略起始日期
     * @param endDate           策略截止日期
     */
    public Promotion_HotelSpecialDateVO(String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, String hotelID) {
        super(promotionName, promotionType, promotionDiscount, hotelID);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Promotion_HotelSpecialDateVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, String hotelID) {
        super(promotionID, promotionName, promotionType, promotionDiscount, hotelID);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Promotion_HotelSpecialDateVO(PromotionPO promotionPO) {
        super(promotionPO);
        this.startDate = new DateUtil(promotionPO.getStartDate());
        this.endDate = new DateUtil(promotionPO.getEndDate());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Promotion_HotelSpecialDateVO) {
            Promotion_HotelSpecialDateVO promotion_HotelSpecialDateVO = (Promotion_HotelSpecialDateVO) o;
            return compareData(promotion_HotelSpecialDateVO);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_HotelSpecialDateVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionName, this.promotionName)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate, this.startDate)
                && judgeEqual(pvo.endDate, this.endDate)
                && judgeEqual(pvo.promotionDiscount, this.promotionDiscount);
    }

    @Override
    public PromotionPO toPO() {
        if (this.promotionID == null) {
            return new PromotionPO(this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    "", this.hotelID, 0, null, null, 0);
        } else {
            return new PromotionPO(this.promotionID, this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    "", this.hotelID, 0, null, null, 0);

        }

    }
}
