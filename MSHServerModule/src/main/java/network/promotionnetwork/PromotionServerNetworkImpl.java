package network;

import dataservice.promotiondataservice.PromotionDataService;
import network.promotionnetwork.PromotionServerNetworkService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kray on 2016/11/27.
 */
public class PromotionServerNetworkImpl extends UnicastRemoteObject implements PromotionServerNetworkService {
    public PromotionServerNetworkImpl() throws RemoteException {
    }
}
