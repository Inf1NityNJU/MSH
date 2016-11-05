package bl.hotelbl;

import blservice.hotelblservice.HotelBLService;

/**
 * Created by SilverNarcissus on 16/11/5.
 */
public class HotelBLFactory {
    private static HotelBLServiceImpl realHotelBLServiceImpl;
    private static HotelBLServiceImpl mockHotelBLServiceImpl;

    public synchronized static HotelBLServiceImpl getHotelBLServiceImpl(){
        if(realHotelBLServiceImpl==null){
            realHotelBLServiceImpl=new HotelBLServiceImpl(false);
        }
        return realHotelBLServiceImpl;
    }
    public synchronized static HotelBLServiceImpl getMockHotelBLServiceImpl(){
        if(mockHotelBLServiceImpl==null){
            mockHotelBLServiceImpl=new HotelBLServiceImpl(true);
        }
        return mockHotelBLServiceImpl;
    }
}
