package dataservice.orderdataservice;

import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/11.
 */
public class OrderDataService_Stub implements OrderDataService {

    private ArrayList<OrderPO> orderPOs;
    private ArrayList<OrderRoomPO> roomPOs;

    public OrderDataService_Stub() {
        roomPOs = new ArrayList<OrderRoomPO>();
        OrderRoomPO room = new OrderRoomPO("20161012010112340000", RoomType.DoubleRoom, 1, 200);
        roomPOs.add(room);

        orderPOs = new ArrayList<OrderPO>();
        OrderPO order = new OrderPO("20161012010112340000", "01011234", "000000001",
                new DateUtil(2016, 10, 12), new DateUtil(2016, 10, 13), null, null,
                null, null, new TimeUtil(2016, 10, 11, 14, 0, 0), 2, false, OrderState.Unexecuted, null, 1, null, 1, 300, 280);
        orderPOs.add(order);
    }

    @Override
    public ResultMessage addOrder(OrderPO opo) {
        System.out.println("Insert Succeed!");
		return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage updateOrder(OrderPO opo) {
        if (opo.getOrderID().equals("20161012010112340000")) {
            System.out.println("Update Succeed!");
        } else {
            System.out.println("Update Failed!");
        }
		return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage addOrderRoom(OrderRoomPO orpo) {
        if (orpo.getOrderID().equals("20161012010112340000")) {
            System.out.println("Add Succeed!");
        } else {
            System.out.println("Add Failed!");
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public OrderPO searchOrderByOrderID(String orderID) {
        if (orderID.equals("20161012010112340000")) {
            System.out.println("SearchByOrderID Succeed!");
            return orderPOs.get(0);
        } else {
            System.out.println("SearchByOrderID Failed!");
            return null;
        }

    }

    @Override
    public ArrayList<OrderPO> searchOrder(OrderState orderState) {
        return null;
    }


    @Override
    public ArrayList<OrderPO> searchOrderByClientID(String clientID, OrderState orderState) {
        if (clientID.equals("000000001")) {
            System.out.println("SearchByClientID Succeed!");
            return orderPOs;
        } else {
            System.out.println("SearchByClientID Failed!");
            return new ArrayList<OrderPO>();
        }

    }

    @Override
    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID, OrderState orderState) {
        if (hotelID.equals("01011234")) {
            System.out.println("SearchByHotelID Succeed!");
            return orderPOs;
        } else {
            System.out.println("SearchByHotelID Failed!");
            return new ArrayList<OrderPO>();
        }
    }

    @Override
    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID) {
        if (orderID.equals("20161012010112340000")) {
            System.out.println("SearchRoomByHotelID Succeed!");
            return roomPOs;
        } else {
            System.out.println("SearchByHotelID Failed!");
            return new ArrayList<OrderRoomPO>();
        }
    }

    @Override
    public ResultMessage addAssessment(AssessmentPO assessment) {
        return null;
    }

    @Override
    public ResultMessage updateAssessment(AssessmentPO assessment) {
        return null;
    }

    @Override
    public AssessmentPO searchAssessmentByOrderID(String orderID) {
        return null;
    }

    @Override
    public int searchOrderQuantity(String orderIDPrefix) {
        return 0;
    }
}
