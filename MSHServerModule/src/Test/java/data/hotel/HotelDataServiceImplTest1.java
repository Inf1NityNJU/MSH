package data.hotel;

import dataservice.hoteldataservice.HotelDataService;
import org.junit.Test;
import po.RoomStockPO;
import util.ResultMessage;
import util.RoomType;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/17.
 */
public class HotelDataServiceImplTest1 {
    @Test
    public void addRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        RoomStockPO roomStockPO = new RoomStockPO("0000010101", "000001", RoomType.SingleRoom, 5, "2016-11-17");
        ResultMessage resultMessage = hotelDataService.addRoomStock(roomStockPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        RoomStockPO roomStockPO = new RoomStockPO("0000010101", "000001", RoomType.SingleRoom, 10, "2015-11-17");
        ResultMessage resultMessage = hotelDataService.updateRoomStock(roomStockPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteRoomStock() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        ResultMessage resultMessage = hotelDataService.deleteRoomStock("0000010101");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void getRoomStockPO() throws Exception {
        HotelDataService hotelDataService = HotelDataServiceFactory.getHotelDataService();
        RoomStockPO roomStockPO = hotelDataService.getRoomStock("0000010101");
        assertEquals("0000010101", roomStockPO.getID());
    }

}