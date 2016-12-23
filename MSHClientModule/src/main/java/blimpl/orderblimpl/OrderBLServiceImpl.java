package blimpl.orderblimpl;

import blimpl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLInfo;
import blservice.orderblservice.OrderBLService;
import blservice.userblservice.UserBLInfo;
import util.*;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/10.
 */
public class OrderBLServiceImpl implements OrderBLService, OrderBLInfo {

    private Order order;
    private UserBLInfo userBLInfo_Client;
    private UserBLInfo userBLInfo_Staff;

    protected OrderBLServiceImpl(Order order, UserBLInfo userBLInfo_Client, UserBLInfo userBLInfo_Staff) {
        this.order = order;
        this.userBLInfo_Client = userBLInfo_Client;
        this.userBLInfo_Staff = userBLInfo_Staff;
    }

    @Override
    public ResultMessage checkCredit() {

        String clientID = userBLInfo_Client.getCurrentClientID();
        int credit = userBLInfo_Client.getCreditOfID(clientID);
        return credit > 0 ? ResultMessage.SUFFICIENT : ResultMessage.INSUFFICIENT;
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
    public ResultMessage editOrderAssessment(String orderID, AssessmentVO assessment) {
        return order.editAssessment(orderID, assessment);
    }

    @Override
    public OrderVO searchOrderByID(String orderID) {
        return order.searchOrderByID(orderID);
    }

    @Override
    public ArrayList<OrderVO> searchOrder(OrderState os) {
        return order.searchOrder(os);
    }

    @Override
    public ArrayList<OrderVO> searchClientOrder(OrderState os) {
        String clientID = userBLInfo_Client.getCurrentClientID();

        return  order.searchClientOrder(clientID, os);
    }

    @Override
    public ArrayList<OrderVO> searchHotelOrder(OrderState os) {

        String staffID = userBLInfo_Staff.getCurrentStaffID();
        String hotelID = userBLInfo_Staff.getHotelIDByStaffID(staffID);
        System.out.print(staffID + " " + hotelID);

        return  order.searchHotelOrder(hotelID, os);
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
