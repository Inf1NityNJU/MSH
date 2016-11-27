package network;

import blservice.userblservice.UserBLService;
import po.ClientPO;
import po.CreditPO;
import util.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Kray on 2016/11/23.
 */
public class UserClientHelper {

    public UserClientHelper() {

    }

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) {
        try {
            ClientServerService clientServerService = (ClientServerService) Naming.lookup("addClient");
            return clientServerService.addClient(clientPO, creditPO);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ClientPO searchClientByID(String clientID) {
        try {
            ClientServerService clientServerService = (ClientServerService) Naming.lookup("searchClientByID");
            return clientServerService.searchClientByID(clientID);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
