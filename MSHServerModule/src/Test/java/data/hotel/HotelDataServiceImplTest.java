package data.hotel;

import data.hotel.HotelDataServiceFactory;
import data.hotel.HotelDataServiceImpl;
import dataservice.hoteldataservice.HotelDataService;
import org.junit.Ignore;
import org.junit.Test;
import po.HotelPO;
import po.HotelRoomPO;
import util.Place;
import util.ResultMessage;
import util.RoomType;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 16/11/13.
 */
public class HotelDataServiceImplTest {
    private HotelDataService hotelDataService;

    public HotelDataServiceImplTest() {
        hotelDataService = HotelDataServiceFactory.getHotelDataService();
    }

    @Ignore
    public void addHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.addHotel(new HotelPO("000005", "My Hotel", "Nanjing Medical University", Place.XIANLIN, 4, "The test hotel", "All"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Ignore
    public void addRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.addRoom(new HotelRoomPO("00000102", "000001", RoomType.SingleRoom, 258.5, 8));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchHotel() {
        ArrayList<HotelPO> hotelPOs = hotelDataService.prefixSearchHotel("address", "Nanjing");
        assertEquals(5, hotelPOs.size());
    }

    @Test
    public void getHotel() throws Exception {
        HotelPO hotelPO = hotelDataService.getHotel("000000");
        assertEquals("000000", hotelPO.getID());
    }

    @Test
    public void getRoom() throws Exception {
        ArrayList<HotelRoomPO> hotelRoomPOs = hotelDataService.getRoom("000001");
        assertEquals(2, hotelRoomPOs.size());
    }

    @Test
    public void updateHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.updateHotel(new HotelPO("000001", "Hotel", "Nanjing News University", Place.XIANLIN, 5, "The test hotel", "All"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.updateRoom(new HotelRoomPO("00000102", "000001", RoomType.Suite, 888.5, 2));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.deleteHotel("000005");
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void deleteRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.deleteRoom("00000002");
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }


}