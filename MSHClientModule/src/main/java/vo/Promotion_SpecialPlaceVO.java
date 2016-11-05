package vo;

import util.DateUtil;
import util.Place;
import util.PromotionType;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_SpecialPlaceVO {
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

    public String specialPlace; /**
     * 策略涉及的商圈
     */
    public Place place;


    /**
     * VIP特定商圈折扣促销策略，包括策略ID，策略类型，优惠起始时间，优惠结束时间，策略折扣，特定商圈
     * @param promotionID
     * @param promotionType
     * @param startDate
     * @param endDate
     * @param promotionDiscount
     * @param place
     */
    public Promotion_SpecialPlaceVO(String promotionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, Place place) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.place = place;
    }
}
