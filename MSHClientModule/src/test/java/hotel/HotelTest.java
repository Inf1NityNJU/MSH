package hotel;

import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import org.junit.Test;
import util.Place;
import util.ResultMessage;
import vo.Hotel_DetailVO;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/18.
 */
public class HotelTest {
    private HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();

    @Test
    public void searchHotel() throws Exception {

    }

    @Test
    public void getHotel() throws Exception {

    }

    @Test
    public void updateHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO = new Hotel_DetailVO("00000006", "Test hotel 6 update", "Nanjing Technical University", Place.XIANLIN, 4, "The test hotel", "All", null);
        ResultMessage resultMessage = hotelBLService.updateHotel(hotel_detailVO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void addHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO = new Hotel_DetailVO("00000006", "Test hotel 6", "Nanjing Technical University", Place.XIANLIN, 4, "The test hotel", "All", null);
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
        Hotel_DetailVO hotel_detailVO1 = hotelBLService.getHotel("00000000");
        Hotel_DetailVO hotel_detailVO2 = hotelBLService.getHotel("00000001");
        Hotel_DetailVO hotel_detailVO3 = hotelBLService.getHotel("00000002");
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

    }

    @Test
    public void starAscendingSort() throws Exception {

    }

    @Test
    public void starDescendingSort() throws Exception {

    }

    @Test
    public void scoreAscendingSort() throws Exception {

    }

    @Test
    public void scoreDescendingSort() throws Exception {

    }
}