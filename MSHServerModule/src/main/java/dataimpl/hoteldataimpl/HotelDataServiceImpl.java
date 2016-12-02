package dataimpl.hoteldataimpl;

import datahelper.DataHelper;
import datahelper.HibernateHelper;
import dataservice.hoteldataservice.HotelDataService;
import org.junit.Test;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.CriteriaClause;
import util.Place;
import util.ResultMessage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public class HotelDataServiceImpl implements HotelDataService {
    private DataHelper<HotelPO> hotelDataHelper;
    private DataHelper<HotelRoomPO> hotelRoomDataHelper;
    private DataHelper<RoomStockPO> roomStockDataHelper;

    protected HotelDataServiceImpl(DataHelper<HotelPO> hotelDataHelper, DataHelper<HotelRoomPO> hotelRoomDataHelper, DataHelper<RoomStockPO> roomStockDataHelper) {
        this.hotelDataHelper = hotelDataHelper;
        this.hotelRoomDataHelper = hotelRoomDataHelper;
        this.roomStockDataHelper = roomStockDataHelper;
    }

    @Override
    public ArrayList<HotelPO> prefixSearchHotel(String field, String value) {
        return hotelDataHelper.prefixMatchQuery(field, value);
    }

    @Override
    public ArrayList<HotelPO> exactlySearchHotel(String field, Object value) {
        return hotelDataHelper.fullMatchQuery(field, value);
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
    public HotelRoomPO getRoomByID(String hotelRoomID) {
        return hotelRoomDataHelper.exactlyQuery("ID", hotelRoomID);
    }

    @Override
    public ResultMessage updateHotel(HotelPO hotelPO) {
        return hotelDataHelper.update(hotelPO);
    }

    @Override
    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO) {
        return hotelRoomDataHelper.update(hotelRoomPO);
    }

    @Override
    public ResultMessage addHotel(HotelPO hotelPO) {
        return hotelDataHelper.save(hotelPO);
    }

    @Override
    public ResultMessage addRoom(HotelRoomPO hotelRoomPO) {
        return hotelRoomDataHelper.save(hotelRoomPO);
    }

    @Override
    public ResultMessage deleteHotel(String hotelID) {
        return hotelDataHelper.delete("ID", hotelID);
    }

    @Override
    public ResultMessage deleteRoom(String roomID) {
        return hotelRoomDataHelper.delete("ID", roomID);
    }

    @Override
    public ResultMessage addRoomStock(RoomStockPO roomStockPO) {
        return roomStockDataHelper.save(roomStockPO);
    }

    @Override
    public ResultMessage updateRoomStock(RoomStockPO roomStockPO) {
        return roomStockDataHelper.update(roomStockPO);
    }

    @Override
    public ResultMessage deleteRoomStock(String roomStockID) {
        return roomStockDataHelper.delete("ID", roomStockID);
    }

    @Override
    public ArrayList<RoomStockPO> getRoomStock(String hotelRoomID) {
        return roomStockDataHelper.prefixMatchQuery("ID", hotelRoomID);
    }

    @Override
    public ArrayList<HotelPO> fullSearchHotel(String field, Object value) {
        return hotelDataHelper.fullMatchQuery(field, value);
    }

    @Override
    public ArrayList<HotelRoomPO> fullSearchHotelRoom(String field, Object value) {
        return hotelRoomDataHelper.fullMatchQuery(field, value);
    }

    @Override
    public ArrayList<HotelPO> multiSearchHotel(ArrayList<CriteriaClause> criteriaClauses) {
        return hotelDataHelper.multiCriteriaQuery(criteriaClauses);
    }

    @Override
    public ArrayList<HotelRoomPO> multiSearchHotelRoom(ArrayList<CriteriaClause> criteriaClauses) {
        return hotelRoomDataHelper.multiCriteriaQuery(criteriaClauses);
    }

    @Override
    public ArrayList<RoomStockPO> multiSearchRoomStockPO(ArrayList<CriteriaClause> criteriaClauses) {
        return roomStockDataHelper.multiCriteriaQuery(criteriaClauses);
    }
}
