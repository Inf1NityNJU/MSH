package vo;

import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class PromotionHotelVO {

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
     * 策略涉及的酒店编号
     */
    public String hotelID;

    /**
     * 执行策略所需的最低房间数量
     */
    public int roomQuantity;

    /**
     *
     * @param promotionID
     * @param promotionType
     * @param startDate
     * @param endDate
     * @param promotionDiscount
     * @param companyName
     * @param hotelID
     * @param roomQuantity
     */
    public PromotionHotelVO(String promotionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, String companyName, String hotelID, int roomQuantity) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.companyName = companyName;
        this.hotelID = hotelID;
        this.roomQuantity = roomQuantity;
    }

    public boolean equals(Object o){
        if (o instanceof PromotionHotelVO) {
            PromotionHotelVO promotionHotelVO = (PromotionHotelVO) o;
            return compareData(promotionHotelVO);
        }
        return false;
    }

    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(PromotionHotelVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount)
                && judgeEqual(pvo.companyName, this.companyName)
                && judgeEqual(pvo.hotelID,this.hotelID)
                && judgeEqual(pvo.roomQuantity, this.roomQuantity);
    }
}
