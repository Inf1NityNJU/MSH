package network.ordernetworkservice;

import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.OrderState;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public interface OrderServerNetworkService extends Remote{

    /**
     * 增加订单
     * @param opo
     * @return
     */
    public ResultMessage addOrder(OrderPO opo) throws RemoteException;

    /**
     * 更新订单
     * @param opo
     * @return
     */
    public ResultMessage updateOrder(OrderPO opo) throws RemoteException;

    /**
     * 增加订单房间
     * @param orpo
     * @return
     */
    public ResultMessage addOrderRoom(OrderRoomPO orpo) throws RemoteException;

    /**
     * 根据订单ID搜索订单
     * @param orderID
     * @return
     */
    public OrderPO searchOrderByOrderID(String orderID) throws RemoteException;

    /**
     * 根据订单状态搜索订单
     * @param orderState
     * @return
     */
    public ArrayList<OrderPO> searchOrder(OrderState orderState) throws RemoteException;

    /**
     * 根据订单状态、客户ID搜索订单
     * @param clientID
     * @return
     */
    public ArrayList<OrderPO> searchOrderByClientID(String clientID, OrderState orderState) throws RemoteException;

    /**
     * 根据订单状态、酒店ID搜索订单
     * @param hotelID
     * @return
     */
    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID, OrderState orderState) throws RemoteException;

    /**
     * 根据订单ID搜索订单房间
     * @param orderID
     * @return
     */
    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID) throws RemoteException;

    /**
     * 增加评价
     * @param assessment
     * @return
     */
    public ResultMessage addAssessment(AssessmentPO assessment) throws RemoteException;

    /**
     * 编辑评价
     * @param assessment
     * @return
     */
    public ResultMessage updateAssessment(AssessmentPO assessment) throws RemoteException;

    /**
     * 根据订单ID搜索评价
     * @param orderID
     * @return
     */
    public AssessmentPO searchAssessmentByOrderID(String orderID) throws RemoteException;

    /**
     * 搜索订单数量
     */
    public int searchOrderQuantity(String orderIDPrefix) throws RemoteException;

}
