package dataimpl.hoteldataimpl;

import datahelper.DataHelper;
import datahelper.DataHelperFactory;
import datahelper.HibernateHelper;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;

/**
 * Created by SilverNarcissus on 16/11/14.
 */
public class HotelDataServiceFactory {
    private static HotelDataServiceImpl hotelDataService;

    /**
     * 得到一个HotelDataService实例
     *
     * @return HotelDataService实例
     */
    public static synchronized HotelDataService getHotelDataService() {
        if (hotelDataService == null) {
            hotelDataService = new HotelDataServiceImpl(new HibernateHelper<HotelPO>(HotelPO.class), new HibernateHelper<HotelRoomPO>(HotelRoomPO.class), new HibernateHelper<RoomStockPO>(RoomStockPO.class));
        }
        return hotelDataService;
    }
}
