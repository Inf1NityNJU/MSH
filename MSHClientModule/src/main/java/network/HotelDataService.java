package network;

import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.ResultMessage;

import java.rmi.Remote;
import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/12.
 *
 */
public interface HotelDataService extends Remote{

    public ArrayList<HotelPO> exactlySearchHotel(String field, Object value);

    public ArrayList<HotelPO> prefixSearchHotel(String field, String value);

    public ArrayList<HotelPO> fullSearchHotel(String field, Object value);

    public ArrayList<HotelPO> suffixSearchHotel(String field, String value);

    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value);

    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max);

    public HotelPO getHotel(String hotelID);

    public ArrayList<HotelRoomPO> getRoom(String hotelID);

    public ResultMessage updateHotel(HotelPO hotelPO);

    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO);

    public ResultMessage addHotel(HotelPO hotelPO);

    public ResultMessage addRoom(HotelRoomPO hotelRoomPO);

    public ResultMessage deleteHotel(String hotelID);

    public ResultMessage deleteRoom(String roomID);

    public ResultMessage addRoomStock(RoomStockPO roomStockPO);

    public ResultMessage updateRoomStock(RoomStockPO roomStockPO);

    public ResultMessage deleteRoomStock(String roomStockID);

    public ArrayList<RoomStockPO> getRoomStock(String hotelRoomID);

    public HotelRoomPO getRoomByID(String hotelRoomID);

    public ArrayList<HotelRoomPO> fullSearchHotelRoom(String field, Object value);
}
