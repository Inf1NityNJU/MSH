package dataimpl.Hotel;

import datahelper.DataHelperFactory;
import datahelper.DataHelper;
import dataservice.hoteldataservice.HotelDataService;

/**
 * Created by SilverNarcissus on 16/11/14.
 */
public class HotelDataServiceFactory {
    private static HotelDataServiceImpl hotelDataService;

    /**
     * 得到一个HotelDataService实例
     * @return HotelDataService实例
     */
    public static synchronized HotelDataService getHotelDataService(){
        if(hotelDataService==null){
            hotelDataService=new HotelDataServiceImpl(DataHelperFactory.getHibernateDataHelper());
        }
        return hotelDataService;
    }
}
