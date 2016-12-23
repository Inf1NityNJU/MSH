package hotel;

import blimpl.hotelblimpl.HotelBLFactory;
import blservice.hotelblservice.HotelBLInfo;
import blservice.hotelblservice.HotelBLService;
import org.junit.Ignore;
import org.junit.Test;
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
public class HotelRoomTest {
    private HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();
    private HotelBLInfo hotelBLInfo = HotelBLFactory.getHotelBLInfo();

    @Test
    public void getRoom() throws Exception {
        ArrayList<HotelRoomVO> hotelRoomVOs = hotelBLService.getRoom("00000000");
        assertEquals(2, hotelRoomVOs.size());
        assertEquals(5, hotelRoomVOs.get(1).roomStockVOs.get(0).availableQuantity);
    }

    @Ignore
    public void updateHotelRoom() throws Exception {
        HotelRoomVO hotelRoomVO = new HotelRoomVO("00000002", RoomType.DoubleDouble, 488.8, 3, null);
        ResultMessage resultMessage = hotelBLService.updateHotelRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateHotelRoomQuantity1() throws Exception {
        DateUtil start = new DateUtil(2016, 12, 4);
        DateUtil end = new DateUtil(2016, 12, 20);
        int quantity = 3;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateHotelRoomQuantity2() throws Exception {
        DateUtil start = new DateUtil(2016, 12, 4);
        DateUtil end = new DateUtil(2016, 12, 20);
        int quantity = -3;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateHotelRoomQuantity3() throws Exception {
        DateUtil start = new DateUtil(2016, 12, 4);
        DateUtil end = new DateUtil(2016, 12, 20);
        int quantity = -10;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        ResultMessage resultMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        assertEquals(ResultMessage.INVALID, resultMessage);
    }


    @Ignore
    public void addRoom() throws Exception {
        HotelRoomVO hotelRoomVO = new HotelRoomVO("00000005", RoomType.SingleRoom, 99, 5, null);
        ResultMessage resultMessage = hotelBLService.addRoom(hotelRoomVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteHotelRoom() throws Exception {
        ResultMessage resultMessage = hotelBLService.deleteHotelRoom("00000000", RoomType.BusinessRoom);
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Test
    public void setRoomWillBeCancel() throws Exception {

    }

    @Test
    public void isOrdered() throws Exception {
        ResultMessage resultMessage = hotelBLService.isOrdered("00000000", RoomType.DoubleDouble);
        assertEquals(ResultMessage.FALSE, resultMessage);
    }

    @Test
    public void getAvailableQuantity() {
        int quantity = hotelBLInfo.getAvailableQuantity(new DateUtil(2016, 12, 5), new DateUtil(2016, 12, 20), "00000000", RoomType.SingleRoom);
        System.out.println(quantity);
    }

    @Test
    public void getOrderRoomStockVO() {
        ArrayList<OrderRoomStockVO> orderRoomStockVOs = hotelBLService.getRoomStocks(new DateUtil(2016, 12, 5), new DateUtil(2016, 12, 20), "00000000");
        System.out.println(orderRoomStockVOs.get(1).orderRoom.price);
        assertEquals(2, orderRoomStockVOs.size());
    }
}