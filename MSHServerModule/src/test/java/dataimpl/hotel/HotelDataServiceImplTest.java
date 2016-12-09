package dataimpl.hotel;

import dataimpl.hoteldataimpl.HotelDataServiceFactory;
import dataservice.hoteldataservice.HotelDataService;
import org.junit.Ignore;
import org.junit.Test;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
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

    @Test
    public void addHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.addHotel(new HotelPO("00000009", "Test hotel 啊", "Nanjing University", Place.XinJieKou, 5, "The test hotel", "All"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);

    }

    @Test
    public void addRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.addRoom(new HotelRoomPO("0000000104", "00000001", RoomType.DoubleDouble, 258.5, 8, false));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchHotel1() {
        ArrayList<HotelPO> hotelPOs = hotelDataService.prefixSearchHotel("address", "Nanjing");
        assertEquals(8, hotelPOs.size());
    }

    @Test
    public void searchHotel2() {
        ArrayList<HotelPO> hotelPOs = hotelDataService.exactlySearchHotel("place", Place.XianLin);
        assertEquals(2, hotelPOs.size());
    }

    @Test
    public void getHotel() throws Exception {
        HotelPO hotelPO = hotelDataService.getHotel("00000000");
        assertEquals("00000000", hotelPO.getID());
    }

    @Test
    public void getRoom() throws Exception {
        ArrayList<HotelRoomPO> hotelRoomPOs = hotelDataService.getRoom("00000001");
        assertEquals(3, hotelRoomPOs.size());
    }

    @Test
    public void updateHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.updateHotel(new HotelPO("00000001", "hoteldataimpl", "Nanjing News University", Place.XianLin, 5, "The test hotel", "All"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.updateRoom(new HotelRoomPO("0000000100", "00000001", RoomType.SuiteRoom, 888.5, 2, false));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.deleteHotel("00000006");
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void deleteRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.deleteRoom("0000000205");
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void addRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        RoomStockPO roomStockPO = new RoomStockPO("0000010101", "000001", RoomType.SingleRoom, 5, "2016-11-17");
        ResultMessage resultMessage = hotelDataService.addRoomStock(roomStockPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        RoomStockPO roomStockPO = new RoomStockPO("0000010101", "000001", RoomType.SingleRoom, 10, "2015-11-17");
        ResultMessage resultMessage = hotelDataService.updateRoomStock(roomStockPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        ResultMessage resultMessage = hotelDataService.deleteRoomStock("0000010101");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void getRoomStockPO() throws Exception {

    }


}