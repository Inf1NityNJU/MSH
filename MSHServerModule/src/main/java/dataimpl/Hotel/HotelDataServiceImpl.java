package dataimpl.Hotel;

import datahelper.DataHelper;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public class HotelDataServiceImpl implements HotelDataService {
    private DataHelper dataHelper;


    protected HotelDataServiceImpl(DataHelper dataHelper) {
        this.dataHelper = dataHelper;
    }

    @Override
    public ArrayList<HotelPO> prefixSearchHotel(String field, String value) {
        return dataHelper.prefixMatchQuery(HotelPO.class, field, value);
    }

    @Override
    public ArrayList<HotelPO> suffixSearchHotel(String field, String value) {
        return dataHelper.suffixMatchQuery(HotelPO.class, field, value);
    }

    @Override
    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value) {
        return dataHelper.fuzzyMatchQuery(HotelPO.class, field, value);
    }

    @Override
    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max) {
        return dataHelper.rangeQuery(HotelPO.class, field, min, max);
    }

    @Override
    public HotelPO getHotel(String hotelID) {
        return dataHelper.exactlyQuery(HotelPO.class, "id", hotelID);
    }

    @Override
    public ArrayList<HotelRoomPO> getRoom(String hotelID) {
        return dataHelper.suffixMatchQuery(HotelRoomPO.class, "hotelID", hotelID);
    }

    @Override
    public ResultMessage updateHotel(HotelPO hotelPO) {
        return dataHelper.update(HotelPO.class,hotelPO);
    }

    @Override
    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO) {
        return dataHelper.update(HotelRoomPO.class,hotelRoomPO);
    }

    @Override
    public ResultMessage addHotel(HotelPO hotelPO) {
        return dataHelper.save(HotelPO.class,hotelPO);
    }

    @Override
    public ResultMessage addRoom(HotelRoomPO hotelRoomPO) {
        return dataHelper.save(HotelRoomPO.class,hotelRoomPO);
    }

    @Override
    public ResultMessage deleteHotel(String hotelID) {
        return dataHelper.delete(HotelPO.class,hotelID,"ID");
    }

    @Override
    public ResultMessage deleteRoom(String roomID) {
        return dataHelper.delete(HotelRoomPO.class,roomID,"ID");
    }

    @Override
    public ResultMessage addRoomStock(RoomStockPO roomStockPO) {
        return dataHelper.save(RoomStockPO.class,roomStockPO);
    }

    @Override
    public ResultMessage updateRoomStock(RoomStockPO roomStockPO) {
        return dataHelper.update(RoomStockPO.class,roomStockPO);
    }

    @Override
    public ResultMessage deleteRoomStock(String roomStockID) {
        return dataHelper.delete(RoomStockPO.class,roomStockID,"ID");
    }

    @Override
    public RoomStockPO getRoomStock(String roomStockID) {
        return dataHelper.exactlyQuery(RoomStockPO.class, "ID", roomStockID);
    }
}
