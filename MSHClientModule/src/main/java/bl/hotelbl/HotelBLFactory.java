package bl.hotelbl;

import blservice.hotelblservice.HotelBLService;

/**
 * Created by SilverNarcissus on 16/11/5.
 */
public class HotelBLFactory {
    private static HotelBLServiceImpl HotelBLServiceImpl;

    public synchronized static HotelBLService getHotelBLService() {
        if (HotelBLServiceImpl == null) {
            HotelBLServiceImpl = new HotelBLServiceImpl(getHotel(),getHotelRoom());
        }
        return HotelBLServiceImpl;
    }

    private synchronized static Hotel getHotel(){
        return new Hotel();
    }

    private synchronized static HotelRoom getHotelRoom(){
        return new HotelRoom();
    }
}
