package blservice.blfactoryservice;

import blservice.hotelblservice.HotelBLInfo;
import blservice.hotelblservice.HotelBLService;
import blservice.orderblservice.OrderBLInfo;
import blservice.orderblservice.OrderBLService;
import blservice.promotionblservice.PromotionBLInfo;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;

/**
 * Created by Sorumi on 16/12/3.
 */
public interface BLFactoryService {

    public HotelBLService getHotelBLService();

    public OrderBLService getOrderBLService();

    public PromotionBLService getPromotionBLService();

    public UserBLService getClientBLService();

    public UserBLService getStaffBLService();

    public UserBLService getSalesmanBLService();

    public HotelBLInfo getHotelBLInfo();

    public OrderBLInfo getOrderBLInfo();

    public PromotionBLInfo getPromotionBLInfo();

    public UserBLInfo getUserBLInfo_Client();

    public UserBLInfo getUserBLInfo_Staff();

    public UserBLInfo getUserBLInfo_Salesman();


    // Stub
    public OrderBLService getOrderBLService_Stub();

    public HotelBLService getHotelBLService_Stub();

    public PromotionBLService getPromotionBLService_Stub();

    public UserBLService getUserBLService_Stub();

    public HotelBLInfo getHotelBLInfo_Stub();

    public UserBLInfo getUserBLInfo_Stub();

}
