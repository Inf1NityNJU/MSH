package data;

import data.hotel.HotelDataServiceImpl;
import org.junit.Test;
import po.HotelPO;
import util.Place;
import util.ResultMessage;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 16/11/13.
 */
public class HotelDataServiceImplTest {
    @Test
    public void getHotel() throws Exception {

    }

    @Test
    public void getRoom() throws Exception {

    }

    @Test
    public void updateHotel() throws Exception {

    }

    @Test
    public void updateRoom() throws Exception {

    }

    @Test
    public void addHotel() throws Exception {
        HotelDataServiceImpl hotelDataService=new HotelDataServiceImpl();
        ResultMessage resultMessage=hotelDataService.addHotel(new HotelPO("000002","如家","南京市栖霞区仙林大道163号", Place.XIANLIN,5,"第一个放进数据库的宾馆","什么都有"));
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

    @Test
    public void addRoom() throws Exception {

    }

    @Test
    public void deleteHotel() throws Exception {

    }

}