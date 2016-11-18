package po;

import util.DateUtil;
import util.OrderState;
import util.PromotionType;
import util.TimeUtil;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class OrderPO {

    /**
     * 订单ID
     */
    private String orderID;

    /**
     * 酒店ID
     */
    private String hotelID;

    /**
     * 客户ID
     */
    private String clientID;

    /**
     * 预计入住日期
     */
    private DateUtil checkInDate;

    /**
     * 预计退房日期
     */
    private DateUtil checkOutDate;

    /**
     * 实际入住时间
     */
    private TimeUtil checkInTime;

    /**
     * 实际退房时间
     */
    private TimeUtil checkOutTime;

    /**
     * 预定时间
     */
    private TimeUtil bookedTime;

    /**
     * 撤销时间
     */
    private TimeUtil cancelledTime;

    /**
     * 最晚执行时间
     */
    private TimeUtil latestExecuteTime;

    /**
     * 人数
     */
    private int peopleQuantity;

    /**
     * 有无儿童
     */
    private boolean hasChildren;

    /**
     * 订单状态
     */
    private OrderState state;

    /**
     * 网站促销策略类型
     */
    private PromotionType websitePromotionType;

    /**
     * 网站促销策略折扣
     */
    private double websitePromotionDiscount;

    /**
     * 酒店促销策略类型
     */
    private PromotionType hotelPromotionType;

    /**
     * 酒店促销策略折扣
     */
    private double hotelPromotionDiscount;

    /**
     * 原价
     */
    private double originPrice;

    /**
     * 总价
     */
    private double totalPrice;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public DateUtil getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(DateUtil checkInDate) {
        this.checkInDate = checkInDate;
    }

    public DateUtil getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(DateUtil checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public TimeUtil getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(TimeUtil checkInTime) {
        this.checkInTime = checkInTime;
    }

    public TimeUtil getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(TimeUtil checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public TimeUtil getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(TimeUtil bookedTime) {
        this.bookedTime = bookedTime;
    }

    public TimeUtil getCancelledTime() {
        return cancelledTime;
    }

    public void setCancelledTime(TimeUtil cancelledTime) {
        this.cancelledTime = cancelledTime;
    }

    public TimeUtil getLatestExecuteTime() {
        return latestExecuteTime;
    }

    public void setLatestExecuteTime(TimeUtil latestExecuteTime) {
        this.latestExecuteTime = latestExecuteTime;
    }

    public int getPeopleQuantity() {
        return peopleQuantity;
    }

    public void setPeopleQuantity(int peopleQuantity) {
        this.peopleQuantity = peopleQuantity;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public PromotionType getWebsitePromotionType() {
        return websitePromotionType;
    }

    public void setWebsitePromotionType(PromotionType websitePromotionType) {
        this.websitePromotionType = websitePromotionType;
    }

    public double getWebsitePromotionDiscount() {
        return websitePromotionDiscount;
    }

    public void setWebsitePromotionDiscount(double websitePromotionDiscount) {
        this.websitePromotionDiscount = websitePromotionDiscount;
    }

    public PromotionType getHotelPromotionType() {
        return hotelPromotionType;
    }

    public void setHotelPromotionType(PromotionType hotelPromotionType) {
        this.hotelPromotionType = hotelPromotionType;
    }

    public double getHotelPromotionDiscount() {
        return hotelPromotionDiscount;
    }

    public void setHotelPromotionDiscount(double hotelPromotionDiscount) {
        this.hotelPromotionDiscount = hotelPromotionDiscount;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderPO(String orderID, String hotelID, String clientID, DateUtil checkInDate, DateUtil checkOutDate,
                   TimeUtil checkInTime, TimeUtil checkOutTime, TimeUtil bookedTime, TimeUtil cancelledTime, TimeUtil latestExecuteTime,
                   int peopleQuantity, boolean hasChildren, OrderState state,
                   PromotionType websitePromotionType, double websitePromotionDiscount, PromotionType hotelPromotionType, double hotelPromotionDiscount,
                   double originPrice, double totalPrice) {
            this.orderID = orderID;
        this.hotelID = hotelID;
        this.clientID = clientID;

        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;

        this.bookedTime = bookedTime;
        this.cancelledTime = cancelledTime;

        this.latestExecuteTime = latestExecuteTime;
        this.peopleQuantity = peopleQuantity;
        this.hasChildren = hasChildren;

        this.state = state;
        this.websitePromotionType = websitePromotionType;
        this.websitePromotionDiscount = websitePromotionDiscount;
        this.hotelPromotionType = hotelPromotionType;
        this.hotelPromotionDiscount = hotelPromotionDiscount;
        this.originPrice = originPrice;
        this.totalPrice = totalPrice;
    }
}
