import bl.hotelbl.Hotel;
import bl.hotelbl.MockHotel;
import org.junit.Test;
import util.HotelNotFoundException;
import util.InfoInvalidException;
import util.ResultMessage;
import util.RoomType;
import vo.FilterFlagsVO;
import vo.HotelRoomVO;
import vo.Hotel_DetailVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 16/11/5.
 */
public class HotelTest {
    private Hotel hotel;
    //Test constant
    private static final String testHotelID = "000000";
    private static final FilterFlagsVO TEST_FILTER_FLAGS_VO = new FilterFlagsVO(null, null, null, 0, 0, null, null, 0, 0, 0, 0, null);
    private static final Hotel_DetailVO TEST_HOTEL_DETIAL_VO = new Hotel_DetailVO("000000", null, null, null, 0, null, null, null);
    private static final HotelRoomVO testHotelRoomVO = new HotelRoomVO(null, 0, 0, null);
    private static final RoomType testType = RoomType.SingleRoom;


    public HotelTest() {
        hotel = new MockHotel();
    }
    @Test
    public void testSearchHotel() {
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotel.searchHotel(TEST_FILTER_FLAGS_VO);
        assertFalse(hotel_detailVOs.isEmpty());
    }

    @Test
    public void testGetHotel() {
        try {
            Hotel_DetailVO hotel_detailVO = hotel.getHotel(testHotelID);
            assertNotNull(hotel_detailVO);
        } catch (HotelNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAddHotel() {
        try {
            ResultMessage resultMessage = hotel.addHotel(TEST_HOTEL_DETIAL_VO);
            assertEquals(resultMessage, ResultMessage.SUCCESS);
        } catch (InfoInvalidException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testUpdateHotelRoomInfo() {
        try {
            ResultMessage resultMessage = hotel.updateHotelInfo(TEST_HOTEL_DETIAL_VO);
            assertEquals(resultMessage, ResultMessage.SUCCESS);
        } catch (InfoInvalidException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDeleteHotelRoom() {
        try {
            ResultMessage resultMessage = hotel.deleteHotel(testHotelID);
            assertEquals(resultMessage, ResultMessage.SUCCESS);
        } catch (HotelNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
}
