package vo;

import util.DateUtil;
import util.PromotionType;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_CompanyVO {
    public String promotionID;
    public PromotionType promotionType;
    public DateUtil startDate;
    public DateUtil endDate;
    public double promotionDiscount;
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
