package hotel;

import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import org.junit.Before;
import org.junit.Test;
import util.City;
import util.Place;
import util.ResultMessage;
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

    @Before
    public void setUp() {
//        hotel_detailVO1 = hotelBLService.getHotel("00000000");
//        hotel_detailVO2 = hotelBLService.getHotel("00000001");
//        hotel_detailVO3 = hotelBLService.getHotel("00000002");
//        hotel_detailVO4 = hotelBLService.getHotel("00000003");
//        hotel_detailVO5 = hotelBLService.getHotel("00000004");
    }

    @Test
    public void searchHotel() throws Exception {
        FilterFlagsVO flags = new FilterFlagsVO(City.NanJing, null, null, null, 0, 0, null, null, 0, -1, 0, 0, null);
        ArrayList<Hotel_DetailVO> hotel_detailVOs = hotelBLService.searchHotel(flags);
        for (Hotel_DetailVO hotel_detailVO : hotel_detailVOs) {
            System.out.println(hotel_detailVO);
        }
    }

    @Test
    public void getHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO = hotelBLService.getHotel("00000000");
        assertEquals("00000000", hotel_detailVO.ID);
    }

    @Test
    public void updateHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO = new Hotel_DetailVO("00000006", "Test hotel 6 update", City.NanJing, "Nanjing Technical University", Place.XianLin, 4, "The test hotel", "All", null, 4.5, 4);
        ResultMessage resultMessage = hotelBLService.updateHotel(hotel_detailVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void addHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO = new Hotel_DetailVO(null, "Test hotel 1", City.BeiJing, "Nanjing Technical University", Place.XinJieKou, 4, "The test hotel", "All", null, 4.5, 4);
        ResultMessage resultMessage = hotelBLService.addHotel(hotel_detailVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void deleteHotel() throws Exception {
        ResultMessage resultMessage = hotelBLService.deleteHotel("00000006");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void scoreAscendingSort() throws Exception {

    }

    @Test
    public void scoreDescendingSort() throws Exception {

    }
}