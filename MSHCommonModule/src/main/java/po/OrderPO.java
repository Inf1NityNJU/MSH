package po;

import util.DateUtil;
import util.OrderState;
import util.PromotionType;
import util.TimeUtil;

import java.io.Serializable;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class OrderPO implements Serializable {

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
    private String  checkInDate;

    /**
     * 预计退房日期
     */
    private String checkOutDate;

    /**
     * 实际入住时间
     */
    private String checkInTime;

    /**
     * 实际退房时间
     */
    private String checkOutTime;

    /**
     * 预定时间
     */
    private String bookedTime;

    /**
     * 撤销时间
     */
    private String cancelledTime;

    /**
     * 最晚执行时间
     */
    private String latestExecuteTime;

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
    private String websitePromotionName;

    /**
     * 网站促销策略折扣
     */
    private double websitePromotionDiscount;

    /**
     * 酒店促销策略类型
     */
    private String hotelPromotionName;

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

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(String bookedTime) {
        this.bookedTime = bookedTime;
    }

    public String getCancelledTime() {
        return cancelledTime;
    }

    public void setCancelledTime(String cancelledTime) {
        this.cancelledTime = cancelledTime;
    }

    public String getLatestExecuteTime() {
        return latestExecuteTime;
    }

    public void setLatestExecuteTime(String latestExecuteTime) {
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

    public String getWebsitePromotionName() {
        return websitePromotionName;
    }

    public void setWebsitePromotionName(String websitePromotionName) {
        this.websitePromotionName = websitePromotionName;
    }

    public double getWebsitePromotionDiscount() {
        return websitePromotionDiscount;
    }

    public void setWebsitePromotionDiscount(double websitePromotionDiscount) {
        this.websitePromotionDiscount = websitePromotionDiscount;
    }

    public String getHotelPromotionName() {
        return hotelPromotionName;
    }

    public void setHotelPromotionName(String hotelPromotionName) {
        this.hotelPromotionName = hotelPromotionName;
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

    public OrderPO() {

    }

    public OrderPO(String orderID, String hotelID, String clientID, DateUtil checkInDate, DateUtil checkOutDate,
                   TimeUtil checkInTime, TimeUtil checkOutTime, TimeUtil bookedTime, TimeUtil cancelledTime, TimeUtil latestExecuteTime,
                   int peopleQuantity, boolean hasChildren, OrderState state,
                   String websitePromotionName, double websitePromotionDiscount, String hotelPromotionName, double hotelPromotionDiscount,
                   double originPrice, double totalPrice) {
            this.orderID = orderID;
        this.hotelID = hotelID;
        this.clientID = clientID;

        this.checkInDate = checkInDate.toString();
        this.checkOutDate = checkOutDate.toString();
        this.checkInTime = checkInTime != null ? checkInTime.toString() : null;
        this.checkOutTime = checkOutTime != null ? checkOutTime.toString() : null;

        this.bookedTime = bookedTime.toString();
        this.cancelledTime = cancelledTime != null ? cancelledTime.toString() : null;

        this.latestExecuteTime = latestExecuteTime.toString();
        this.peopleQuantity = peopleQuantity;
        this.hasChildren = hasChildren;

        this.state = state;
        this.websitePromotionName = websitePromotionName;
        this.websitePromotionDiscount = websitePromotionDiscount;
        this.hotelPromotionName = hotelPromotionName;
        this.hotelPromotionDiscount = hotelPromotionDiscount;
        this.originPrice = originPrice;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OrderPO) {
            OrderPO orderPO = (OrderPO) o;
            return orderPO.getOrderID().equals(orderID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return orderID.hashCode();
    }

}
