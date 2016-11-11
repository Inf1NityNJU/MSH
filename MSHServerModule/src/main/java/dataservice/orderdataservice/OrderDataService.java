package dataservice.orderdataservice;

import po.OrderPO;
import po.OrderRoomPO;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/13.
 */
public interface OrderDataService {

    public ResultMessage addOrder(OrderPO opo);

    public ResultMessage updateOrder(String OrderID, OrderPO opo);

    public ResultMessage addOrderRoom(OrderRoomPO orpo);

    public ArrayList<OrderPO> searchOrderByOrderID(String orderID);

    public ArrayList<OrderPO> searchOrderByClientID(String clientID);

    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID);

    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID);

}
