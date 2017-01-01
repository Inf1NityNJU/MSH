package order;

import blimpl.orderblimpl.Order;
import blimpl.orderblimpl.OrderBLFactory;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import util.*;
import vo.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Sorumi on 16/11/3.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTest {

    private Order order;
    private String orderID = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "000000010000";

    public OrderTest() {
        order = OrderBLFactory.getOrderForTest();
    }

    private void addOrder() {
        OrderVO orderVO = new OrderVO("00000001", new DateUtil(2016,12,14), new DateUtil(2016,12,16));
        OrderRoomVO orderRoomVO = new OrderRoomVO(RoomType.DoubleRoom, 2, 200);
        orderVO.rooms = new ArrayList<>();
        orderVO.rooms.add(orderRoomVO);
        orderVO.clientID = "000000007";
        order.startOrder(orderVO);
    }
    @Test
    public void a_testStartOrder() {
        OrderVO orderVO = new OrderVO("00000001", new DateUtil(2016,12,14), new DateUtil(2016,12,16));
        orderVO.rooms = new ArrayList<>();
        ResultMessage rm = order.startOrder(orderVO);
        assertEquals(ResultMessage.SUCCESS, rm);
    }


    @Test
    public void b_testModifyRoomQuantity() {
        this.addOrder();

        ResultMessage rm = order.modifyRoomQuantity(RoomType.DoubleRoom, 1);
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = order.modifyRoomQuantity(RoomType.SingleRoom, 1);
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void c_testGetBill() {
        this.addOrder();


        BillVO bill = order.getBill();
        assertNotNull(bill);
    }

    @Test
    public void d_testGenerate() {
        this.addOrder();
        order.getBill();

        ResultMessage rm = order.generate(new TimeUtil(2016, 10, 29, 18, 0, 0), 3, true);
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void e_testRevoke() {
        ResultMessage rm = order.revoke(orderID);
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void f_testCheckIn() {
        ResultMessage rm = order.checkIn(orderID, new TimeUtil(2016, 10, 26, 18, 0, 0));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void g_testCheckOut() {
        ResultMessage rm = order.checkOut(orderID, new TimeUtil(2016, 10, 28, 10, 0, 0));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void h_testEditAssessment() {
        ResultMessage rm = order.editAssessment(orderID, new AssessmentVO(5, 5, 4, 5, "很干净很舒服"));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void i_testSearchOrderByID() {
        OrderVO orderVO = order.searchOrderByID(orderID);
        assertNotNull(orderVO);
    }

    @Test
    public void j_testSearchOrder() {
        ArrayList<OrderVO> orders = order.searchOrder(OrderState.Executed);
        assertEquals(1, orders.size());
    }

    @Test
    public void k_testSearchClientOrder() {
        ArrayList<OrderVO> orders = order.searchClientOrder("000000007", null);
        assertEquals(1, orders.size());
    }

    @Test
    public void l_testSearchHotelOrder() {
        ArrayList<OrderVO> orders = order.searchHotelOrder("00000001", null);
        assertEquals(1, orders.size());
    }

    @Test
    public void m_testGetBookedHotelIDByClientID() {
        ArrayList<String> hotels = order.getBookedHotelIDByClientID("000000007");
        assertEquals(1, hotels.size());
    }

    @Test
    public void n_setGetAssessmentByHotelID() {
        ArrayList<Assessment_HotelVO> assessment_hotelVOs = order.getAssessmentByHotelID("00000001");
        assertEquals(1, assessment_hotelVOs.size());
    }


//    @Test
//    public void testSearchClientHotelOrder() {
//        order = OrderBLFactory.getOrder();
//        ArrayList<OrderVO> orderVOs = order.searchClientHotelOrder("000000001", "00000001");
//        assertEquals(1, orderVOs.size());
//    }
}