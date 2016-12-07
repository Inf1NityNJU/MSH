package vo;

import po.PromotionPO;
import util.PromotionType;

/**
 * Created by vivian on 16/11/17.
 */
public class PromotionVO {
    /**
     * 策略编号
     */
    public String promotionID;

    /**
     * 策略名称
     */
    public String promotionName;

    /**
     * 策略类型
     */
    public PromotionType promotionType;

    /**
     * 策略折扣
     */
    public double promotionDiscount;

    /**
     *
     * @param promotionName 策略名称
     * @param promotionType 策略类型
     * @param promotionDiscount 策略折扣
     */
    public PromotionVO(String promotionName, PromotionType promotionType, double promotionDiscount) {
        this.promotionType = promotionType;
        this.promotionDiscount = promotionDiscount;
        this.promotionName=promotionName;
    }

    public PromotionVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.promotionDiscount = promotionDiscount;
        this.promotionName = promotionName;
    }
    public PromotionPO toPO(){
        return null;
    };

}
