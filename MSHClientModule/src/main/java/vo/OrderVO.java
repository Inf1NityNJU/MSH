package vo;

import po.AssessmentPO;
import po.OrderPO;
import util.DateUtil;
import util.OrderState;
import util.PromotionType;
import util.TimeUtil;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/12.
 */
public class OrderVO {

    /**
     * 订单编号
     */
    public String orderID;

    /**
     * 酒店编号
     */
    public String hotelID;

    /**
     * 客户编号
     */
    public String clientID;

    /**
     * 酒店名称
     */
    public String hotelName;

    /**
     * 客户姓名
     */
    public String clientName;

    /**
     * 房间
     */
    public ArrayList<OrderRoomVO> rooms;

    /**
     * 预计入住日期
     */
    public DateUtil checkInDate;

    /**
     * 预计退房日期
     */
    public DateUtil checkOutDate;

    /**
     * 实际入住时间
     */
    public TimeUtil checkInTime;

    /**
     * 预计退房时间
     */
    public TimeUtil checkOutTime;

    /**
     * 预定时间
     */
    public TimeUtil bookedTime;

    /**
     * 撤销时间
     */
    public TimeUtil cancelledTime;

    /**
     * 最晚订单执行时间
     */
    public TimeUtil latestExecuteTime;

    /**
     * 入住人数
     */
    public int peopleQuantity;

    /**
     * 有无儿童
     */
    public boolean hasChildren;

    /**
     * 订单状态
     */
    public OrderState state;

    /**
     * 原价总价
     */
    public BillVO bill;

    /**
     * 评分评价
     */
    public AssessmentVO assessment;

    public OrderVO(String hotelID, DateUtil checkInDate, DateUtil checkOutDate) {
        this.hotelID = hotelID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public OrderVO(String orderID, String hotelID, String clientID, String hotelName, String clientName,
                   ArrayList<OrderRoomVO> rooms,
                   DateUtil checkInDate, DateUtil checkOutDate, TimeUtil checkInTime, TimeUtil checkOutTime,
                   TimeUtil bookedTime, TimeUtil cancelledTime, TimeUtil latestExecuteTime, int peopleQuantity, boolean hasChildren,
                   OrderState state, BillVO bill, AssessmentVO assessment) {
        this.orderID = orderID;
        this.hotelID = hotelID;
        this.clientID = clientID;

        this.hotelName = hotelName;
        this.clientName = clientName;

        this.rooms = rooms;

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
        this.bill = bill;
        this.assessment = assessment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderVO) {
            OrderVO order = (OrderVO)obj;
            return orderID.equals(order.orderID);
        }
        return false;
    }

    public OrderPO toPO() {
        String websitePromotionName = null;
        double websitePromotionDiscount = 1;
        String hotelPromotionName = null;
        double hotelPromotionDiscount = 1;

        if (bill.websitePromotion != null) {
            websitePromotionName = bill.websitePromotion.promotionName;
            websitePromotionDiscount = bill.websitePromotion.promotionDiscount;
        }

        if (bill.hotelPromotion != null) {
            hotelPromotionName = bill.hotelPromotion.promotionName;
            hotelPromotionDiscount = bill.hotelPromotion.promotionDiscount;
        }
        return new OrderPO(orderID, hotelID, clientID,
                checkInDate, checkOutDate, checkInTime, checkOutTime,
                bookedTime, cancelledTime, latestExecuteTime,
                peopleQuantity, hasChildren, state,
                websitePromotionName, websitePromotionDiscount,
                hotelPromotionName, hotelPromotionDiscount,
                bill.originPrice, bill.totalPrice);
    }

    public OrderVO(OrderPO orderPO, String hotelName, String clientName,
                   ArrayList<OrderRoomVO> rooms, BillVO bill, AssessmentVO assessment) {

        this.orderID = orderPO.getOrderID();
        this.hotelID = orderPO.getHotelID();
        this.clientID = orderPO.getClientID();

        this.hotelName = hotelName;
        this.clientName = clientName;

        this.rooms = rooms;

        this.checkInDate = new DateUtil(orderPO.getCheckInDate());
        this.checkOutDate = new DateUtil(orderPO.getCheckOutDate());
        this.checkInTime = orderPO.getCheckInTime() != null ? new TimeUtil(orderPO.getCheckInTime()) : null;
        this.checkOutTime = orderPO.getCheckOutTime() != null ? new TimeUtil(orderPO.getCheckOutTime()) : null;

        this.bookedTime = new TimeUtil(orderPO.getBookedTime());
        this.cancelledTime = orderPO.getCancelledTime() != null ? new TimeUtil(orderPO.getCancelledTime()) : null;

        this.latestExecuteTime = orderPO.getLatestExecuteTime() != null ? new TimeUtil(orderPO.getLatestExecuteTime()) : null;
        this.peopleQuantity = orderPO.getPeopleQuantity();
        this.hasChildren = orderPO.isHasChildren();

        this.state = orderPO.getState();
        this.bill = bill;
        this.assessment = assessment;
    }
}
