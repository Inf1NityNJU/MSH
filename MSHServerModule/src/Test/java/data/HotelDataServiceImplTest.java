package data;

import data.hotel.HotelDataServiceImpl;
import org.junit.Test;
import po.HotelPO;
import util.Place;
import util.ResultMessage;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 16/11/13.
 */
public class HotelDataServiceImplTest {
    @Test
    public void searchHotel(){
        HotelDataServiceImpl hotelDataService=new HotelDataServiceImpl();
        ArrayList<HotelPO> hotelPOs=hotelDataService.searchHotel("Un");
        assertEquals(3,hotelPOs.size());
    }
    @Test
    public void getHotel() throws Exception {
        HotelDataServiceImpl hotelDataService=new HotelDataServiceImpl();
        HotelPO hotelPO=hotelDataService.getHotel("000000");
        assertEquals("000000",hotelPO.getID());
    }

    @Test
    public void getRoom() throws Exception {

    }

    @Test
    public void updateHotel() throws Exception {

    }

    @Test
    public void updateRoom() throws Exception {
        HotelDataServiceImpl hotelDataService=new HotelDataServiceImpl();
        ResultMessage resultMessage=hotelDataService.updateHotel(new HotelPO("000001","Hotel","Nanjing University", Place.XIANLIN,5,"The test hotel","All"));
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

    @Test
    public void addHotel() throws Exception {
        HotelDataServiceImpl hotelDataService=new HotelDataServiceImpl();
        ResultMessage resultMessage=hotelDataService.addHotel(new HotelPO("000001","Hotel","Nanjing Medical University", Place.XIANLIN,5,"The test hotel","All"));
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

    @Test
    public void addRoom() throws Exception {

    }

    @Test
    public void deleteHotel() throws Exception {
        HotelDataServiceImpl hotelDataService=new HotelDataServiceImpl();
        ResultMessage resultMessage=hotelDataService.deleteHotel("000001");
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

}