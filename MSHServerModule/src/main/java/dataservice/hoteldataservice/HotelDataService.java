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

    public ResultMessage updateHotel(HotelPO hvo);

    public ResultMessage updateRoom(HotelRoomPO rvo);

    public ResultMessage addHotel(HotelPO hvo);

    public ResultMessage addRoom(HotelRoomPO rvo);

    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException;
}
