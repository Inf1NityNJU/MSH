package hotel;

import bl.hotelbl.HotelBLFactory;
import bl.hotelbl.HotelRoom;
import blservice.hotelblservice.HotelBLService;
import org.junit.Test;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/18.
 */
public class HotelRoomTest {
    private HotelBLService hotelBLService=HotelBLFactory.getHotelBLService();

    @Test
    public void getRoom() throws Exception {
        ArrayList<HotelRoomVO>hotelRoomVOs=hotelBLService.getRoom("00000001");
        assertEquals(2,hotelRoomVOs.size());
        assertEquals(10,hotelRoomVOs.get(0).roomStockVOs.get(0).availableQuantity);
    }

    @Test
    public void updateHotelRoom() throws Exception {
        HotelRoomVO hotelRoomVO=new HotelRoomVO("00000001", RoomType.DoubleRoom,488.8,50,null);
        ResultMessage resultMessage=hotelBLService.updateHotelRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

    @Test
    public void updateHotelRoomQuantity() throws Exception {

    }

    @Test
    public void addRoom() throws Exception {
        HotelRoomVO hotelRoomVO=new HotelRoomVO("00000001", RoomType.SingleRoom,288.8,10,null);
        ResultMessage resultMessage=hotelBLService.addRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

    @Test
    public void deleteHotelRoom() throws Exception {
        ResultMessage resultMessage=hotelBLService.deleteHotelRoom("00000001",RoomType.SingleRoom);
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

    @Test
    public void setRoomWillBeCancel() throws Exception {

    }

    @Test
    public void isOrdered() throws Exception {

    }

}