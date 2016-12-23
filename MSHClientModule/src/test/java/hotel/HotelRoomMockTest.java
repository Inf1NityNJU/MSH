package hotel;

import blimpl.hotelblimpl.HotelRoom;
import blimpl.hotelblimpl.MockHotelRoom;
import org.junit.Test;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 16/11/5.
 */
public class HotelRoomMockTest {
    private HotelRoom hotelRoom;
    RoomType type = RoomType.DoubleDouble;
    RoomType type2;
    //Test constant
    private static final String testHotelID = "000000";
    private static final HotelRoomVO testHotelRoomVO = new HotelRoomVO("00000000",null, 0, 0, null);
    private static final RoomType testType = RoomType.SingleRoom;


    public HotelRoomMockTest() {
        hotelRoom = new MockHotelRoom();
    }

    @Test
    public void testGetHotelRoom() {
        ArrayList<HotelRoomVO> hotelRoomVOs = hotelRoom.getRoom(testHotelID);
        assertFalse(hotelRoomVOs.isEmpty());
    }

    @Test
    public void testAddHotelRoom() {
        ResultMessage resultMessage = hotelRoom.addRoom(testHotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void testUpdateHotelRoomInfo() {
        ResultMessage resultMessage = hotelRoom.updateHotelRoom(testHotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void testDeleteHotelRoom() {
        ResultMessage resultMessage = hotelRoom.deleteHotelRoom(testHotelID, testType);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

}
