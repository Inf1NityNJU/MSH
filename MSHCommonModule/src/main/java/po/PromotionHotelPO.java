package po;

import util.DateUtil;
import util.Place;
import util.PromotionType;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class PromotionHotelPO {

    /**
     * 策略编号
     */
    private String promotionID;

    /**
     * 策略类型
     */
    private PromotionType promotionType;

    /**
     * 策略执行开始日期
     */
    private DateUtil startDate;

    /**
     * 策略执行结束日期
     */
    private DateUtil endDate;

    /**
     * 策略折扣
     */
    private double promotionDiscount;

    /**
     * 策略涉及的公司名称
     */
    private String companyName;

    /**
     * 策略涉及的酒店编号
     */
    private String hotelID;

    /**
     * 执行策略所需的最低房间数量
     */
    private int roomQuantity;


    /**
     *
     * @param promptionID
     * @param promotionType
     * @param startDate
     * @param endDate
     * @param promotionDiscount
     * @param companyName
     * @param hotelID
     * @param roomQuantity
     */
    public PromotionHotelPO(String promptionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, String companyName, String hotelID,  int roomQuantity) {
        this.promotionID = promptionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.companyName = companyName;
        this.hotelID = hotelID;
        this.roomQuantity = roomQuantity;
    }

    public String getPromptionID() {
        return promotionID;
    }

    public void setPromptionID(String promptionID) {
        this.promotionID = promptionID;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public DateUtil getStartDate() {
        return startDate;
    }

    public void setStartDate(DateUtil startDate) {
        this.startDate = startDate;
    }

    public DateUtil getEndDate() {
        return endDate;
    }

    public void setEndDate(DateUtil endDate) {
        this.endDate = endDate;
    }

    public double getPromotionDiscount() {
        return promotionDiscount;
    }

    public void setPromotionDiscount(double promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
}
