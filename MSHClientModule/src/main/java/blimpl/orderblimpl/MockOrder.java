package blimpl.orderblimpl;

import blservice.hotelblservice.HotelBLInfo;
import blservice.userblservice.UserBLInfo;
import network.OrderClientNetworkService;
import util.*;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockOrder extends Order {

    public MockOrder(UserBLInfo userBLInfo, HotelBLInfo hotelBLInfo, OrderClientNetworkService orderClientNetworkService) {
        super(userBLInfo, hotelBLInfo, orderClientNetworkService);
    }

    /**
     * 修改订单房间数量
     *
     * @param type
     * @param quantity
     * @return 是否成功修改
     */
    public ResultMessage modifyRoomQuantity(RoomType type, int quantity) {
        if (type == RoomType.SingleRoom) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    /**
     * 得到账单
     *
     * @param date
     * @param start
     * @param end
     * @param birthday
     * @param hotelID
     * @param quantity
     * @return BillVO
     */
    public BillVO getBill(String hotelID, DateUtil date, DateUtil start, DateUtil end, DateUtil birthday, int quantity) {
        Promotion_HotelVO hotelPromotion = new Promotion_HotelVO("201610130101", PromotionType.Hotel_Birthday, 0.80,"00000000");
        return new BillVO(hotelPromotion, null, 300, 300);
    }

    /**
     * 生成订单
     *
     * @param latest
     * @param peopleQuantity
     * @param hasChildren
     * @return 是否成功生成
     */
    public ResultMessage generate(TimeUtil latest, int peopleQuantity, boolean hasChildren) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 撤销订单
     *
     * @param orderID
     * @return 是否成功撤销
     */
    public ResultMessage revoke(String orderID) {
        if (orderID.equals("20161026010112340000")) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    /**
     * 更新入住
     *
     * @param orderID
     * @param time
     * @return 是否成功
     */
    public ResultMessage checkIn(String orderID, TimeUtil time) {
        if (orderID.equals("20161026010112340000")) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    /**
     * 更新退房
     *
     * @param orderID
     * @param time
     * @return 是否成功
     */
    public ResultMessage checkOut(String orderID, TimeUtil time) {
        if (orderID.equals("20161026010112340000")) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    /**
     * 编辑评分评价
     *
     * @param orderID
     * @param assessment
     * @return 是否成功
     */
    public ResultMessage editAssessment(String orderID, AssessmentVO assessment) {
        if (orderID.equals("20161026010112340000")) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    /**
     * 通过订单ID搜索订单
     *
     * @param orderID
     * @return OrderVO
     */
    public OrderVO searchOrderByID(String orderID) {
        if (orderID.equals("20161026010112340000")) {
            ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
            OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
            rooms.add(room1);

            return new OrderVO("20161012010112340000", "01011234", "000000001", "喵喵酒店", "小茗同学", rooms,
                    new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                    null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null);
        } else {
            return null;
        }
    }

    /**
     * 通过订单ID搜索订单
     *
     * @param orderID
     * @return OrderVO
     */
    public ArrayList<OrderRoomVO> searchOrderRoomByOrderID(String orderID) {
        if (orderID.equals("20161026010112340000")) {
            ArrayList<OrderRoomVO> roomVOs = new ArrayList<OrderRoomVO>();
            roomVOs.add(new OrderRoomVO(RoomType.DoubleRoom, 300, 1));
            return roomVOs;
        } else {
            return null;
        }
    }

    /**
     * 通过订单ID搜索评分评价
     *
     * @param orderID
     * @return AssessmentVO
     */
    public AssessmentVO searchAssessmentByOrderID(String orderID) {
        if (orderID.equals("20161026010112340000")) {
            return new AssessmentVO(5, 5, 5, 5, "很舒适");
        } else {
            return null;
        }
    }

    /**
     * 通过订单状态、关键字搜索订单
     *
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchOrder(OrderState os, String keyword) {
        ArrayList<OrderVO> orderVOs = new ArrayList<OrderVO>();

        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);

        OrderVO order1 = new OrderVO("20161012010112340000", "01011234", "000000001", "喵喵酒店", "小茗同学", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null);

        OrderVO order2 = new OrderVO("20161012010112340001", "01011234", "000000001", "喵喵酒店", "小茗同学", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), new TimeUtil(2016, 10, 12, 14, 0, 0), null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Executed, new BillVO(null, null, 300, 280), new AssessmentVO(5, 5, 5, 5, "很好很舒适"));

        OrderVO order3 = new OrderVO("20161012010112340002", "01011234", "000000001", "喵喵酒店", "小茗同学", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Abnormal, new BillVO(null, null, 300, 280), null);

        OrderVO order4 = new OrderVO("20161012010112340003", "01011234", "000000001", "喵喵酒店", "小茗同学", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), new TimeUtil(2016, 10, 11, 14, 0, 0), null, 2, false, OrderState.Cancelled, new BillVO(null, null, 300, 280), null);

        if (os != null) {
            switch (os) {
                case Unexecuted:
                    orderVOs.add(order1);
                    break;
                case Executed:
                    orderVOs.add(order2);
                    break;
                case Abnormal:
                    orderVOs.add(order3);
                    break;
                case Cancelled:
                    orderVOs.add(order4);
                    break;
            }
        } else {
            orderVOs.add(order1);
            orderVOs.add(order2);
            orderVOs.add(order3);
            orderVOs.add(order4);
        }

        return orderVOs;
    }

    /**
     * 通过客户ID、订单状态、关键字搜索订单
     *
     * @param clientID
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchClientOrder(String clientID, OrderState os, String keyword) {
        if (clientID.equals("000000001")) {
            return searchOrder(os, keyword);
        } else {
            return new ArrayList<OrderVO>();
        }
    }

    /**
     * 通过酒店ID、订单状态、关键字搜索订单
     *
     * @param hotelID
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchHotelOrder(String hotelID, OrderState os, String keyword) {
        if (hotelID.equals("01011234")) {
            return searchOrder(os, keyword);
        } else {
            return new ArrayList<OrderVO>();
        }
    }
}
