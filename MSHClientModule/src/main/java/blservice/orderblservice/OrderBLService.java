package blservice.orderblservice;

import util.*;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/12.
 */
public interface OrderBLService {

    /**
     * 检查客户信用值
     * @return 是否足以生成订单
     */
    public ResultMessage checkCredit();

    /**
     * 开启新的生成订单回合
     * @param order
     * @return
     */
    public ResultMessage startOrder(OrderVO order);

    /**
     * 获得本次订单的某房间的库存
     * @param roomVO
     * @return
     */
    public int getOrderRoomStock(OrderRoomVO roomVO);

    /**
     * 修改订单房间数量
     * @param type
     * @param quantity
     * @return 是否成功修改
     */
    public ResultMessage modifyRoomQuantity(RoomType type, int quantity);

    /**
     * 得到账单信息
     * @return BillVO
     */
    public BillVO getBill();

    /**
     * 生成订单
     * @param latest
     * @param peopleQuantity
     * @param hasChildren
     * @return 是否成功生成
     */
    public ResultMessage generateOrder(TimeUtil latest, int peopleQuantity, boolean hasChildren);

    /**
     * 撤销订单
     * @param orderID
     * @return 是否撤销成功
     */
    public ResultMessage revokeOrder(String orderID);

    /**
     * 撤销异常订单
     * @param orderID
     * @param credit
     * @return
     */
    public ResultMessage revokeAbnormalOrder(String orderID, int credit);

    /**
     * 更新订单入住
     * @param orderID
     * @param time
     * @return 是否更新成功
     */
    public ResultMessage checkInOrder(String orderID, TimeUtil time);

    /**
     * 更新订单退房
     * @param orderID
     * @param time
     * @return 是否更新成功
     */
    public ResultMessage checkOutOrder(String orderID, TimeUtil time);

    /**
     * 编辑订单评分评价
     * @param orderId
     * @param assessment
     * @return 是否更新成功
     */
    public ResultMessage editOrderAssessment(String orderId, AssessmentVO assessment);

    /**
     * 通过订单ID搜索订单
     * @param orderID
     * @return OrderVO
     */
    public OrderVO searchOrderByID(String orderID);

    /**
     * 通过订单状态搜索订单
     * @param os
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchOrder(OrderState os);

    /**
     * 通过订单状态搜索当前登录客户订单
     * @param os
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchClientOrder(OrderState os);

    /**
     * 通过订单状态搜索当前登录酒店管理人员所属酒店订单
     * @param os
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchHotelOrder(OrderState os);

}
