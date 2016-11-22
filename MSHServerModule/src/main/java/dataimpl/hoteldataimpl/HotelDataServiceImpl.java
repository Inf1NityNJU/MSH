package dataimpl.hoteldataimpl;

import datahelper.DataHelper;
import datahelper.HibernateHelper;
import dataservice.hoteldataservice.HotelDataService;
import org.junit.Test;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.Place;
import util.ResultMessage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public class HotelDataServiceImpl implements HotelDataService {
    private DataHelper<HotelPO> hotelDataHelper = new HibernateHelper<HotelPO>();
    private DataHelper<HotelRoomPO> hotelRoomDataHelper = new HibernateHelper<HotelRoomPO>();
    private DataHelper<RoomStockPO> roomStockDataHelper = new HibernateHelper<RoomStockPO>();

    protected HotelDataServiceImpl(DataHelper<HotelPO> hotelDataHelper) {
        this.hotelDataHelper = hotelDataHelper;
    }

    @Override
    public ArrayList<HotelPO> prefixSearchHotel(String field, String value) {
        return hotelDataHelper.prefixMatchQuery(field, value);
    }

    @Override
    public ArrayList<HotelPO> suffixSearchHotel(String field, String value) {
        return hotelDataHelper.suffixMatchQuery(field, value);
    }

    @Override
    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value) {
        return hotelDataHelper.fuzzyMatchQuery(field, value);
    }

    @Override
    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max) {
        return hotelDataHelper.rangeQuery(field, min, max);
    }

    @Override
    public HotelPO getHotel(String hotelID) {
        return hotelDataHelper.exactlyQuery("id", hotelID);
    }

    @Override
    public ArrayList<HotelRoomPO> getRoom(String hotelID) {
        return hotelRoomDataHelper.prefixMatchQuery("hotelID", hotelID);
    }

    @Override
    public ResultMessage updateHotel(HotelPO hotelPO) {
        return hotelDataHelper.update(hotelPO);
    }

    @Override
    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO) {
        return hotelDataHelper.update(hotelRoomPO);
    }

    @Override
    public ResultMessage addHotel(HotelPO hotelPO) {
        return hotelDataHelper.save(hotelPO);
    }

    @Override
    public ResultMessage addRoom(HotelRoomPO hotelRoomPO) {
        return hotelDataHelper.save(hotelRoomPO);
    }

    @Override
    public ResultMessage deleteHotel(String hotelID) {
        return hotelDataHelper.delete("ID", hotelID);
    }

    @Override
    public ResultMessage deleteRoom(String roomID) {
        return hotelDataHelper.delete("ID", roomID);
    }

    @Override
    public ResultMessage addRoomStock(RoomStockPO roomStockPO) {
        return hotelDataHelper.save(roomStockPO);
    }

    @Override
    public ResultMessage updateRoomStock(RoomStockPO roomStockPO) {
        return hotelDataHelper.update(roomStockPO);
    }

    @Override
    public ResultMessage deleteRoomStock(String roomStockID) {
        return hotelDataHelper.delete("ID", roomStockID);
    }

    @Override
    public ArrayList<RoomStockPO> getRoomStock(String hotelRoomID) {
        return roomStockDataHelper.prefixMatchQuery("ID", hotelRoomID);
    }

}
