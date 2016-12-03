package order;

import bl.orderbl.MockOrder;
import bl.orderbl.Order;
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
        order = new MockOrder();
    }

    @Test
    public void testModifyRoomQuantity() {
        ResultMessage rm = order.modifyRoomQuantity(RoomType.DoubleRoom, 1);
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = order.modifyRoomQuantity(RoomType.SingleRoom, 1);
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testGetBill() {
        BillVO bill = order.getBill("01011234", "000000001", new DateUtil(2016, 10, 27), new DateUtil(2016, 10, 29), new DateUtil(2016, 10, 30), 3);
        assertNotNull(bill);
    }

    @Test
    public void testGenerate() {
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
        ArrayList<OrderVO> orders = order.searchOrder(OrderState.Abnormal, null);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testSearchClientOrder() {
        ArrayList<OrderVO> orders = order.searchClientOrder("000000001", OrderState.Unexecuted, null);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testSearchHotelOrder() {
        ArrayList<OrderVO> orders = order.searchHotelOrder("01011234", OrderState.Executed, null);
        assertFalse(orders.isEmpty());
    }

}