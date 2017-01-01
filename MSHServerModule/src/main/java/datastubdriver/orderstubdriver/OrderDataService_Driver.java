package datastubdriver.orderstubdriver;

import dataimpl.orderdataimpl.OrderDataServiceFactory;
import dataservice.orderdataservice.OrderDataService;
import org.junit.Test;
import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/13.
 */
public class OrderDataService_Driver {

    @Test
    public void test() {
        OrderDataService orderDataService = OrderDataServiceFactory.getOrderDataService();
        driver(orderDataService);
    }

    public void driver(OrderDataService orderDataService) {

        OrderPO orderPO = new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                new TimeUtil(2016, 10, 11, 14, 0, 0), null, new TimeUtil(2016, 10, 12, 14, 0, 0), 2, false, OrderState.Unexecuted, null, 1, null, 1, 300, 280);

        ResultMessage rm = orderDataService.addOrder(orderPO);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Add Order Success");
        } else {
            System.out.println("Add Order Failed");
        }

        rm = orderDataService.updateOrder( new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), new TimeUtil(2016, 10, 12, 13, 0, 0), null,
                new TimeUtil(2016, 10, 11, 14, 0, 0), null, new TimeUtil(2016, 10, 12, 14, 0, 0), 2, false, OrderState.Executed, null, 1, null, 1, 300, 280));
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Update Order Success");
        } else {
            System.out.println("Update Order Failed");
        }

        rm = orderDataService.addOrderRoom(new OrderRoomPO("201610120101123400000", "20161012010112340000", RoomType.DoubleRoom, 1, 200));

        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Add Room Success");
        } else {
            System.out.println("Add Room Failed");
        }


        OrderPO order = orderDataService.searchOrderByOrderID("20161012010112340000");
        if (order != null) {
            System.out.println("Search Success");
        } else {
            System.out.println("Search Failed");
        }

        ArrayList<OrderPO> orders = orderDataService.searchOrder(OrderState.Executed);
        if (orders.size() > 0) {
            System.out.println("Search Success");
        } else {
            System.out.println("Search Failed");
        }

        orders = orderDataService.searchOrderByClientID("000000001", null);
        if (orders.size() > 0) {
            System.out.println("Search Success");
        } else {
            System.out.println("Search Failed");
        }

        orders = orderDataService.searchOrderByHotelID("01011234", null);
        if (orders.size() > 0) {
            System.out.println("Search Success");
        } else {
            System.out.println("Search Failed");
        }

        ArrayList<OrderRoomPO> orderRooms = orderDataService.searchOrderRoomByOrderID("20161012010112340000");
        if (orderRooms.size() > 0) {
            System.out.println("Search Success");
        } else {
            System.out.println("Search Failed");
        }

        rm = orderDataService.addAssessment(new AssessmentPO("20161012010112340000", 5, 5, 4, 4, "好的好的", "000000001"));
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Add Assessment Success");
        } else {
            System.out.println("Add Assessment Failed");
        }

        rm = orderDataService.updateAssessment(new AssessmentPO("20161012010112340000", 5, 5, 5, 5, "五星好评", "000000001"));
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Update Assessment Success");
        } else {
            System.out.println("Update Assessment Failed");
        }

        AssessmentPO assessment = orderDataService.searchAssessmentByOrderID("20161012010112340000");
        if (assessment != null) {
            System.out.println("Search Success");
        } else {
            System.out.println("Search Failed");
        }

        int num = orderDataService.searchOrderQuantity("2016101201011234");
        if (num > 0) {
            System.out.println("Search Success");
        } else {
            System.out.println("Search Failed");
        }
    }
}
