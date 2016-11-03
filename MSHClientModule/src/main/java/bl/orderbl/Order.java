package bl.orderbl;

import util.*;
import vo.AssessmentVO;
import vo.OrderRoomVO;
import vo.OrderVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/30.
 */
public class Order {

    public ResultMessage modifyRoomQuantity(RoomType roomtype, int quantity){
        return ResultMessage.SUCCESS;
    }

    public Bill getBill(DateUtil date, DateUtil start, DateUtil end, DateUtil birthday, String hotelID, int quantity){
        return null;
    }

    public ResultMessage generate(TimeUtil latest, int peopleQuantity, boolean hasChildren) {
        return ResultMessage.SUCCESS;
    }

    public ResultMessage revoke(String orderID) {
        return ResultMessage.SUCCESS;
    }

    public ResultMessage checkIn(String orderID, TimeUtil time) {
        return ResultMessage.SUCCESS;
    }

    public ResultMessage checkOut(String orderID, TimeUtil time) {
        return ResultMessage.SUCCESS;
    }

    public ResultMessage editAssessment(String orderId, int score, String comment) {
        return ResultMessage.SUCCESS;
    }

    public OrderVO searchOrderByID(String orderID) {
        return null;
    }

    public ArrayList<OrderRoomVO> searchOrderRoomByOrderID(String orderID) {
        return null;
    }


    public AssessmentVO searchAssessmentByOrderID(String orderID) {
        return null;
    }

    public ArrayList<OrderVO> searchOrder(OrderState os, String keyword) {
        return null;
    }

    public ArrayList<OrderVO> searchClientOrder(String clientID, OrderState os, String keyword) {
        return null;
    }

    public ArrayList<OrderVO> searchHotelOrder(String hotelID, OrderState os, String keyword) {
        return null;
    }
}
