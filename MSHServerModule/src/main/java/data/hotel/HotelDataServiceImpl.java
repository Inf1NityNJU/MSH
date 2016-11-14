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
    private DataHelper dataHelper = new HibernateHelper("HotelPO.cfg.xml");
    private Class<HotelPO> classType;

    public HotelDataServiceImpl() {
        dataHelper.setClassName("po.HotelPO");
        //
        try {
            classType=(Class<HotelPO>) Class.forName("po.HotelPO");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HotelPO> searchHotel(String address){
        return dataHelper.rangeQuery(classType,"star",4,5);
    }

    public HotelPO getHotel(String hotelID) throws HotelNotFoundException {
        return dataHelper.exactlyQuery(classType,"id",hotelID);
    }

    public ArrayList<HotelRoomPO> getRoom(String hotelID) {
        return null;
    }

    public ResultMessage updateHotel(HotelPO hotelPO) {
        return dataHelper.update(hotelPO);
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
        return dataHelper.delete(hotelID);
    }
}
