package dataservice.hoteldataservice;

import po.HotelPO;
import po.HotelRoomPO;
import util.HotelNotFoundException;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public interface HotelDataService {
    public HotelPO getHotel(String hotelID) throws HotelNotFoundException;

    public ArrayList<HotelRoomPO> getRoom(String hotelID);

    public ResultMessage updateHotel(HotelPO hotelPO);

    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO);

    public ResultMessage addHotel(HotelPO hotelPO);

    public ResultMessage addRoom(HotelRoomPO hotelRoomPO);

    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException;
}
