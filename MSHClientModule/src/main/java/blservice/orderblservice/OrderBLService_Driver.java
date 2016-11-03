package blservice.orderblservice;

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

    public void driver(OrderBLService orderBLService) {
        ArrayList<OrderVO> orderVOs = new ArrayList<OrderVO>();

        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);

        BillVO bill = orderBLService.price("01011234", "000000001", new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), rooms);
        if (bill != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        ResultMessage rm = orderBLService.generate("01011234", "000000001", new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), rooms,
                new TimeUtil(2016, 10, 12, 14, 0, 0), 2, false);
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Generate Success");
        } else {
            System.out.print("Generate Failed");
        }

        ArrayList<OrderVO> orders = orderBLService.orders(null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.orders(OrderState.Unexecuted);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.orders(OrderState.Executed);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.orders(OrderState.Abnormal);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.orders(OrderState.Cancelled);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.clientOrders("000000001", null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.hotelOrders("01011234", null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        OrderVO order = orderBLService.getOrder("20161012010112340000");
        if (order != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        rm = orderBLService.revokeOrder("20161012010112340000");
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Revoke Success");
        } else {
            System.out.print("Revoke Failed");
        }

        rm = orderBLService.addCheckInTime(null, "20161012010112340000");
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Add Success");
        } else {
            System.out.print("Add Failed");
        }

        rm = orderBLService.addCheckOutTime(null, "20161012010112340000");
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Add Success");
        } else {
            System.out.print("Add Failed");
        }

        rm = orderBLService.editAssessment("20161012010112340000", new AssessmentVO(5,5,5,5, "很好很舒适，下次再来"));
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Edit Success");
        } else {
            System.out.print("Edit Failed");
        }

        ArrayList<AssessmentVO> assessment = orderBLService.hotelAssessment("01011234");
        if (assessment != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }
    }
}
