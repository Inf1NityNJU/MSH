package clienthelper;

import serverservice.TestServerService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Kray on 2016/11/23.
 */
public class ClientHelper {

    public static void main(String[] args) {
        try {
            //查找其他机器的服务
            TestServerService hello = (TestServerService) Naming.lookup("testRMI");
//            HelloInterface hello = (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");
            System.out.println(hello.test());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public ClientHelper() {

    }

}
