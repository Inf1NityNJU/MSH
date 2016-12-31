package dataimpl.hotel;

import dataimpl.hoteldataimpl.HotelDataServiceFactory;
import dataservice.hoteldataservice.HotelDataService;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
 * Done on 16/12/31.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelDataServiceImplTest {
    private HotelDataService hotelDataService;

    public HotelDataServiceImplTest() {
        hotelDataService = HotelDataServiceFactory.getHotelDataService();
    }

    @Test
    public void a_addHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.addHotel(new HotelPO("00000000", "Test hotel", "Nanjing University", Place.XinJieKou, 5, "The test hotel", "All"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);

    }

    @Test
    public void b_addRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.addRoom(new HotelRoomPO("0000000001", "00000000", RoomType.DoubleRoom, 258.5, 8, false));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void c_searchHotel1() {
        ArrayList<HotelPO> hotelPOs = hotelDataService.prefixSearchHotel("address", "Nanjing");
        assertEquals(1, hotelPOs.size());
    }

    @Test
    public void d_searchHotel2() {
        ArrayList<HotelPO> hotelPOs = hotelDataService.exactlySearchHotel("place", Place.XinJieKou);
        assertEquals(1, hotelPOs.size());
    }

    @Test
    public void e_getHotel() throws Exception {
        HotelPO hotelPO = hotelDataService.getHotel("00000000");
        assertEquals("00000000", hotelPO.getID());
    }

    @Test
    public void f_getRoom() throws Exception {
        ArrayList<HotelRoomPO> hotelRoomPOs = hotelDataService.getRoom("00000000");
        assertEquals(1, hotelRoomPOs.size());
    }

    @Test
    public void g_updateHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.updateHotel(new HotelPO("00000000", "test 2", "Nanjing News University", Place.XianLin, 5, "The test hotel", "All"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void h_updateRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.updateRoom(new HotelRoomPO("0000000001", "00000001", RoomType.DoubleRoom, 888.5, 2, false));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void i_deleteHotel() throws Exception {
        ResultMessage resultMessage = hotelDataService.deleteHotel("00000000");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void j_deleteRoom() throws Exception {
        ResultMessage resultMessage = hotelDataService.deleteRoom("0000000001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void k_addRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        RoomStockPO roomStockPO = new RoomStockPO("000000000001", "000000", RoomType.SingleRoom, 5, "2016-11-17");
        ResultMessage resultMessage = hotelDataService.addRoomStock(roomStockPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void l_updateRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        RoomStockPO roomStockPO = new RoomStockPO("000000000001", "000000", RoomType.SingleRoom, 10, "2015-11-17");
        ResultMessage resultMessage = hotelDataService.updateRoomStock(roomStockPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void m_getRoomStockPO() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        ArrayList<RoomStockPO> roomStockPO=hotelDataService.getRoomStock("0000000000");
        assertEquals(1,roomStockPO.size());
    }

    @Test
    public void n_deleteRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        ResultMessage resultMessage = hotelDataService.deleteRoomStock("000000000001");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }
}