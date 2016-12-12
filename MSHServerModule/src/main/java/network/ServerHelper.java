package network;

import network.hotelnetwork.HotelServerNetworkImpl;
import network.hotelnetworkservice.HotelServerNetworkService;
import network.ordernetwork.OrderServerNetworkImpl;
import network.ordernetworkservice.OrderServerNetworkService;
import network.promotionnetwork.PromotionServerNetworkImpl;
import network.promotionnetworkservice.PromotionServerNetworkService;
import network.usernetworkservice.UserServerNetworkService;
import network.usernetwork.UserServerNetworkImpl;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kray on 2016/11/23.
 */
public class ServerHelper {

    private static ServerHelper serverHelper;
    private static Registry registry;

    private ServerHelper(int port) {
        buildNetwork(port);
    }

    public static ServerHelper getServerHelper(int port) {
        if (serverHelper != null) {
            return serverHelper;
        } else {
            return new ServerHelper(port);
        }
    }

    public static ServerHelper buildNetwork(int port) {

        try {
            //启动RMI注册服务，指定端口为1099　（1099为默认端口）
            //也可以通过命令 ＄java_home/bin/rmiregistry 1099启动
            //这里用这种方式避免了再打开一个DOS窗口
            //而且用命令rmiregistry启动注册服务还必须事先用RMIC生成一个stub类为它所用
            registry = LocateRegistry.createRegistry(port);
            //创建远程对象的一个或多个实例，下面是hello对象
            //可以用不同名字注册不同的实例
            System.out.println("RMI server is ready!");

            //把clientServerService注册到RMI注册服务器上，命名为testRMI
            UserServerNetworkService userServerNetwork = new UserServerNetworkImpl();
            HotelServerNetworkService hotelServerNetworkService = new HotelServerNetworkImpl();
            PromotionServerNetworkService promotionServerNetworkService = new PromotionServerNetworkImpl();
            OrderServerNetworkService orderServerNetworkService = new OrderServerNetworkImpl();


            Naming.rebind("HotelServerNetworkService", hotelServerNetworkService);
            Naming.rebind("UserServerNetworkService", userServerNetwork);
            Naming.rebind("PromotionServerNetWorkService", promotionServerNetworkService);
            Naming.rebind("OrderServerNetworkService", orderServerNetworkService);

            return serverHelper;
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Connect Failed");
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Connect Failed");
            return null;
        }
    }

    public static void disableNetwork() {
        try {
            UnicastRemoteObject.unexportObject(registry, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

}
