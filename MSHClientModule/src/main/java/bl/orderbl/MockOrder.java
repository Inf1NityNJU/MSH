package bl.orderbl;

import util.*;
import vo.AssessmentVO;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockOrder extends Order {

    public ResultMessage modifyRoomQuantity(RoomType roomtype, int quantity){
        if (roomtype == RoomType.SingleRoom) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    public Bill getBill(DateUtil date, DateUtil start, DateUtil end, DateUtil birthday, String hotelID, int quantity){
        return new MockBill(300, 300);
    }

    public ResultMessage generate(TimeUtil latest, int peopleQuantity, boolean hasChildren) {
        return ResultMessage.SUCCESS;
    }

    public ResultMessage revoke(String orderID) {
        if (orderID.equals("20161026010112340000")) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    public ResultMessage checkIn(String orderID, TimeUtil time) {
        if (orderID.equals("20161026010112340000")) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    public ResultMessage checkOut(String orderID, TimeUtil time) {
        if (orderID.equals("20161026010112340000")) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    public ResultMessage editAssessment(String orderID, int score, String comment) {
        if (orderID.equals("20161026010112340000")) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    public OrderVO searchOrderByID(String orderID) {
        if (orderID.equals("20161026010112340000")) {
            ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
            OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
            rooms.add(room1);

            return new OrderVO("20161012010112340000", "01011234", "000000001", rooms,
                    new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                    null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null);
        } else {
            return null;
        }
    }

    public ArrayList<OrderRoomVO> searchOrderRoomByOrderID(String orderID) {
        if (orderID.equals("20161026010112340000")) {
            ArrayList<OrderRoomVO> roomVOs = new ArrayList<OrderRoomVO>();
            roomVOs.add(new OrderRoomVO(RoomType.DoubleRoom, 300, 1));
            return roomVOs;
        } else {
            return null;
        }
    }


    public AssessmentVO searchAssessmentByOrderID(String orderID) {
        if (orderID.equals("20161026010112340000")) {
            return new AssessmentVO(5, 5, 5, 5, "很舒适");
        } else {
            return null;
        }
    }

    public ArrayList<OrderVO> searchOrder(OrderState os, String keyword) {
        ArrayList<OrderVO> orderVOs = new ArrayList<OrderVO>();

        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);

        OrderVO order1 = new OrderVO("20161012010112340000", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, new BillVO(null, null, 300, 280), null);

        OrderVO order2 = new OrderVO("20161012010112340001", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), new TimeUtil(2016, 10, 12, 14, 0, 0), null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Executed, new BillVO(null, null, 300, 280), new AssessmentVO(5,5,5,5, "很好很舒适"));

        OrderVO order3 = new OrderVO("20161012010112340002", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Abnormal, new BillVO(null, null, 300, 280), null);

        OrderVO order4 = new OrderVO("20161012010112340003", "01011234", "000000001", rooms,
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), new TimeUtil(2016, 10, 11, 14, 0, 0), null, 2, false, OrderState.Cancelled, new BillVO(null, null, 300, 280), null);

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
            default:
                orderVOs.add(order1);
                orderVOs.add(order2);
                orderVOs.add(order3);
                orderVOs.add(order4);
        }
        return orderVOs;
    }

    public ArrayList<OrderVO> searchClientOrder(String clientID, OrderState os, String keyword) {
        if (clientID.equals("000000001")) {
            return searchOrder(os, keyword);
        } else {
            return new ArrayList<OrderVO>();
        }
    }

    public ArrayList<OrderVO> searchHotelOrder(String hotelID, OrderState os, String keyword) {
        if (hotelID.equals("01011234")) {
            return searchOrder(os, keyword);
        } else {
            return new ArrayList<OrderVO>();
        }
    }
}
