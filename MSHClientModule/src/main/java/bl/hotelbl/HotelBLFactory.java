package bl.hotelbl;

import blservice.hotelblservice.HotelBLService;

/**
 * Created by SilverNarcissus on 16/11/5.
 */
public class HotelBLFactory {
    private static HotelBLServiceImpl HotelBLServiceImpl;

    public synchronized static HotelBLServiceImpl getHotelBLServiceImpl() {
        if (HotelBLServiceImpl == null) {
            HotelBLServiceImpl = new HotelBLServiceImpl(false);
        }
        return HotelBLServiceImpl;
    }
}
