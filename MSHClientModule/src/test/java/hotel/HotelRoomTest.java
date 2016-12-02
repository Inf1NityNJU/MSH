package hotel;

import bl.hotelbl.HotelBLFactory;
import bl.hotelbl.HotelRoom;
import blservice.hotelblservice.HotelBLService;
import org.junit.Ignore;
import org.junit.Test;
import util.DateUtil;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;
import vo.RoomChangeInfoVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/18.
 */
public class HotelRoomTest {
    private HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();

    @Ignore
    public void getRoom() throws Exception {
        ArrayList<HotelRoomVO> hotelRoomVOs = hotelBLService.getRoom("00000001");
        assertEquals(2, hotelRoomVOs.size());
        assertEquals(10, hotelRoomVOs.get(0).roomStockVOs.get(0).availableQuantity);
    }

    @Ignore
    public void updateHotelRoom() throws Exception {
        HotelRoomVO hotelRoomVO = new HotelRoomVO("00000000", RoomType.DoubleDouble, 488.8, 50, null);
        ResultMessage resultMessage = hotelBLService.updateHotelRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateHotelRoomQuantity1() throws Exception {
        DateUtil start = new DateUtil(2016, 11, 28);
        DateUtil end = new DateUtil(2016, 12, 5);
        int quantity = 20;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        assertEquals(ResultMessage.INSUFFICIENT, resultMessage);
    }

    @Test
    public void updateHotelRoomQuantity2() throws Exception {
        DateUtil start = new DateUtil(2016, 11, 28);
        DateUtil end = new DateUtil(2016, 12, 1);
        int quantity = 20;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        assertEquals(ResultMessage.INSUFFICIENT, resultMessage);
    }

    @Test
    public void addRoom() throws Exception {
        HotelRoomVO hotelRoomVO = new HotelRoomVO("00000010", RoomType.DoubleDouble,320, 10, null);
        ResultMessage resultMessage = hotelBLService.addRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Ignore
    public void deleteHotelRoom() throws Exception {
        ResultMessage resultMessage = hotelBLService.deleteHotelRoom("00000000", RoomType.DoubleDouble);
        assertEquals(ResultMessage.INVALID, resultMessage);
    }

    @Test
    public void setRoomWillBeCancel() throws Exception {

    }

    @Test
    public void isOrdered() throws Exception {
        ResultMessage resultMessage = hotelBLService.isOrdered("00000000", RoomType.DoubleDouble);
        assertEquals(ResultMessage.FALSE,resultMessage);
    }
}