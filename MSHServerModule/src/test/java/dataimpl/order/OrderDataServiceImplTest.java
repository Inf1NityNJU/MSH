package dataimpl.order;

import dataimpl.orderdataimpl.OrderDataServiceFactory;
import dataservice.orderdataservice.OrderDataService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sorumi on 16/11/28.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDataServiceImplTest {

    private OrderDataService orderDataService;

    public OrderDataServiceImplTest() {
        orderDataService = OrderDataServiceFactory.getOrderDataService();
    }

    @Test
    public void a_addOrder() {
        ResultMessage resultMessage = orderDataService.addOrder(new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted,
                null, 1, "生日折扣", 1, 300, 280));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void b_updateOrder() {
        ResultMessage resultMessage = orderDataService.updateOrder(new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), new TimeUtil(2016, 10, 12, 13, 0, 0), null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Executed,
                null, 1, "生日折扣", 1, 300, 280));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void c_searchOrder() {
        ArrayList<OrderPO> orders = orderDataService.searchOrder(OrderState.Executed);
        assertEquals(1, orders.size());
    }


    @Test
    public void d_searchOrderByOrderID() {
        OrderPO order = orderDataService.searchOrderByOrderID("20161012010112340000");
        OrderPO exampleOrder = new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), new TimeUtil(2016, 10, 12, 13, 0, 0), null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Executed,
                null, 1, "生日折扣", 1, 300, 280);
        assertEquals(exampleOrder, order);
    }

    @Test
    public void e_searchOrderByClientID() {
        ArrayList<OrderPO> orders = orderDataService.searchOrderByClientID("000000001", null);
        assertEquals(1, orders.size());
    }

    @Test
    public void f_searchOrderByHotelID() {
        ArrayList<OrderPO> orders = orderDataService.searchOrderByHotelID("01011234", null);
        assertEquals(1, orders.size());
    }

    @Test
    public void g_addOrderRoom() {
        ResultMessage resultMessage = orderDataService.addOrderRoom(new OrderRoomPO("201610120101123400000", "20161012010112340000", RoomType.DoubleRoom, 1, 200));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void h_searchOrderRoomByOrderID() {
        ArrayList<OrderRoomPO> rooms = orderDataService.searchOrderRoomByOrderID("20161012010112340000");
        assertEquals(1, rooms.size());
    }

    @Test
    public void i_addAssessment() {
        ResultMessage resultMessage = orderDataService.addAssessment(new AssessmentPO("20161012010112340000", 5, 5, 4, 4, "很好呀", "000000001"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void j_updateAssessment() {
        ResultMessage resultMessage = orderDataService.updateAssessment(new AssessmentPO("20161012010112340000", 5, 5, 4, 4, "下次再来", "000000001"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void k_searchAssessmentByOrderID() {
        AssessmentPO assessment = orderDataService.searchAssessmentByOrderID("20161012010112340000");
        AssessmentPO exampleAssessment = new AssessmentPO("20161012010112340000", 5, 5, 4, 4, "下次再来", "000000001");
        assertEquals(exampleAssessment, assessment);
    }

    @Test
    public void l_searchOrderQuantity() {
        int quantity = orderDataService.searchOrderQuantity("2016101201011234");
        assertEquals(1, quantity);
    }
}
