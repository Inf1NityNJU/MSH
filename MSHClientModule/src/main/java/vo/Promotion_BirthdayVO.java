package vo;

import util.DateUtil;
import util.PromotionType;

/**
 * Created by vivian on 16/10/22.
 */
public class Promotion_BirthdayVO {
    public String promotionID;
    public PromotionType promotionType;
    public DateUtil bithday;
    public double promotionDiscount;

    /**
     * 生日折扣促销策略，包含策略ID，策略类型，客户生日，策略折扣
     * @param promotionID
     * @param promotionType
     * @param bithday
     * @param promotionDiscount
     */
    public Promotion_BirthdayVO(String promotionID, PromotionType promotionType, DateUtil bithday, double promotionDiscount) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.bithday = bithday;
        this.promotionDiscount = promotionDiscount;
    }
}
