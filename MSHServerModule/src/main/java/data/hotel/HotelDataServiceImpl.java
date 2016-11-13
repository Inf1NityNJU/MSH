package data.hotel;

import datahelper.DataHelper;
import datahelper.HibernateHelper;
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
    private DataHelper dataHelper=new HibernateHelper("HotelPO.cfg.xml");
    public HotelDataServiceImpl(){
        dataHelper.setClassName("po.HotelPO");
    }

    public HotelPO getHotel(String hotelID) throws HotelNotFoundException {
        return null;
    }

    public ArrayList<HotelRoomPO> getRoom(String hotelID) {
        return null;
    }

    public ResultMessage updateHotel(HotelPO hotelPO) {
        return null;
    }

    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO) {
        return null;
    }

    public ResultMessage addHotel(HotelPO hotelPO) {
        return dataHelper.save(hotelPO);
    }

    public ResultMessage addRoom(HotelRoomPO hotelRoomPO) {
        return null;
    }

    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException {
        return null;
    }
}
