package serverimpl;

import serverservice.TestServerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kray on 2016/11/23.
 */
public class TestServerImpl extends UnicastRemoteObject implements TestServerService {

    private String string;

    public TestServerImpl(String string) throws RemoteException {
        this.string = string;
    }

    public String test() throws RemoteException {
        System.out.println(string);
        return string;
    }

}
