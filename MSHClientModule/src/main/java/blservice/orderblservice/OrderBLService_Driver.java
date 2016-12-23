package blservice.orderblservice;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.orderblimpl.OrderBLFactory;
import org.junit.Test;
import util.*;
import vo.AssessmentVO;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/12.
 */
public class OrderBLService_Driver {

    @Test
    public void test() {
        OrderBLService orderBLService = OrderBLFactory.getOrderBLServiceImplForTest();
        driver(orderBLService);
    }

    public void driver(OrderBLService orderBLService) {
        ArrayList<OrderVO> orderVOs = new ArrayList<OrderVO>();

        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.SingleRoom, 1, 300);
        rooms.add(room1);

        ResultMessage rm;

        OrderVO orderVO = new OrderVO("00000001", new DateUtil(2016, 12, 23), new DateUtil(2016, 12, 24));
        orderVO.bookedTime = new TimeUtil(2016, 12, 23, 10, 0, 0);
        orderVO.rooms = rooms;

        rm = orderBLService.startOrder(orderVO);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Start Success");
        } else {
            System.out.println("Start Failed");
        }
//        orderVO.clientID = "000000001";

        rm = orderBLService.modifyRoomQuantity(RoomType.SingleRoom, 1);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Modify Success");
        } else {
            System.out.println("Modify Failed");
        }

        BillVO bill = orderBLService.getBill();
        if (bill != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        rm = orderBLService.generateOrder(new TimeUtil(2016, 12, 23, 18, 0, 0), 3, false);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Generate Success");
        } else {
            System.out.println("Generate Failed");
        }

        rm = orderBLService.revokeOrder("20161223000000010000");
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Revoke Success");
        } else {
            System.out.println("Revoke Failed");
        }

        rm = orderBLService.checkInOrder("20161223000000010000", new TimeUtil(2016, 10, 17, 18, 0, 0));
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("CheckIn Success");
        } else {
            System.out.println("CheckIn Failed");
        }

        rm = orderBLService.checkOutOrder("20161223000000010000", new TimeUtil(2016, 10, 18, 18, 0, 0));
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("CheckOut Success");
        } else {
            System.out.println("CheckOut Failed");
        }

        rm = orderBLService.editOrderAssessment("20161223000000010000", new AssessmentVO(5, 5, 5, 5, "很好很舒适，下次再来"));
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("Edit Success");
        } else {
            System.out.println("Edit Failed");
        }

        OrderVO order = orderBLService.searchOrderByID("20161223000000010000");
        if (order != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        ArrayList<OrderVO> orders = orderBLService.searchOrder(null);
        if (orders != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        orders = orderBLService.searchOrder(OrderState.Unexecuted);
        if (orders != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        orders = orderBLService.searchOrder(OrderState.Executed);
        if (orders != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        orders = orderBLService.searchOrder(OrderState.Abnormal);
        if (orders != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        orders = orderBLService.searchOrder(OrderState.Cancelled);
        if (orders != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        orders = orderBLService.searchClientOrder(null);
        if (orders != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        orders = orderBLService.searchHotelOrder(null);
        if (orders != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }
    }
}
