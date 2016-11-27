package network;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import po.ClientPO;
import po.CreditPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kray on 2016/11/23.
 */
public class ClientServerImpl extends UnicastRemoteObject implements ClientServerService {

    public ClientServerImpl() throws RemoteException {

    }

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) throws RemoteException {
        UserDataService userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.addClient(clientPO, creditPO);
    }

    public ClientPO searchClientByID(String clientID) throws RemoteException {
        UserDataService userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.searchClientByID(clientID);
    }
}
