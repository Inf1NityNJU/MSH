package bl.orderbl;

import util.*;
import vo.AssessmentVO;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/30.
 */
public class Order {

    private OrderVO order;

    private ArrayList<OrderRoom> rooms;
    private Bill bill;

    /**
     * 修改订单房间数量
     * @param type
     * @param quantity
     * @return 是否成功修改
     */
    public ResultMessage modifyRoomQuantity(RoomType type, int quantity){

        OrderRoom room = null;

        for (OrderRoom roomItr : rooms) {
            if (roomItr.getType() == type) {
                room = roomItr;
                break;
            }
        }

        if (room == null) {
//            room = new OrderRoom(type, quantity);
        }
//        OrderRoom room = new OrderRoom();
        return ResultMessage.SUCCESS;
    }

    /**
     * 得到账单
     * @param date
     * @param start
     * @param end
     * @param clientID
     * @param hotelID
     * @param quantity
     * @return BillVO
     */
    public BillVO getBill(String hotelID, String clientID, DateUtil date, DateUtil start, DateUtil end, int quantity){
        return null;
    }

    /**
     * 生成订单
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
     * @param orderID
     * @return 是否成功撤销
     */
    public ResultMessage revoke(String orderID) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 更新入住
     * @param orderID
     * @param time
     * @return 是否成功
     */
    public ResultMessage checkIn(String orderID, TimeUtil time) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 更新退房
     * @param orderID
     * @param time
     * @return 是否成功
     */
    public ResultMessage checkOut(String orderID, TimeUtil time) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 编辑评分评价
     * @param orderID
     * @param assessment
     * @return 是否成功
     */
    public ResultMessage editAssessment(String orderID, AssessmentVO assessment) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 通过订单ID搜索订单
     * @param orderID
     * @return OrderVO
     */
    public OrderVO searchOrderByID(String orderID) {
        return null;
    }

    /**
     * 通过订单状态、关键字搜索订单
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchOrder(OrderState os, String keyword) {
        return null;
    }

    /**
     * 通过客户ID、订单状态、关键字搜索订单
     * @param clientID
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchClientOrder(String clientID, OrderState os, String keyword) {
        return null;
    }

    /**
     * 通过酒店ID、订单状态、关键字搜索订单
     * @param hotelID
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchHotelOrder(String hotelID, OrderState os, String keyword) {
        return null;
    }
}
