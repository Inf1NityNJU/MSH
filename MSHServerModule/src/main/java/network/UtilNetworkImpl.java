package network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kray on 2016/12/25.
 */
public class UtilNetworkImpl extends UnicastRemoteObject implements UtilNetworkService {

    public UtilNetworkImpl() throws RemoteException {

    }

    public void addCurrentConnectionNum() throws RemoteException {
        System.out.println("ADD");
        ServerHelper.addCurrentConnectionNum();
    }

    public void minusCurrentConnectionNum() throws RemoteException {
        System.out.println("MINUS");
        ServerHelper.minusCurrentConnectionNum();
    }

}
