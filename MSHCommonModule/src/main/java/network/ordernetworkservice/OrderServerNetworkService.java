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

    public ResultMessage addOrder(OrderPO opo) throws RemoteException;

    public ResultMessage updateOrder(OrderPO opo) throws RemoteException;

    public ResultMessage addOrderRoom(OrderRoomPO orpo) throws RemoteException;

    public OrderPO searchOrderByOrderID(String orderID) throws RemoteException;

    public ArrayList<OrderPO> searchOrder(OrderState orderState) throws RemoteException;

    public ArrayList<OrderPO> searchOrderByClientID(String clientID, OrderState orderState) throws RemoteException;

    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID, OrderState orderState) throws RemoteException;

    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID) throws RemoteException;

    public ResultMessage addAssessment(AssessmentPO assessment) throws RemoteException;

    public ResultMessage updateAssessment(AssessmentPO assessment) throws RemoteException;

    public AssessmentPO searchAssessmentByOrderID(String orderID) throws RemoteException;

    public int searchOrderQuantity(String orderIDPrefix) throws RemoteException;

}
