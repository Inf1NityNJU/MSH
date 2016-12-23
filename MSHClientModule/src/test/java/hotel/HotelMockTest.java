package hotel;

import blimpl.hotelblimpl.Hotel;
import blimpl.hotelblimpl.MockHotel;
import org.junit.Test;
import util.City;
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
public class HotelMockTest {
    //Test constant
    private static final String testHotelID = "000000";
    private static final FilterFlagsVO TEST_FILTER_FLAGS_VO = new FilterFlagsVO(null, null, null, null, 0, 0, null, null, 0, 0, 0, 0, null);
    private static final Hotel_DetailVO TEST_HOTEL_DETAIL_VO = new Hotel_DetailVO("00000000", null, City.NanJing, null, null, 0, null, null, null, 0, 0);
    private static final HotelRoomVO testHotelRoomVO = new HotelRoomVO("0000000000", null, 0, 0, null);
    private static final RoomType testType = RoomType.SingleRoom;
    private Hotel hotel;


    public HotelMockTest() {
        hotel = new MockHotel();
    }

    @Test
    public void testSearchHotel() {
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotel.searchHotel(TEST_FILTER_FLAGS_VO);
        assertFalse(hotel_detailVOs.isEmpty());
    }

    @Test
    public void testGetHotel() {
        Hotel_DetailVO hotel_detailVO = hotel.getHotel(testHotelID);
        assertNotNull(hotel_detailVO);
    }

    @Test
    public void testAddHotel() {
        ResultMessage resultMessage = hotel.addHotel(TEST_HOTEL_DETAIL_VO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void testUpdateHotelInfo() {
        ResultMessage resultMessage = hotel.updateHotel(TEST_HOTEL_DETAIL_VO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void testDeleteHotelRoom() {
        ResultMessage resultMessage = hotel.deleteHotel(testHotelID);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

}
