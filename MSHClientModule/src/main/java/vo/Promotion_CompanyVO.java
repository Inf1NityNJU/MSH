package vo;

import util.DateUtil;
import util.PromotionType;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_CompanyVO {

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
     * 策略涉及的公司名称
     */
    public String companyName;


    /**
     * 合作企业折扣促销策略，包括策略ID，策略类型，优惠起始时间，优惠结束时间，策略折扣，合作企业名称
     * @param promotionID
     * @param promotionType
     * @param promotionDiscount
     * @param startDate
     * @param endDate
     * @param companyName
     */
    public Promotion_CompanyVO(String promotionID, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, String companyName) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.promotionDiscount = promotionDiscount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.companyName = companyName;
    }
}
