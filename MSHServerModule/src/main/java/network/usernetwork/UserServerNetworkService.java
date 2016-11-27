package network.usernetwork;

import dataservice.userdataservice.UserDataService;
import po.ClientPO;
import po.CreditPO;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public interface UserServerNetworkService extends Remote {

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) throws RemoteException;

    public ClientPO searchClientByID(String clientID) throws RemoteException;

    public ArrayList<ClientPO> searchClient(String keyword) throws RemoteException;
}
