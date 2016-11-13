package data.hotel;

import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;
import po.HotelRoomPO;
import util.HotelNotFoundException;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public class HotelDataServiceImpl implements HotelDataService {
    public HotelPO getHotel(String hotelID) throws HotelNotFoundException {
        return null;
    }

    public ArrayList<HotelRoomPO> getRoom(String hotelID) {
        return null;
    }

    public ResultMessage updateHotel(HotelPO hvo) {
        return null;
    }

    public ResultMessage updateRoom(HotelRoomPO rvo) {
        return null;
    }

    public ResultMessage addHotel(HotelPO hvo) {
        return null;
    }

    public ResultMessage addRoom(HotelRoomPO rvo) {
        return null;
    }

    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException {
        return null;
    }
}
