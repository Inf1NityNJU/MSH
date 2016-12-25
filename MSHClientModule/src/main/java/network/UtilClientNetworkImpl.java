package network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Kray on 2016/12/25.
 */
public class UtilClientNetworkImpl {

    /**
     * 再次尝试连接的时间间隔
     */
    private static final int SLEEP_TIME = 2000;

    private UtilNetworkService utilNetworkService;

    public UtilClientNetworkImpl() {
        while (utilNetworkService == null) {
            try {
                utilNetworkService = (UtilNetworkService) Naming.lookup("UtilNetworkService");
            } catch (NotBoundException e) {
                sleep();
                System.err.println("UtilNetworkService: Not bound, trying to connect");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                break;
            } catch (RemoteException e) {
                sleep();
                System.err.println("UtilNetworkService: No service, trying to connect");
            }
        }

        try {
            utilNetworkService.addCurrentConnectionNum();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程睡眠一段时间
     */
    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

}
