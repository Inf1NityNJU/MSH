package hotel;

import bl.hotelbl.HotelRoom;
import bl.hotelbl.MockHotelRoom;
import org.junit.Test;
import util.HotelNotFoundException;
import util.InfoInvalidException;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 16/11/5.
 */
public class HotelRoomTest {
    private HotelRoom hotelRoom;
    RoomType type = RoomType.DoubleDouble;
    RoomType type2;
    //Test constant
    private static final String testHotelID = "000000";
    private static final HotelRoomVO testHotelRoomVO = new HotelRoomVO(null, 0, 0, null);
    private static final RoomType testType = RoomType.SingleRoom;


    public HotelRoomTest() {
        hotelRoom = new MockHotelRoom();
    }

    @Test
    public void testGetHotelRoom() {
        ArrayList<HotelRoomVO> hotelRoomVOs = hotelRoom.getRoom(testHotelID);
        assertFalse(hotelRoomVOs.isEmpty());
    }

    @Test
    public void testAddHotelRoom() {
        try {
            ResultMessage resultMessage = hotelRoom.addRoom(testHotelRoomVO);
            assertEquals(ResultMessage.SUCCESS, resultMessage);
        } catch (InfoInvalidException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testUpdateHotelRoomInfo() {
        try {
            ResultMessage resultMessage = hotelRoom.updateHotelRoom(testHotelRoomVO);
            assertEquals(ResultMessage.SUCCESS, resultMessage);
        } catch (InfoInvalidException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDeleteHotelRoom() {
        try {
            ResultMessage resultMessage = hotelRoom.deleteHotelRoom(testHotelID, testType);
            assertEquals(ResultMessage.SUCCESS, resultMessage);
        } catch (HotelNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
}
