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

        ResultMessage rm = orderBLService.checkCredit();
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Credit enough");
        } else {
            System.out.print("Credit not enough");
        }

        rm = orderBLService.modifyDate(new DateUtil(2016, 11, 10), new DateUtil(2016, 11, 14));
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Modify Success");
        } else {
            System.out.print("Modify Failed");
        }

        rm = orderBLService.modifyRoomQuantity(RoomType.SingleRoom, 2);
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Modify Success");
        } else {
            System.out.print("Modify Failed");
        }

        BillVO bill = orderBLService.getBill();
        if (bill != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        rm = orderBLService.generateOrder("01011234", new TimeUtil(2016, 10, 12, 18, 0, 0), 3, false);
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Generate Success");
        } else {
            System.out.print("Generate Failed");
        }

        rm = orderBLService.revokeOrder("20161012010112340000");
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Revoke Success");
        } else {
            System.out.print("Revoke Failed");
        }

        rm = orderBLService.checkInOrder("20161012010112340000", new TimeUtil(2016, 10, 17, 18, 0, 0));
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Add Success");
        } else {
            System.out.print("Add Failed");
        }

        rm = orderBLService.checkOutOrder("20161012010112340000", new TimeUtil(2016, 10, 18, 18, 0, 0));
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Add Success");
        } else {
            System.out.print("Add Failed");
        }

        rm = orderBLService.editOrderAssessment("20161012010112340000", new AssessmentVO(5,5,5,5, "很好很舒适，下次再来"));
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Edit Success");
        } else {
            System.out.print("Edit Failed");
        }

        OrderVO order = orderBLService.searchOrderByID("20161012010112340000");
        if (order != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        ArrayList<OrderVO> orders = orderBLService.searchOrder(null, null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.searchOrder(OrderState.Unexecuted, null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.searchOrder(OrderState.Executed, null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.searchOrder(OrderState.Abnormal, null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.searchOrder(OrderState.Cancelled, null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.searchClientOrder(null, null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }

        orders = orderBLService.searchHotelOrder(null, null);
        if (orders != null) {
            System.out.print("Success");
        } else {
            System.out.print("Failed");
        }
    }
}
