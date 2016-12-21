package network;

import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.OrderState;
import util.ResultMessage;

import java.rmi.Remote;
import java.util.ArrayList;

/**
 * Created by Sorumi on 16/12/11.
 */
public interface OrderClientNetworkService extends Remote {

    public ResultMessage addOrder(OrderPO opo);

    public ResultMessage updateOrder(OrderPO opo);

    public ResultMessage addOrderRoom(OrderRoomPO orpo);

    public OrderPO searchOrderByOrderID(String orderID);

    public ArrayList<OrderPO> searchOrder(OrderState orderState);

    public ArrayList<OrderPO> searchOrderByClientID(String clientID, OrderState orderState);

    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID, OrderState orderState);

    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID);

    public ResultMessage addAssessment(AssessmentPO assessment);

    public ResultMessage updateAssessment(AssessmentPO assessment);

    public AssessmentPO searchAssessmentByOrderID(String orderID);

    public int searchOrderQuantity(String orderIDPrefix);
}
