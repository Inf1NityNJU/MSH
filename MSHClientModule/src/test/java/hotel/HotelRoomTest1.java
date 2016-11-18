package hotel;

import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import org.junit.Test;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/18.
 */
public class HotelRoomTest1 {
    @Test
    public void getRoom() throws Exception {

    }

    @Test
    public void updateHotelRoom() throws Exception {

    }

    @Test
    public void updateHotelRoomQuantity() throws Exception {

    }

    @Test
    public void addRoom() throws Exception {
        HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();
        HotelRoomVO hotelRoomVO = new HotelRoomVO("00000001", RoomType.SingleRoom, 288.8, 10, null);
        ResultMessage resultMessage = hotelBLService.addRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteHotelRoom() throws Exception {

    }

}