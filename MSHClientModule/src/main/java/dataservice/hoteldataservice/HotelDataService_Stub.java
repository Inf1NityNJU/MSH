package dataservice.hoteldataservice;

import po.HotelPO;
import po.HotelRoomPO;
import util.HotelNotFoundException;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class HotelDataService_Stub implements HotelDataService{

    public HotelPO getHotel(String hotelID) throws HotelNotFoundException {
        System.out.println("Get hotel Success!");
        return new HotelPO(null,null,null,null,0,null,null);
    }

    public ArrayList<HotelRoomPO> getRoom(String hotelID){
        System.out.println("Get hotel room Success!");
        return new ArrayList<HotelRoomPO>();
    }

    public ResultMessage updateHotel(HotelPO hvo){
        System.out.println("Update hotel Success!");
        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateRoom(HotelRoomPO rvo){
        System.out.println("Update hotel room Success!");
        return ResultMessage.SUCCESS;
    }

    public ResultMessage addHotel(HotelPO hvo){
        System.out.println("Add hotel Success!");
        return ResultMessage.SUCCESS;
    }

    public ResultMessage addRoom(HotelRoomPO rvo){
        System.out.println("Add hotel room Success!");
        return ResultMessage.SUCCESS;
    }

    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException {
        System.out.println("Delete Success!");
        return ResultMessage.SUCCESS;
    }
}
