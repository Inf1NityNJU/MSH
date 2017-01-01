package hotel;

import blimpl.hotelblimpl.HotelBLFactory;
import blservice.hotelblservice.HotelBLInfo;
import blservice.hotelblservice.HotelBLService;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.DateUtil;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;
import vo.OrderRoomStockVO;
import vo.RoomChangeInfoVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/18.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelRoomTest {
    private HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();
    private HotelBLInfo hotelBLInfo = HotelBLFactory.getHotelBLInfo();

    @Test
    public void a_addRoom() throws Exception {
        HotelRoomVO hotelRoomVO = new HotelRoomVO("00000000", RoomType.DoubleDouble, 99, 5, null);
        ResultMessage resultMessage = hotelBLService.addRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void b_updateHotelRoom() throws Exception {
        HotelRoomVO hotelRoomVO = new HotelRoomVO("00000000", RoomType.DoubleDouble, 488.8, 3, null);
        ResultMessage resultMessage = hotelBLService.updateHotelRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void c_getRoom() throws Exception {
        ArrayList<HotelRoomVO> hotelRoomVOs = hotelBLService.getRoom("00000000");
        assertEquals(1, hotelRoomVOs.size());
    }



    @Test
    public void d_updateHotelRoomQuantity1() throws Exception {
        DateUtil start = new DateUtil(2016, 1,5);
        DateUtil end = new DateUtil(2016, 1, 10);
        int quantity = 3;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void e_updateHotelRoomQuantity2() throws Exception {
        DateUtil start = new DateUtil(2016, 1, 5);
        DateUtil end = new DateUtil(2016, 1, 10);
        int quantity = -3;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void f_updateHotelRoomQuantity3() throws Exception {
        DateUtil start = new DateUtil(2016, 1, 5);
        DateUtil end = new DateUtil(2016, 1, 10);
        int quantity = -10;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }




    @Test
    public void g_deleteHotelRoom() throws Exception {
        ResultMessage resultMessage = hotelBLService.deleteHotelRoom("00000000", RoomType.DoubleDouble);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }


    @Test
    public void h_isOrdered() throws Exception {
        ResultMessage resultMessage = hotelBLService.isOrdered("00000000", RoomType.DoubleDouble);
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void i_getAvailableQuantity() {
        int quantity = hotelBLInfo.getAvailableQuantity(new DateUtil(2016, 12, 5), new DateUtil(2016, 12, 20), "00000000", RoomType.DoubleDouble);
        System.out.println(quantity);
    }

    @Test
    public void j_getOrderRoomStockVO() {
        ArrayList<OrderRoomStockVO> orderRoomStockVOs = hotelBLService.getRoomStocks(new DateUtil(2016, 1, 5), new DateUtil(2016, 1, 10), "00000000");
    }
}