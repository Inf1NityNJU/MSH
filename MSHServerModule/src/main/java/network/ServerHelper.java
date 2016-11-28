package network;

import network.usernetwork.UserServerNetworkService;
import network.usernetwork.UserServerNetworkImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Kray on 2016/11/23.
 */
public class ServerHelper {

    public static void main(String[] args) {

        try {
            //启动RMI注册服务，指定端口为1099　（1099为默认端口）
            //也可以通过命令 ＄java_home/bin/rmiregistry 1099启动
            //这里用这种方式避免了再打开一个DOS窗口
            //而且用命令rmiregistry启动注册服务还必须事先用RMIC生成一个stub类为它所用
            LocateRegistry.createRegistry(1099);
            //创建远程对象的一个或多个实例，下面是hello对象
            //可以用不同名字注册不同的实例
            System.out.println("RMI server is ready!");

            //把clientServerService注册到RMI注册服务器上，命名为testRMI
            UserServerNetworkService userServerNetwork = new UserServerNetworkImpl();







            Naming.rebind("addClient", userServerNetwork);
            Naming.rebind("searchClientByID", userServerNetwork);
//            Naming.rebind("updateClient", userServerNetwork);
//            Naming.rebind("deleteClient", userServerNetwork);
            Naming.rebind("searchClient", userServerNetwork);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public ServerHelper() {

    }

}
