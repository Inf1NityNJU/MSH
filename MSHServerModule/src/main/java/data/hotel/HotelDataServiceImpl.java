package data.hotel;

import datahelper.DataHelper;
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
    private DataHelper hotelDataHelper;
    private DataHelper hotelRoomDataHelper;

    protected HotelDataServiceImpl(DataHelper hotelDataHelper, DataHelper hotelRoomDataHelper) {
        this.hotelDataHelper = hotelDataHelper;
        this.hotelRoomDataHelper = hotelRoomDataHelper;
    }

    @Override
    public ArrayList<HotelPO> prefixSearchHotel(String field, String value) {
        return hotelDataHelper.prefixMatchQuery(HotelPO.class, field, value);
    }

    @Override
    public ArrayList<HotelPO> suffixSearchHotel(String field, String value) {
        return hotelDataHelper.suffixMatchQuery(HotelPO.class, field, value);
    }

    @Override
    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value) {
        return hotelDataHelper.fuzzyQuery(HotelPO.class, field, value);
    }

    @Override
    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max) {
        return hotelDataHelper.rangeQuery(HotelPO.class, field, min, max);
    }

    @Override
    public HotelPO getHotel(String hotelID) throws HotelNotFoundException {
        return hotelDataHelper.exactlyQuery(HotelPO.class, "id", hotelID);
    }

    @Override
    public ArrayList<HotelRoomPO> getRoom(String hotelID) {
        return hotelRoomDataHelper.suffixMatchQuery(HotelRoomPO.class, "hotelID", hotelID);
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
    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException {
        return hotelDataHelper.delete(hotelID);
    }

    @Override
    public ResultMessage deleteRoom(String roomID) {
        return hotelRoomDataHelper.delete(roomID);
    }
}
