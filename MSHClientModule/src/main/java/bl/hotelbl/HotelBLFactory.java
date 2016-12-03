package bl.hotelbl;

import blservice.hotelblservice.HotelBLService;

/**
 * Created by SilverNarcissus on 16/11/5.
 * All Done on 16/11/26
 */
public class HotelBLFactory {
    private static HotelBLServiceImpl hotelBLServiceImpl;

    public synchronized static HotelBLServiceImpl getHotelBLService() {
        if (hotelBLServiceImpl == null) {
            hotelBLServiceImpl = new HotelBLServiceImpl(getHotel(),getHotelRoom());
        }
        return hotelBLServiceImpl;
    }

    private synchronized static Hotel getHotel(){
        return new Hotel();
    }

    private synchronized static HotelRoom getHotelRoom(){
        return new HotelRoom();
    }
}
