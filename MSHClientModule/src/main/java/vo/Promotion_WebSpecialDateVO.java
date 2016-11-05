package vo;

import util.DateUtil;
import util.PromotionType;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_WebSpecialDateVO {
    /**
     * 策略编号
     */
    public String promotionID;

    /**
     * 策略类型
     */
    public PromotionType promotionType;

    /**
     * 策略执行开始日期
     */
    public DateUtil startDate;

    /**
     * 策略执行结束日期
     */
    public DateUtil endDate;

    /**
     * 策略折扣
     */
    public double promotionDiscount;

    /**
     * 网站特定期间预订折扣促销策略，策略ID，策略类型，优惠起始时间，优惠结束时间，策略折扣
     * @param promotionID
     * @param promotionType
     * @param startDate
     * @param endDate
     * @param promotionDiscount
     */
    public Promotion_WebSpecialDateVO(String promotionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
    }
}
