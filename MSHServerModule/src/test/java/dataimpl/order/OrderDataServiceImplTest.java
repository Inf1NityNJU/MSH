package dataimpl.order;

import dataimpl.orderdataimpl.OrderDataServiceFactory;
import dataservice.orderdataservice.OrderDataService;
import org.junit.Test;
import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sorumi on 16/11/28.
 */
public class OrderDataServiceImplTest {

    private OrderDataService orderDataService;

    public OrderDataServiceImplTest() {
        orderDataService = OrderDataServiceFactory.getOrderDataService();
    }

    @Test
    public void addOrder() {
        ResultMessage resultMessage = orderDataService.addOrder(new OrderPO("20161012010112340003", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted,
                null, 1, "生日折扣", 1, 300, 280));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateOrder() {
        ResultMessage resultMessage = orderDataService.updateOrder(new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Executed,
                null, 1, "优惠折扣", 1, 300, 260));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchOrder() {
        ArrayList<OrderPO> orders = orderDataService.searchOrder(null);
        assertEquals(5, orders.size());
    }


    @Test
    public void searchOrderByOrderID() {
        OrderPO order = orderDataService.searchOrderByOrderID("20161012010112340000");
        OrderPO exampleOrder = new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 10, 14, 0, 0), null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Executed,
                null, 1, "生日折扣", 1, 300, 260);
        assertEquals(exampleOrder, order);
    }

    @Test
    public void searchOrderByClientID() {
        ArrayList<OrderPO> orders = orderDataService.searchOrderByClientID("000101101", null);
        assertEquals(4, orders.size());
    }

    @Test
    public void searchOrderByHotelID() {
        ArrayList<OrderPO> orders = orderDataService.searchOrderByHotelID("00000001", null);
        assertEquals(7, orders.size());
    }

    @Test
    public void addOrderRoom() {
        ResultMessage resultMessage = orderDataService.addOrderRoom(new OrderRoomPO("201610120101123400000", "20161012010112340000", RoomType.DoubleRoom, 1, 200));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchOrderRoomByOrderID() {
        ArrayList<OrderRoomPO> rooms = orderDataService.searchOrderRoomByOrderID("20161209000000010000");
        assertEquals(2, rooms.size());
    }

    @Test
    public void addAssessment() {
        ResultMessage resultMessage = orderDataService.addAssessment(new AssessmentPO("20161012010112340001", 5, 5, 4, 4, "很好呀", "000000001"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void updateAssessment() {
        ResultMessage resultMessage = orderDataService.updateAssessment(new AssessmentPO("20161012010112340000", 5, 5, 4, 4, "下次再来", "000000001"));
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void searchAssessmentByOrderID() {
        AssessmentPO assessment = orderDataService.searchAssessmentByOrderID("2016101201011234000");
        AssessmentPO exampleAssessment = new AssessmentPO("20161012010112340000", 5, 5, 4, 4, "下次再来", "000000001");
        assertEquals(exampleAssessment, assessment);
    }

    @Test
    public void searchAssessmentByOrderID2() {
        AssessmentPO assessment = orderDataService.searchAssessmentByOrderID("2016101201011234006");
//        AssessmentPO exampleAssessment = new AssessmentPO("20161012010112340000", 5, 5, 4, 4, "下次再来", "000000001");
        assertEquals(null, assessment);
    }

    @Test
    public void searchOrderQuantity() {
        int quantity = orderDataService.searchOrderQuantity("2016101201011234");
        assertEquals(5, quantity);
    }
}
