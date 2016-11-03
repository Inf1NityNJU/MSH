package blservice.orderblservice;

import util.DateUtil;
import util.OrderState;
import util.ResultMessage;
import util.TimeUtil;
import vo.AssessmentVO;
import vo.BillVO;
import vo.OrderRoomVO;
import vo.OrderVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/10/12.
 */
public interface OrderBLService {


    public BillVO price(String hotelID, String clientID, DateUtil checkInDate, DateUtil checkOutDate, ArrayList<OrderRoomVO> rvo);

    public ResultMessage generate(String hotelID, String clientID, DateUtil checkInDate, DateUtil checkOutDate, ArrayList<OrderRoomVO> rvo,
                                  TimeUtil latestExecuteTime, int peopleQuantity, boolean hasChildren);

    public ArrayList<OrderVO> orders(OrderState os);

    public ArrayList<OrderVO> clientOrders(String clientID, OrderState os);

    public ArrayList<OrderVO> hotelOrders(String hotelID, OrderState os);

    public OrderVO getOrder(String orderID);

    public ResultMessage revokeOrder(String orderID);

    public ResultMessage addCheckInTime(TimeUtil checkInTime, String orderID);

    public ResultMessage addCheckOutTime(TimeUtil checkOutTime, String orderID);

    public ResultMessage editAssessment(String orderID, AssessmentVO assessment);

    public ArrayList<AssessmentVO> hotelAssessment(String hotelID);
}
