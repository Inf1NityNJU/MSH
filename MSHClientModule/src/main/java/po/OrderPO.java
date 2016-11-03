package po;

import util.DateUtil;
import util.OrderState;
import util.PromotionType;
import util.TimeUtil;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class OrderPO {

    private String orderID;
    private String hotelID;
    private String clientID;

    private DateUtil checkInDate;
    private DateUtil checkOutDate;
    private TimeUtil checkInTime;
    private TimeUtil checkOutTime;
    private TimeUtil bookedTime;
    private TimeUtil cancelledTime;
    private TimeUtil latestExecuteTime;

    private int peopleQuantity;
    private boolean hasChildren;
    private OrderState state;

    private PromotionType websitePromotionType;
    private double websitePromotionDiscount;
    private PromotionType hotelPromotionType;
    private double hotelPromotionDiscount;
    private double originPrice;
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
