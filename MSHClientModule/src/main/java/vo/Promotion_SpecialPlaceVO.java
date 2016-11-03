package vo;

import util.DateUtil;
import util.PromotionType;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_SpecialPlaceVO {
    public String promotionID;
    public PromotionType promotionType;
    public DateUtil startDate;
    public DateUtil endDate;
    public double promotionDiscount;
    public String specialPlace;

    /**
     * VIP特定商圈折扣促销策略，包括策略ID，策略类型，优惠起始时间，优惠结束时间，策略折扣，特定商圈
     * @param promotionID
     * @param promotionType
     * @param startDate
     * @param endDate
     * @param promotionDiscount
     * @param specialPlace
     */
    public Promotion_SpecialPlaceVO(String promotionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, String specialPlace) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.specialPlace = specialPlace;
    }
}
