package serverservice.userserverservice;

import po.ClientPO;
import po.CreditPO;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Kray on 2016/11/23.
 */
public interface ClientServerService extends Remote {

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) throws RemoteException;

    public ClientPO searchClientByID(String clientID) throws RemoteException;

}
