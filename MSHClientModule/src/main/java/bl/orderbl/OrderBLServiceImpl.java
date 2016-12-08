package bl.orderbl;

import bl.blfactory.BLFactoryImpl;
import bl.hotelbl.Hotel;
import bl.hotelbl.HotelRoom;
import blservice.orderblservice.OrderBLService;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService_Stub;
import util.*;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/10.
 */
public class OrderBLServiceImpl implements OrderBLService {

    private Order order;

    protected OrderBLServiceImpl(Order order) {
        this.order = order;
    }

    /**
     * 检查客户信用值
     * @return 是否足以生成订单
     */
    public ResultMessage checkCredit() {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo();
        userBLInfo = new UserBLService_Stub();
        String clientID = userBLInfo.getCurrentID();
        int credit = userBLInfo.getCreditOfID(clientID);
        return credit > 0 ? ResultMessage.TRUE : ResultMessage.FAILED;
    }

    @Override
    public ResultMessage startOrder(OrderVO order) {
        return this.order.startOrder(order);
    }


    public int getOrderRoomStock(OrderRoomVO room) {

        return this.order.getOrderRoomStock(room);
    }

    /**
     * 修改入住退房日期
     * @param start
     * @param end
     * @return 是否修改成功
     */
    public ResultMessage modifyDate(DateUtil start, DateUtil end) {
        return null;
    }

    /**
     * 修改订单房间数量
     * @param type
     * @param quantity
     * @return 是否成功修改
     */
    public ResultMessage modifyRoomQuantity(RoomType type, int quantity) {
        return order.modifyRoomQuantity(type, quantity);
    }

    /**
     * 得到账单信息
     * @return BillVO
     */
    public BillVO getBill() {
        return order.getBill();
    }

    /**
     * 生成订单
     * @param latest
     * @param peopleQuantity
     * @param hasChildren
     * @return 是否成功生成
     */
    public ResultMessage generateOrder(TimeUtil latest, int peopleQuantity, boolean hasChildren) {
        return order.generate(latest, peopleQuantity, hasChildren);
    }

    /**
     * 撤销订单
     * @param orderID
     * @return 是否撤销成功
     */
    public ResultMessage revokeOrder(String orderID) {
        return null;
    }

    /**
     * 更新订单入住
     * @param orderID
     * @param time
     * @return 是否更新成功
     */
    public ResultMessage checkInOrder(String orderID, TimeUtil time) {
        return null;
    }

    /**
     * 更新订单退房
     * @param orderID
     * @param time
     * @return 是否更新成功
     */
    public ResultMessage checkOutOrder(String orderID, TimeUtil time) {
        return null;
    }

    /**
     * 编辑订单评分评价
     * @param orderId
     * @param assessment
     * @return 是否更新成功
     */
    public ResultMessage editOrderAssessment(String orderId, AssessmentVO assessment) {
        return null;
    }

    /**
     * 通过订单ID搜索订单
     * @param orderID
     * @return OrderVO
     */
    public OrderVO searchOrderByID(String orderID) {
        return order.searchOrderByID(orderID);
    }

    /**
     * 通过订单状态、关键字搜索订单
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchOrder(OrderState os, String keyword) {
        return searchOrder(os, keyword);
    }

    /**
     * 通过客户ID、订单状态、关键字搜索订单
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchClientOrder(OrderState os, String keyword) {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo();
//        userBLInfo = new UserBLService_Stub();
        String clientID = userBLInfo.getCurrentID();
        clientID = "000101101";
        return  order.searchClientOrder(clientID, os, keyword);
    }

    /**
     * 通过酒店ID、订单状态、关键字搜索订单
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchHotelOrder(OrderState os, String keyword) {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo();
//        userBLInfo = new UserBLService_Stub();
        String staffID = userBLInfo.getCurrentID();
        String hotelID = userBLInfo.getHotelIDByStaffID(staffID);
        return  order.searchHotelOrder(hotelID, os, keyword);
    }

}
