package hotel;

import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLService;
import org.junit.Test;
import util.Place;
import util.ResultMessage;
import vo.Hotel_DetailVO;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/18.
 */
public class HotelTest {
    private HotelBLService hotelBLService= HotelBLFactory.getHotelBLService();

    @Test
    public void searchHotel() throws Exception {

    }

    @Test
    public void getHotel() throws Exception {

    }

    @Test
    public void updateHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO=new Hotel_DetailVO("00000006", "Test hotel 6 update", "Nanjing Technical University", Place.XIANLIN, 4,"The test hotel", "All",null);
        ResultMessage resultMessage=hotelBLService.updateHotel(hotel_detailVO);
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

    @Test
    public void addHotel() throws Exception {
        Hotel_DetailVO hotel_detailVO=new Hotel_DetailVO("00000006", "Test hotel 6", "Nanjing Technical University", Place.XIANLIN, 4,"The test hotel", "All",null);
        ResultMessage resultMessage=hotelBLService.addHotel(hotel_detailVO);
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

    @Test
    public void deleteHotel() throws Exception {
        ResultMessage resultMessage=hotelBLService.deleteHotel("00000006");
        assertEquals(ResultMessage.SUCCESS,resultMessage);
    }

}