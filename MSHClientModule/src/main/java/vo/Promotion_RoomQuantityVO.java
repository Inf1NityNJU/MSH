package vo;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
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
     * @param promotionName 策略名称
     * @param promotionType 策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate 策略起始日期
     * @param endDate 策略截止日期
     * @param roomQuantity 最低房间数量
     */
    public Promotion_RoomQuantityVO(String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, String hotelID, int roomQuantity) {
        super(promotionName, promotionType, promotionDiscount,hotelID);
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomQuantity = roomQuantity;
    }

    public Promotion_RoomQuantityVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, String hotelID, int roomQuantity) {
        super(promotionID, promotionName, promotionType, promotionDiscount,hotelID);
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomQuantity = roomQuantity;
    }

    public Promotion_RoomQuantityVO(PromotionPO promotionPO){
        super(promotionPO);
        this.startDate = new DateUtil(promotionPO.getStartDate());
        this.endDate = new DateUtil(promotionPO.getEndDate());
        this.roomQuantity = promotionPO.getRoomQuantity();
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Promotion_RoomQuantityVO) {
            Promotion_RoomQuantityVO promotion_RoomQuantityVO = (Promotion_RoomQuantityVO) o;
            return compareData(promotion_RoomQuantityVO);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_RoomQuantityVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionName, this.promotionName)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount)
                && judgeEqual(pvo.roomQuantity,this.roomQuantity);
    }

    @Override
    public PromotionPO toPO() {
        if(this.promotionID==null) {
            return new PromotionPO(this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    "", this.hotelID, this.roomQuantity, null, null, 0);
        }else {
            return new PromotionPO(this.promotionID, this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    "", this.hotelID, this.roomQuantity, null, null, 0);

        }
    }
}
