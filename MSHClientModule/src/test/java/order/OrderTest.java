package order;

import blimpl.orderblimpl.Order;
import blimpl.orderblimpl.OrderBLFactory;
import util.*;
import vo.*;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Sorumi on 16/11/3.
 */
public class OrderTest {

    private Order order;

    public OrderTest() {
        order = OrderBLFactory.getOrder();
    }

    private void addOrder() {
        OrderVO orderVO = new OrderVO("00000001", new DateUtil(2016,12,14), new DateUtil(2016,12,16));
        OrderRoomVO orderRoomVO = new OrderRoomVO(RoomType.DoubleRoom, 2, 200);
        orderVO.rooms = new ArrayList<>();
        orderVO.rooms.add(orderRoomVO);
        orderVO.clientID = "000000001";
        order.startOrder(orderVO);
    }
    @Test
    public void testStartOrder() {
        OrderVO orderVO = new OrderVO("00000001", new DateUtil(2016,12,14), new DateUtil(2016,12,16));
        orderVO.rooms = new ArrayList<>();
        ResultMessage rm = order.startOrder(orderVO);
        assertEquals(ResultMessage.SUCCESS, rm);
    }


    @Test
    public void testModifyRoomQuantity() {
        this.addOrder();

        ResultMessage rm = order.modifyRoomQuantity(RoomType.DoubleRoom, 1);
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = order.modifyRoomQuantity(RoomType.SingleRoom, 1);
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testGetBill() {
        this.addOrder();

        BillVO bill = order.getBill();
        assertNotNull(bill);
    }

    @Test
    public void testGenerate() {
        this.addOrder();

        ResultMessage rm = order.generate(new TimeUtil(2016, 10, 29, 18, 0, 0), 3, true);
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testRevoke() {
        ResultMessage rm = order.revoke("20161026010112340000");
        assertEquals(ResultMessage.FAILED, rm);
        rm = order.revoke("20161026010112341111");
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testCheckIn() {
        ResultMessage rm = order.checkIn("20161026010112340000", new TimeUtil(2016, 10, 26, 18, 0, 0));
        assertEquals(ResultMessage.FAILED, rm);
        rm = order.checkIn("20161026010112341111", new TimeUtil(2016, 10, 26, 18, 0, 0));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testCheckOut() {
        ResultMessage rm = order.checkOut("20161026010112340000", new TimeUtil(2016, 10, 28, 10, 0, 0));
        assertEquals(ResultMessage.FAILED, rm);
        rm = order.checkOut("20161026010112341111", new TimeUtil(2016, 10, 28, 10, 0, 0));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testEditAssessment() {
        ResultMessage rm = order.editAssessment("20161026010112340000", new AssessmentVO(5, 5, 5, 5, "很干净很舒服"));
        assertEquals(ResultMessage.FAILED, rm);
        rm = order.editAssessment("20161026010112341111", new AssessmentVO(5, 5, 4, 5, "很干净很舒服"));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testSearchOrderByID() {
        OrderVO orderVO = order.searchOrderByID("20161026010112340000");
        assertNotNull(orderVO);
    }

    @Test
    public void testSearchOrder() {
        ArrayList<OrderVO> orders = order.searchOrder(OrderState.Abnormal);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testSearchClientOrder() {
        ArrayList<OrderVO> orders = order.searchClientOrder("000000001", OrderState.Unexecuted);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testSearchHotelOrder() {
        ArrayList<OrderVO> orders = order.searchHotelOrder("00000001", null);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testGetBookedHotelIDByClientID() {
        ArrayList<String> hotels = order.getBookedHotelIDByClientID("000000001");
        assertEquals(3, hotels.size());
    }

    @Test
    public void setGetAssessmentByHotelID() {
        ArrayList<Assessment_HotelVO> assessment_hotelVOs = order.getAssessmentByHotelID("00000000");
        assertEquals(1, assessment_hotelVOs.size());
    }
}