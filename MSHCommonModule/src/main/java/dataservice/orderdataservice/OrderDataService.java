package dataservice.orderdataservice;

import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.OrderState;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/13.
 */
public interface OrderDataService {

    /**
     * 增加订单
     * @param opo
     * @return
     */
    public ResultMessage addOrder(OrderPO opo);

    /**
     * 更新订单
     * @param opo
     * @return
     */
    public ResultMessage updateOrder(OrderPO opo);

    /**
     * 增加订单房间
     * @param orpo
     * @return
     */
    public ResultMessage addOrderRoom(OrderRoomPO orpo);

    /**
     * 根据订单ID搜索订单
     * @param orderID
     * @return
     */
    public OrderPO searchOrderByOrderID(String orderID);

    /**
     * 根据订单状态搜索订单
     * @param orderState
     * @return
     */
    public ArrayList<OrderPO> searchOrder(OrderState orderState);

    /**
     * 根据订单状态、客户ID搜索订单
     * @param clientID
     * @return
     */
    public ArrayList<OrderPO> searchOrderByClientID(String clientID, OrderState orderState);

    /**
     * 根据订单状态、酒店ID搜索订单
     * @param hotelID
     * @return
     */
    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID, OrderState orderState);

    /**
     * 根据订单ID搜索订单房间
     * @param orderID
     * @return
     */
    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID);

    /**
     * 增加评价
     * @param assessment
     * @return
     */
    public ResultMessage addAssessment(AssessmentPO assessment);

    /**
     * 编辑评价
     * @param assessment
     * @return
     */
    public ResultMessage updateAssessment(AssessmentPO assessment);

    /**
     * 根据订单ID搜索评价
     * @param orderID
     * @return
     */
    public AssessmentPO searchAssessmentByOrderID(String orderID);

    /**
     * 搜索订单数量
     */
    public int searchOrderQuantity(String orderIDPrefix);

}
