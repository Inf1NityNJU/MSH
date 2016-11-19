package po;

import util.PromotionType;

/**
 * Created by vivian on 16/11/11.
 */
public class PromotionPO {
    /**
     * 策略编号
     */
    private String promotionID;

    /**
     * 策略类型
     */
    private PromotionType promotionType;

    /**
     * 策略折扣
     */
    private double promotionDiscount;

    /**
     * 策略执行开始日期
     */
    private String startDate;

    /**
     * 策略执行结束日期
     */
    private String endDate;

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
     * 策略涉及的商圈
     */
    private String place;

    /**
     * 执行策略所需的最低用户等级
     */
    private int clientGrade;

    public String getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getClientGrade() {
        return clientGrade;
    }

    public void setClientGrade(int clientGrade) {
        this.clientGrade = clientGrade;
    }

    public PromotionPO(String promotionID, PromotionType promotionType, double promotionDiscount,String startDate, String endDate,  String companyName, String hotelID, int roomQuantity, String place, int clientGrade) {

        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.companyName = companyName;
        this.hotelID = hotelID;
        this.roomQuantity = roomQuantity;
        this.place = place;
        this.clientGrade = clientGrade;
    }
}
