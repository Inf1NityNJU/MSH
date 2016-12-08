package dataservice.orderdataservice;

import po.OrderPO;
import po.OrderRoomPO;
import util.*;

/**
 * Created by Sorumi on 16/10/13.
 */
public class OrderDataService_Driver {

    public void driver(OrderDataService orderDataService) {

        ResultMessage rm = orderDataService.addOrder(new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, null, 1, null, 1, 300, 280));
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Add Order Success");
        } else {
            System.out.print("Add Order Failed");
        }

        rm = orderDataService.updateOrder(new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, null, 1, null, 1, 300, 280));
        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Update Order Success");
        } else {
            System.out.print("Update Order Failed");
        }

        rm = orderDataService.addOrderRoom(new OrderRoomPO("20161012010112340000", RoomType.DoubleRoom, 1, 200));

        if (rm == ResultMessage.SUCCESS) {
            System.out.print("Add Room Success");
        } else {
            System.out.print("Add Room Failed");
        }

        orderDataService.searchOrderByOrderID("20161012010112340000");

        orderDataService.searchOrderByClientID("000000001", null);

        orderDataService.searchOrderByHotelID("01011234", null);

        orderDataService.searchOrderRoomByOrderID("20161012010112340000");

    }
}
