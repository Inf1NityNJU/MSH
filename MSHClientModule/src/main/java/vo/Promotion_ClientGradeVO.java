package vo;

import util.DateUtil;
import util.PromotionType;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_ClientGradeVO {
    public String promotionID;
    public PromotionType promotionType;
    public DateUtil startDate;
    public DateUtil endDate;
    public double promotionDiscount;
    public int clientGrade;

    /**
     * 会员等级折扣促销策略，包括策略ID，策略类型，优惠起始时间，优惠结束时间，策略折扣，会员等级
     * @param promotionID
     * @param promotionType
     * @param startDate
     * @param endDate
     * @param promotionDiscount
     * @param clientGrade
     */
    public Promotion_ClientGradeVO(String promotionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, int clientGrade) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.clientGrade = clientGrade;
    }
}
