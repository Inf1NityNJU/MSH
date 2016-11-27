package network;

import network.usernetwork.UserServerNetworkService;
import po.ClientPO;
import po.CreditPO;
import util.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/23.
 */
public class UserClientNetworkImpl
//        implements UserBLService, UserBLInfo, LevelService
{
    public UserClientNetworkImpl() {

    }

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) {
        try {
            UserServerNetworkService userServerNetworkService = (UserServerNetworkService) Naming.lookup("addClient");
            return userServerNetworkService.addClient(clientPO, creditPO);
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
            UserServerNetworkService userServerNetworkService = (UserServerNetworkService) Naming.lookup("searchClientByID");
            return userServerNetworkService.searchClientByID(clientID);
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

    public ArrayList<ClientPO> searchClient(String keyword) {
        try {
            System.out.println("CLIENT SEARCH CLIENT");
            UserServerNetworkService userServerNetworkService = (UserServerNetworkService) Naming.lookup("searchClient");
            return userServerNetworkService.searchClient(keyword);
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
