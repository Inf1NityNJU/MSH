package bl.orderbl;

import blservice.orderblservice.OrderBLService;
import util.*;
import vo.AssessmentVO;
import vo.BillVO;
import vo.OrderVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/10.
 */
public class OrderBLServiceImpl implements OrderBLService {

    /**
     * 检查客户信用值
     * @return 是否足以生成订单
     */
    public ResultMessage checkCredit() {
        return null;
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
        return null;
    }

    /**
     * 得到账单信息
     * @param hotelID
     * @param date
     * @param start
     * @param end
     * @param birthday
     * @param quantity
     * @return BillVO
     */
    public BillVO getBill(String hotelID, DateUtil date, DateUtil start, DateUtil end, DateUtil birthday, int quantity) {
        return null;
    }

    /**
     * 生成订单
     * @param latest
     * @param peopleQuantity
     * @param hasChildren
     * @return 是否成功生成
     */
    public ResultMessage generateOrder(String hotelID, TimeUtil latest, int peopleQuantity, boolean hasChildren) {
        return null;
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
