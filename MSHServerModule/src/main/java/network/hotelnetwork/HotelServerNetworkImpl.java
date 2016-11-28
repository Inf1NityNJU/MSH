package network;

import dataservice.hoteldataservice.HotelDataService;
import network.hotelnetwork.HotelServerNetworkService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kray on 2016/11/27.
 */
public class HotelServerNetworkImpl extends UnicastRemoteObject implements HotelServerNetworkService {
    public HotelServerNetworkImpl() throws RemoteException {
    }
}
