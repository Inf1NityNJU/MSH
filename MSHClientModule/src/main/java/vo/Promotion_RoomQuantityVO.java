package vo;

import po.PromotionPO;
import util.DateUtil;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/22.
 */
public class Promotion_RoomQuantityVO extends Promotion_HotelVO{



    /**
     * 策略执行开始日期
     */
    public DateUtil startDate;

    /**
     * 策略执行结束日期
     */
    public DateUtil endDate;


    /**
     * 执行策略所需的最低房间数量
     */
    public int roomQuantity;

    /**
     *
     * @param promotionID 策略编号
     * @param promotionType 策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate 策略起始日期
     * @param endDate 策略截止日期
     * @param roomQuantity 最低房间数量
     */
    public Promotion_RoomQuantityVO(String promotionID, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, int roomQuantity) {
        super(promotionID, promotionType, promotionDiscount);
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomQuantity = roomQuantity;
    }

    public boolean equals(Object o){
        if (o instanceof Promotion_RoomQuantityVO) {
            Promotion_RoomQuantityVO promotion_RoomQuantityVO = (Promotion_RoomQuantityVO) o;
            return compareData(promotion_RoomQuantityVO);
        }
        return false;
    }

    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_RoomQuantityVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount)
                && judgeEqual(pvo.roomQuantity,this.roomQuantity);
    }

    public PromotionPO toPO() {
        return new PromotionPO(this.promotionID, this.promotionType, this.promotionDiscount,
                this.startDate.toString(), this.endDate.toString(),
                null, null, this.roomQuantity, null, 0);
    }
}
