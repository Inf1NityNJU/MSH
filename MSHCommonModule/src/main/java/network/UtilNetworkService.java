package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Kray on 2016/12/25.
 */
public interface UtilNetworkService extends Remote {

    public void addCurrentConnectionNum() throws RemoteException;

    public void minusCurrentConnectionNum() throws RemoteException;
}
