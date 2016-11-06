import bl.hotelbl.HotelBLFactory;
import bl.hotelbl.HotelBLServiceImpl;
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
public class HotelRoomTest {
    private HotelBLServiceImpl hotelBLServiceImpl;
    //Test constant
    private static final String testHotelID = "000000";
    private static final FilterFlagsVO TEST_FILTER_FLAGS_VO = new FilterFlagsVO(null,null,null,0,0,null,null,0,0,0,0,null);
    private static final Hotel_DetailVO TEST_HOTEL_DETIAL_VO = new Hotel_DetailVO("000000",null,null,null,0,null,null,null);
    private static final HotelRoomVO testHotelRoomVO = new HotelRoomVO(null,0,0,null);
    private static final RoomType testType = RoomType.SingleRoom;


    public HotelRoomTest(){
        hotelBLServiceImpl = HotelBLFactory.getMockHotelBLServiceImpl();
    }

    @Test
    public void testGetHotelRoom() {
        ArrayList<HotelRoomVO> hotelRoomVOs = hotelBLServiceImpl.getRoom(testHotelID);
        assertFalse(hotelRoomVOs.isEmpty());
    }

    @Test
    public void testAddHotelRoom() {
        try {
            ResultMessage resultMessage = hotelBLServiceImpl.addRoom(testHotelRoomVO);
            assertEquals(resultMessage, ResultMessage.SUCCESS);
        } catch (InfoInvalidException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testUpdateHotelRoomInfo() {
        try {
            ResultMessage resultMessage = hotelBLServiceImpl.updateHotelRoomInfo(testHotelRoomVO);
            assertEquals(resultMessage, ResultMessage.SUCCESS);
        } catch (InfoInvalidException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDeleteHotelRoom() {
        try {
            ResultMessage resultMessage = hotelBLServiceImpl.deleteHotelRoom(testHotelID,testType);
            assertEquals(resultMessage, ResultMessage.SUCCESS);
        } catch (HotelNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
}
