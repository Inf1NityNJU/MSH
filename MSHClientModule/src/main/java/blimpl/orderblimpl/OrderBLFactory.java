package blimpl.orderblimpl;

import blimpl.blfactory.BLFactoryImpl;
import blservice.blfactoryservice.BLFactoryService;
import blservice.hotelblservice.HotelBLInfo;
import blservice.orderblservice.OrderBLInfo;
import blservice.orderblservice.OrderBLService;
import blservice.userblservice.UserBLInfo;
import network.OrderClientNetworkImpl;
import network.OrderClientNetworkService;

/**
 * Created by Sorumi on 16/12/3.
 */
public class OrderBLFactory {

    //
    private static OrderBLServiceImpl orderBLServiceImpl;

    public synchronized static OrderBLServiceImpl getOrderBLServiceImpl() {
        if (orderBLServiceImpl == null) {
            orderBLServiceImpl = new OrderBLServiceImpl(getOrder(), getUserBLInfo_Client(), getUserBLInfo_Staff());
        }
        return orderBLServiceImpl;
    }

    public synchronized static OrderBLInfo getOrderBLInfo() {
        if (orderBLServiceImpl == null) {

            orderBLServiceImpl = new OrderBLServiceImpl(getOrder(), getUserBLInfo_Client(), getUserBLInfo_Staff());
        }
        return orderBLServiceImpl;
    }


    public synchronized static Order getOrder() {
        return new Order(getUserBLInfo_Client(), getHotelBLInfo(), getOrderClientNetworkService());
    }


    // For Test
    public synchronized static OrderBLService getOrderBLServiceImplForTest() {
        BLFactoryService blFactory = new BLFactoryImpl();
        return new OrderBLServiceImpl(getOrderForTest(), blFactory.getUserBLInfo_Stub(), blFactory.getUserBLInfo_Stub());
    }

    public synchronized static Order getOrderForTest() {
        BLFactoryService blFactory = new BLFactoryImpl();
        return new Order(blFactory.getUserBLInfo_Stub(), blFactory.getHotelBLInfo_Stub(), getOrderClientNetworkService());
    }

    // private

    private synchronized static HotelBLInfo getHotelBLInfo() {
        BLFactoryService blFactory = new BLFactoryImpl();
        return blFactory.getHotelBLInfo();
    }

    private synchronized static UserBLInfo getUserBLInfo_Client() {
        BLFactoryService blFactory = new BLFactoryImpl();
        return blFactory.getUserBLInfo_Client();
    }

    private synchronized static UserBLInfo getUserBLInfo_Staff() {
        BLFactoryService blFactory = new BLFactoryImpl();
        return blFactory.getUserBLInfo_Staff();
    }

    private synchronized static OrderClientNetworkService getOrderClientNetworkService() {
        return new OrderClientNetworkImpl();
    }


}
