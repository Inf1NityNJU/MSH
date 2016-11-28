package network;

import dataservice.orderdataservice.OrderDataService;
import network.ordernetwork.OrderServerNetworkService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kray on 2016/11/27.
 */
public class OrderServerNetworkImpl extends UnicastRemoteObject implements OrderServerNetworkService {
    public OrderServerNetworkImpl() throws RemoteException {
    }
}