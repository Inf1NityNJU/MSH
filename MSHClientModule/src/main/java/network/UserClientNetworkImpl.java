//package network;
//
//import bl.userbl.User;
//import blservice.userblservice.LevelService;
//import blservice.userblservice.UserBLInfo;
//import blservice.userblservice.UserBLService;
//import po.ClientPO;
//import po.CreditPO;
//import util.ResultMessage;
//
//import java.net.MalformedURLException;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//
///**
// * Created by Kray on 2016/11/23.
// */
//public class UserClientNetworkImpl implements UserBLService, UserBLInfo, LevelService {
//
//    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) {
//        try {
//            UserServerNetworkImpl userServerNetwork = (UserServerNetworkImpl) Naming.lookup("addClient");
//            return userServerNetwork.addClient(clientPO, creditPO);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return ResultMessage.FAILED;
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            return ResultMessage.FAILED;
//        } catch (NotBoundException e) {
//            e.printStackTrace();
//            return ResultMessage.FAILED;
//        }
//    }
//
//    public ClientPO searchClientByID(String clientID) {
//        try {
//            UserServerNetworkImpl userServerNetwork = (UserServerNetworkImpl) Naming.lookup("searchClientByID");
//            return userServerNetwork.searchClientByID(clientID);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            return null;
//        } catch (NotBoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//}
