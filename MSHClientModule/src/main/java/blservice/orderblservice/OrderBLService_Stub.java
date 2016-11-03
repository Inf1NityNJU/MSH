package blservice.orderblservice;

import util.*;
import vo.AssessmentVO;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/11.
 */
public class OrderBLService_Stub implements OrderBLService {

    public BillVO price(String hotelID, String clientID, DateUtil checkInDate, DateUtil checkOutDate, ArrayList<OrderRoomVO> rvo) {
        return new BillVO(null, null, 300, 300);
    }

    public ResultMessage generate(String hotelID, String clientID, DateUtil checkInDate, DateUtil checkOutDate, ArrayList<OrderRoomVO> rvo,
                                  TimeUtil latestExecuteTime, int peopleQuantity, boolean hasChildren) {
        if (hotelID.equals("01011234"))
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILED;
    }

    public ArrayList<OrderVO> orders(OrderState os) {
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

    public ArrayList<OrderVO> clientOrders(String clientID, OrderState os) {
        if (clientID.equals("000000001"))
            return orders(os);
        else
            return new ArrayList<OrderVO>();
    }

    public ArrayList<OrderVO> hotelOrders(String hotelID, OrderState os) {
        if (hotelID.equals("01011234"))
            return orders(os);
        else
            return new ArrayList<OrderVO>();
    }

    public OrderVO getOrder(String orderID) {

        if (orderID.equals("20161012010112340000")) {
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

    public ResultMessage revokeOrder(String orderID) {
        if (orderID.equals("20161012010112340000")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.NOT_EXIST;
        }
    }

    public ResultMessage addCheckInTime(TimeUtil checkInTime, String orderID) {
        if (orderID.equals("20161012010112340000")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.NOT_EXIST;
        }
    }

    public ResultMessage addCheckOutTime(TimeUtil checkOutTime, String orderID) {
        if (orderID.equals("20161012010112340000")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.NOT_EXIST;
        }
    }

    public ResultMessage editAssessment(String orderID, AssessmentVO assessment) {
        if (orderID.equals("20161012010112340000"))
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.NOT_EXIST;

    }

    public ArrayList<AssessmentVO> hotelAssessment(String hotelID) {
        if (hotelID.equals("01011234")) {
            ArrayList<AssessmentVO> assessmentVOs = new ArrayList<AssessmentVO>();
            AssessmentVO assessment1 = new AssessmentVO(5,5,5,5, "很好很舒适，下次再来");
            AssessmentVO assessment2 = new AssessmentVO(4,5,4,5, "");
            assessmentVOs.add(assessment1);
            assessmentVOs.add(assessment2);

            return assessmentVOs;
        } else {
            return new ArrayList<AssessmentVO>();
        }
    }
}