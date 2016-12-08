package vo;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/11/13.
 */
public class Promotion_WebVO extends PromotionVO{

    /**
     * 策略执行开始日期
     */
    public DateUtil startDate;

    /**
     * 策略执行结束日期
     */
    public DateUtil endDate;

    /**
     *
     * @param promotionName 策略名称
     * @param promotionType 策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate 策略起始日期
     * @param endDate 策略截止日期
     */
    public Promotion_WebVO(String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate) {
        super(promotionName, promotionType, promotionDiscount);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Promotion_WebVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate) {
        super(promotionID, promotionName, promotionType, promotionDiscount);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Promotion_WebVO(PromotionPO promotionPO){
        super(promotionPO);
        this.startDate = new DateUtil(promotionPO.getStartDate());
        this.endDate = new DateUtil(promotionPO.getEndDate());
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Promotion_WebVO) {
            Promotion_WebVO PromotionWebVO = (Promotion_WebVO) o;
            return compareData(PromotionWebVO);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_WebVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionName, this.promotionName)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate);
    }
}
