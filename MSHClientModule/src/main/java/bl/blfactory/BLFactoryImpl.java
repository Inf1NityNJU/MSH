package bl.blfactory;

import bl.hotelbl.HotelBLFactory;
import bl.hotelbl.HotelBLServiceImpl;
import blservice.blfactoryservice.BLFactoryService;
import blservice.hotelblservice.HotelBLService;
import blservice.orderblservice.OrderBLService;
import blservice.orderblservice.OrderBLService_Stub;
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
        return null;
    }

    @Override
    public UserBLService getUserBLService() {
        return null;
    }
}
