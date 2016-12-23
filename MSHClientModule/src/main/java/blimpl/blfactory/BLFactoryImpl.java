package blimpl.blfactory;

import blimpl.hotelblimpl.HotelBLFactory;
import blimpl.orderblimpl.OrderBLFactory;
import blimpl.promotionblimpl.PromotionBLFactory;
import blimpl.userblimpl.UserBLFactory;
import blservice.blfactoryservice.BLFactoryService;
import blservice.hotelblservice.HotelBLInfo;
import blservice.hotelblservice.HotelBLService;
import blservice.hotelblservice.HotelBLService_Stub;
import blservice.orderblservice.OrderBLInfo;
import blservice.orderblservice.OrderBLService;
import blservice.orderblservice.OrderBLService_Stub;
import blservice.promotionblservice.PromotionBLInfo;
import blservice.promotionblservice.PromotionBLService;
import blservice.promotionblservice.PromotionBLService_Stub;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import blservice.userblservice.UserBLService_Stub;

/**
 * Created by Sorumi on 16/12/3.
 */
public class BLFactoryImpl implements BLFactoryService {

    @Override
    public HotelBLService getHotelBLService() {
        return HotelBLFactory.getHotelBLService();
    }

    @Override
    public OrderBLService getOrderBLService() {
        return OrderBLFactory.getOrderBLServiceImpl();
    }

    @Override
    public PromotionBLService getPromotionBLService() {
        return PromotionBLFactory.getPromotionBLService();
    }

    @Override
    public HotelBLInfo getHotelBLInfo() {
        return HotelBLFactory.getHotelBLInfo();
    }

    @Override
    public OrderBLInfo getOrderBLInfo() {
        return OrderBLFactory.getOrderBLInfo();
    }

    @Override
    public PromotionBLInfo getPromotionBLInfo() {
        return PromotionBLFactory.getPromotionBLService();
    }

    @Override
    public UserBLService getClientBLService() {
        return UserBLFactory.getUserBLServiceImpl_Client();
    }

    @Override
    public UserBLService getStaffBLService() {
        return UserBLFactory.getUserBLServiceImpl_Staff();
    }

    @Override
    public UserBLService getSalesmanBLService() {
        return UserBLFactory.getUserBLServiceImpl_Salesman();
    }

    @Override
    public UserBLInfo getUserBLInfo_Client() {
        return UserBLFactory.getUserInfo_Client();
    }

    @Override
    public UserBLInfo getUserBLInfo_Staff() {
        return UserBLFactory.getUserInfo_Staff();
    }

    @Override
    public UserBLInfo getUserBLInfo_Salesman() {
        return UserBLFactory.getUserInfo_Salesman();
    }



    // Stub

    @Override
    public OrderBLService getOrderBLService_Stub() {
        return new OrderBLService_Stub();
    }

    @Override
    public HotelBLService getHotelBLService_Stub() {
        return new HotelBLService_Stub();
    }

    @Override
    public PromotionBLService getPromotionBLService_Stub() {
        return new PromotionBLService_Stub();
    }

    @Override
    public UserBLService getUserBLService_Stub() {
        return new UserBLService_Stub();
    }

    @Override
    public HotelBLInfo getHotelBLInfo_Stub() {
        return new HotelBLService_Stub();
    }

    @Override
    public UserBLInfo getUserBLInfo_Stub() {
        return new UserBLService_Stub();
    }


}
