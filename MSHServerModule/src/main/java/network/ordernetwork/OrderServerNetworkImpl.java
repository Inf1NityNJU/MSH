package network.ordernetwork;

import dataimpl.orderdataimpl.OrderDataServiceFactory;
import dataservice.orderdataservice.OrderDataService;
import network.ordernetworkservice.OrderServerNetworkService;
import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.OrderState;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public class OrderServerNetworkImpl extends UnicastRemoteObject implements OrderServerNetworkService {

    private OrderDataService orderDataService;

    public OrderServerNetworkImpl() throws RemoteException {
        orderDataService = OrderDataServiceFactory.getOrderDataService();
    }


    @Override
    public ResultMessage addOrder(OrderPO opo) throws RemoteException {
        return orderDataService.addOrder(opo);
    }

    @Override
    public ResultMessage updateOrder(OrderPO opo) throws RemoteException {
        return orderDataService.updateOrder(opo);
    }

    @Override
    public ResultMessage addOrderRoom(OrderRoomPO orpo) throws RemoteException {
        return orderDataService.addOrderRoom(orpo);
    }

    @Override
    public OrderPO searchOrderByOrderID(String orderID) throws RemoteException {
        return orderDataService.searchOrderByOrderID(orderID);
    }

    @Override
    public ArrayList<OrderPO> searchOrder(OrderState orderState) throws RemoteException {
        return orderDataService.searchOrder(orderState);
    }

    @Override
    public ArrayList<OrderPO> searchOrderByClientID(String clientID, OrderState orderState) throws RemoteException {
        return orderDataService.searchOrderByClientID(clientID, orderState);
    }

    @Override
    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID, OrderState orderState) throws RemoteException {
        return orderDataService.searchOrderByHotelID(hotelID, orderState);
    }

    @Override
    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID) throws RemoteException {
        return orderDataService.searchOrderRoomByOrderID(orderID);
    }

    @Override
    public ResultMessage addAssessment(AssessmentPO assessment) throws RemoteException {
        return orderDataService.addAssessment(assessment);
    }

    @Override
    public ResultMessage updateAssessment(AssessmentPO assessment) throws RemoteException {
        return orderDataService.updateAssessment(assessment);
    }

    @Override
    public AssessmentPO searchAssessmentByOrderID(String orderID) throws RemoteException {
        return orderDataService.searchAssessmentByOrderID(orderID);
    }

    @Override
    public int searchOrderQuantity(String orderIDPrefix) throws RemoteException {
        return orderDataService.searchOrderQuantity(orderIDPrefix);
    }
}