package hotel;

import bl.hotelbl.Hotel;
import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLInfo;
import blservice.hotelblservice.HotelBLService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import po.HotelPO;
import util.*;
import vo.FilterFlagsVO;
import vo.Hotel_DetailVO;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/18.
 */
public class HotelTest {
    Hotel_DetailVO hotel_detailVO1;
    Hotel_DetailVO hotel_detailVO2;
    Hotel_DetailVO hotel_detailVO3;
    Hotel_DetailVO hotel_detailVO4;
    Hotel_DetailVO hotel_detailVO5;
    private HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();
    private HotelBLInfo hotelBLInfo = HotelBLFactory.getHotelBLService();

    @Before
    public void setUp() {
//        hotel_detailVO1 = hotelBLService.getHotel("00000000");
//        hotel_detailVO2 = hotelBLService.getHotel("00000001");
//        hotel_detailVO3 = hotelBLService.getHotel("00000002");
//        hotel_detailVO4 = hotelBLService.getHotel("00000003");
//        hotel_detailVO5 = hotelBLService.getHotel("00000004");
    }

    @Test
    public void searchHotelByCity() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(City.NanJing, null, null, null, 0, 0, null, null, 0, -1, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void searchHotelByPlace() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(null, Place.XianLin, null, null, 0, 0, null, null, 0, -1, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void searchHotelByName() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(null, null, "te", null, 0, 0, null, null, 0, -1, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void searchHotelByRoom1() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(null, null, null, RoomType.SingleRoom, 0, 0, new DateUtil(2017, 1, 12), new DateUtil(2017, 1, 23), 0, -1, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        assertEquals(null, hotel_detailVOs);
    }

    @Test
    public void searchHotelByRoom2() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(null, null, null, RoomType.SingleRoom, 0, 0, new DateUtil(2016, 11, 29), new DateUtil(2016, 12, 3), 10, -1, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void searchHotelByRoom3() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(null, null, null, RoomType.SingleRoom, 150, 300, new DateUtil(2016, 11, 29), new DateUtil(2016, 12, 3), 10, -1, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void searchHotelByRoom4() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(null, null, null, RoomType.SingleRoom, 150, 300, new DateUtil(2016, 11, 29), new DateUtil(2016, 12, 3), 2, -1, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void searchHotelByStar() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(null, null, null, null, 0, 0, null, null, 0, 5, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void searchHotelByScore() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(null, null, null, null, 0, 0, null, null, 0, -1, 3, 5, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Ignore
    public void searchHotelByClientID() throws Exception {
        //TODO
        FilterFlagsVO flags = new FilterFlagsVO(null, null, null, null, 0, 0, null, null, 0, -1, 3, 5, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void searchHotelInAll() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(City.NanJing, Place.XianLin, "te", RoomType.SingleRoom, 250, 400, new DateUtil(2016, 11, 30), new DateUtil(2016, 12, 5), 2, 4, 3, 5, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void addScore() {
        ResultMessage resultMessage = hotelBLInfo.addScoreToHotelByHotelID(4,"01020000");
        Hotel_DetailVO hotel_detailVO=hotelBLService.getHotel("01020000");
        assertEquals(ResultMessage.SUCCESS,resultMessage);
        System.out.println(hotel_detailVO.score);
    }

    @Test
    public void getHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO = hotelBLService.getHotel("00000000");
        assertEquals("00000000", hotel_detailVO.ID);
    }

    @Test
    public void updateHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO = new Hotel_DetailVO("00000001", "Test hotel 1 update", City.NanJing, "Nanjing Technical University", Place.XianLin, 4, "The test hotel", "All", null, 4.5, 4);
        ResultMessage resultMessage = hotelBLService.updateHotel(hotel_detailVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void addHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO = new Hotel_DetailVO(null, "Han Ting", City.GuangZhou, "Center Park", Place.XianLin, 5, "The test hotel", "All", null, 4.5, 4);
        ResultMessage resultMessage = hotelBLService.addHotel(hotel_detailVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteHotel() throws Exception {
        ResultMessage resultMessage = hotelBLService.deleteHotel("00000006");
        assertEquals(ResultMessage.NOT_EXIST, resultMessage);
    }

    @Ignore
    public void priceAscendingSort() throws Exception {
        ArrayList<Hotel_DetailVO> hotel_detailVOs = new ArrayList<Hotel_DetailVO>();
        hotel_detailVOs.add(hotel_detailVO1);
        hotel_detailVOs.add(hotel_detailVO2);
        hotel_detailVOs.add(hotel_detailVO3);
        Iterator<Hotel_DetailVO> hotel_detailVOIterator = hotelBLService.priceAscendingSort(hotel_detailVOs);
        while (hotel_detailVOIterator.hasNext()) {
            System.out.println(hotel_detailVOIterator.next().minPrice);
        }
    }

    @Ignore
    public void priceDescendingSort() throws Exception {
        ArrayList<Hotel_DetailVO> hotel_detailVOs = new ArrayList<Hotel_DetailVO>();
        hotel_detailVOs.add(hotel_detailVO1);
        hotel_detailVOs.add(hotel_detailVO2);
        hotel_detailVOs.add(hotel_detailVO3);
        Iterator<Hotel_DetailVO> hotel_detailVOIterator = hotelBLService.priceDescendingSort(hotel_detailVOs);
        while (hotel_detailVOIterator.hasNext()) {
            System.out.println(hotel_detailVOIterator.next().maxPrice);
        }
    }

    @Ignore
    public void starAscendingSort() throws Exception {
        ArrayList<Hotel_DetailVO> hotel_detailVOs = new ArrayList<Hotel_DetailVO>();
        hotel_detailVOs.add(hotel_detailVO1);
        hotel_detailVOs.add(hotel_detailVO2);
        hotel_detailVOs.add(hotel_detailVO3);
        hotel_detailVOs.add(hotel_detailVO4);
        hotel_detailVOs.add(hotel_detailVO5);
        Iterator<Hotel_DetailVO> hotel_detailVOIterator = hotelBLService.starAscendingSort(hotel_detailVOs);
        while (hotel_detailVOIterator.hasNext()) {
            System.out.println(hotel_detailVOIterator.next().star);
        }
    }

    @Ignore
    public void starDescendingSort() throws Exception {
        ArrayList<Hotel_DetailVO> hotel_detailVOs = new ArrayList<Hotel_DetailVO>();
        hotel_detailVOs.add(hotel_detailVO1);
        hotel_detailVOs.add(hotel_detailVO2);
        hotel_detailVOs.add(hotel_detailVO3);
        hotel_detailVOs.add(hotel_detailVO4);
        hotel_detailVOs.add(hotel_detailVO5);
        Iterator<Hotel_DetailVO> hotel_detailVOIterator = hotelBLService.starDescendingSort(hotel_detailVOs);
        while (hotel_detailVOIterator.hasNext()) {
            System.out.println(hotel_detailVOIterator.next().star);
        }
    }

    @Ignore
    public void scoreAscendingSort() throws Exception {

    }

    @Ignore
    public void scoreDescendingSort() throws Exception {

    }
}