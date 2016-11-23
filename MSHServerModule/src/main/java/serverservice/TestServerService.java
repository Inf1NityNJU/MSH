package serverservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Kray on 2016/11/23.
 */
public interface TestServerService extends Remote {

    public String test() throws RemoteException;

}
