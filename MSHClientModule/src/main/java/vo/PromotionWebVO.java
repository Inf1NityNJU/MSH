package vo;

import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/11/13.
 */
public class PromotionWebVO {
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
     * 策略涉及的商圈
     */
    public Place place;

    /**
     * 执行策略所需的最低用户等级
     */
    public int clientGrade;

    public PromotionWebVO(String promotionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, Place place, int clientGrade) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.place = place;
        this.clientGrade = clientGrade;
    }

    public boolean equals(Object o){
        if (o instanceof PromotionWebVO) {
            PromotionWebVO PromotionWebVO = (PromotionWebVO) o;
            return compareData(PromotionWebVO);
        }
        return false;
    }

    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(PromotionWebVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount)
                && judgeEqual(pvo.place, this.place)
                && judgeEqual(pvo.clientGrade,this.clientGrade);
    }
}
