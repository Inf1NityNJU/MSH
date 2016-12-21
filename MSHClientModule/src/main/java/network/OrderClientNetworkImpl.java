package network;

import network.ordernetworkservice.OrderServerNetworkService;
import network.promotionnetworkservice.PromotionServerNetworkService;
import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.OrderState;
import util.ResultMessage;
import vo.OrderRoomVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public class OrderClientNetworkImpl implements OrderClientNetworkService {

    private OrderServerNetworkService orderServerNetworkService;


    public OrderClientNetworkImpl() {
        try {
            orderServerNetworkService = (OrderServerNetworkService) Naming.lookup("OrderServerNetworkService");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ResultMessage addOrder(OrderPO opo) {
        try {
            return orderServerNetworkService.addOrder(opo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage updateOrder(OrderPO opo) {
        try {
            return orderServerNetworkService.updateOrder(opo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage addOrderRoom(OrderRoomPO orpo) {
        try {
            return orderServerNetworkService.addOrderRoom(orpo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public OrderPO searchOrderByOrderID(String orderID) {
        try {
            return orderServerNetworkService.searchOrderByOrderID(orderID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<OrderPO> searchOrder(OrderState orderState) {
        try {
            return orderServerNetworkService.searchOrder(orderState);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<OrderPO>();
        }
    }

    public ArrayList<OrderPO> searchOrderByClientID(String clientID, OrderState orderState) {
        try {
            return orderServerNetworkService.searchOrderByClientID(clientID, orderState);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<OrderPO>();
        }
    }

    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID, OrderState orderState) {
        try {
            return orderServerNetworkService.searchOrderByHotelID(hotelID, orderState);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<OrderPO>();
        }
    }

    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID) {
        try {
            return orderServerNetworkService.searchOrderRoomByOrderID(orderID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<OrderRoomPO>();
        }
    }

    public ResultMessage addAssessment(AssessmentPO assessment) {
        try {
            return orderServerNetworkService.addAssessment(assessment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage updateAssessment(AssessmentPO assessment) {
        try {
            return orderServerNetworkService.updateAssessment(assessment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AssessmentPO searchAssessmentByOrderID(String orderID) {
        try {
            return orderServerNetworkService.searchAssessmentByOrderID(orderID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int searchOrderQuantity(String orderIDPrefix) {
        try {
            return orderServerNetworkService.searchOrderQuantity(orderIDPrefix);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
