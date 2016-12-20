package bl.orderbl;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.Hotel;
import bl.hotelbl.HotelRoom;
import blservice.orderblservice.OrderBLInfo;
import blservice.orderblservice.OrderBLService;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService_Stub;
import util.*;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/10.
 */
public class OrderBLServiceImpl implements OrderBLService, OrderBLInfo {

    private Order order;

    protected OrderBLServiceImpl(Order order) {
        this.order = order;
    }

    @Override
    public ResultMessage checkCredit() {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();
        String clientID = userBLInfo.getCurrentClientID();
        int credit = userBLInfo.getCreditOfID(clientID);
        return credit > 0 ? ResultMessage.TRUE : ResultMessage.FAILED;
    }

    @Override
    public ResultMessage startOrder(OrderVO order) {
        return this.order.startOrder(order);
    }

    @Override
    public int getOrderRoomStock(OrderRoomVO room) {

        return this.order.getOrderRoomStock(room);
    }

    @Override
    public ResultMessage modifyDate(DateUtil start, DateUtil end) {
        return null;
    }

    @Override
    public ResultMessage modifyRoomQuantity(RoomType type, int quantity) {
        return order.modifyRoomQuantity(type, quantity);
    }

    @Override
    public BillVO getBill() {
        return order.getBill();
    }

    @Override
    public ResultMessage generateOrder(TimeUtil latest, int peopleQuantity, boolean hasChildren) {
        return order.generate(latest, peopleQuantity, hasChildren);
    }

    @Override
    public ResultMessage revokeOrder(String orderID) {
        return order.revoke(orderID);
    }

    @Override
    public ResultMessage revokeAbnormalOrder(String orderID, int credit) {
        return order.revokeAbnormal(orderID, credit);
    }

    @Override
    public ResultMessage checkInOrder(String orderID, TimeUtil time) {
        return order.checkIn(orderID, time);
    }

    @Override
    public ResultMessage checkOutOrder(String orderID, TimeUtil time) {
        return order.checkOut(orderID, time);
    }

    @Override
    public AssessmentVO getOrderAssessment(String orderID) {
        return order.getOrderAssessment(orderID);
    }

    @Override
    public ResultMessage editOrderAssessment(String orderID, AssessmentVO assessment) {
        return order.editAssessment(orderID, assessment);
    }

    @Override
    public OrderVO searchOrderByID(String orderID) {
        return order.searchOrderByID(orderID);
    }

    @Override
    public ArrayList<OrderVO> searchOrder(OrderState os, String keyword) {
        return order.searchOrder(os, keyword);
    }

    @Override
    public ArrayList<OrderVO> searchClientOrder(OrderState os, String keyword) {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();
        String clientID = userBLInfo.getCurrentClientID();

        return  order.searchClientOrder(clientID, os, keyword);
    }

    @Override
    public ArrayList<OrderVO> searchHotelOrder(OrderState os, String keyword) {
        UserBLInfo userBLInfo_staff = new BLFactoryImpl().getUserBLInfo_Staff();

        String staffID = userBLInfo_staff.getCurrentStaffID();
        String hotelID = userBLInfo_staff.getHotelIDByStaffID(staffID);
        System.out.print(staffID + " " + hotelID);

        return  order.searchHotelOrder(hotelID, os, keyword);
    }

    @Override
    public ArrayList<String> getBookedHotelIDByClientID(String clientID) {
        return order.getBookedHotelIDByClientID(clientID);
    }

    @Override
    public boolean isBookedHotelByClient(String hotelID, String clientID) {
        return order.isBookedHotelByClient(hotelID, clientID);
    }

    @Override
    public ArrayList<Assessment_HotelVO> getAssessmentByHotelID(String hotelID) {
        return order.getAssessmentByHotelID(hotelID);
    }
}
