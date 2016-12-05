package bl.blfactory;

import bl.hotelbl.HotelBLFactory;
import bl.hotelbl.HotelBLServiceImpl;
import bl.promotionbl.PromotionBLFactory;
import bl.userbl.UserBLFactory;
import blservice.blfactoryservice.BLFactoryService;
import blservice.hotelblservice.HotelBLService;
import blservice.orderblservice.OrderBLService;
import blservice.orderblservice.OrderBLService_Stub;
import blservice.promotionblservice.PromotionBLInfo;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLService;

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
        return new OrderBLService_Stub();
    }

    @Override
    public PromotionBLService getPromotionBLService() {
        return PromotionBLFactory.getPromotionBLService();
    }

    public PromotionBLInfo getPromotionBLInfo(){return PromotionBLFactory.getPromotionBLService();}

    public UserBLService getClientBLService() {
        return UserBLFactory.getUserBLServiceImpl_Client();
    }

    public UserBLService getStaffBLService() {
        return UserBLFactory.getUserBLServiceImpl_Staff();
    }

    public UserBLService getSalesmanBLService() {
        return UserBLFactory.getUserBLServiceImpl_Salesman();
    }
}
