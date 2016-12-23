package blimpl.hotelblimpl;

import blservice.hotelblservice.HotelBLInfo;

/**
 * Created by SilverNarcissus on 16/11/5.
 * All Done on 16/11/26
 */
public class HotelBLFactory {

    private static Hotel hotel = new Hotel();
    private static HotelRoom hotelRoom = new HotelRoom();
    private static HotelBLServiceImpl hotelBLServiceImpl;

    public synchronized static HotelBLServiceImpl getHotelBLService() {
        if (hotelBLServiceImpl == null) {
            hotelBLServiceImpl = new HotelBLServiceImpl(hotel, hotelRoom);
        }
        return hotelBLServiceImpl;
    }

    public synchronized static HotelBLInfo getHotelBLInfo() {
        if (hotelBLServiceImpl == null) {
            hotelBLServiceImpl = new HotelBLServiceImpl(hotel, hotelRoom);
        }
        return hotelBLServiceImpl;
    }

}
