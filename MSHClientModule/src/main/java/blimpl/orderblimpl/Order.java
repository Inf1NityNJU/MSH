package blimpl.orderblimpl;

import blimpl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLInfo;
import blservice.userblservice.UserBLInfo;
import network.OrderClientNetworkImpl;
import network.OrderClientNetworkService;
import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.*;
import vo.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Sorumi on 16/10/30.
 */
public class Order {

    private OrderVO order;

    private ArrayList<OrderRoom> orderRooms;
    private Bill bill;

    private UserBLInfo userBLInfo;
    private HotelBLInfo hotelBLInfo;
    private OrderClientNetworkService orderClientNetworkService;

    public Order(UserBLInfo userBLInfo, HotelBLInfo hotelBLInfo, OrderClientNetworkService orderClientNetworkService) {
        this.userBLInfo = userBLInfo;
        this.hotelBLInfo = hotelBLInfo;
        this.orderClientNetworkService = orderClientNetworkService;
    }

    /**
     * 开始新的订单回合
     * @param order
     * @return
     */
    public ResultMessage startOrder(OrderVO order) {
        this.order = order;

        order.clientID = userBLInfo.getCurrentClientID();

        orderRooms = new ArrayList<>();

        for (OrderRoomVO room : order.rooms) {
            OrderRoom orderRoom = new OrderRoom(room.type, room.quantity, room.price);
            orderRooms.add(orderRoom);
        }

        bill = new Bill();
        return ResultMessage.SUCCESS;
    }

    /**
     * 得到房间库存
     * @param room
     * @return
     */
    public int getOrderRoomStock(OrderRoomVO room) {
        HotelBLInfo hotelBLInfo = new BLFactoryImpl().getHotelBLInfo();
        return hotelBLInfo.getAvailableQuantity(order.checkInDate, order.checkOutDate, order.hotelID, room.type);
    }

    /**
     * 修改订单房间数量
     *
     * @param type
     * @param quantity
     * @return 是否成功修改
     */
    public ResultMessage modifyRoomQuantity(RoomType type, int quantity) {

        OrderRoom orderRoom = null;

        for (OrderRoom orderRoomItr : orderRooms) {
            if (orderRoomItr.getType() == type) {
                orderRoom = orderRoomItr;
                break;
            }
        }

        if (orderRoom == null) {
            return ResultMessage.FAILED;
        }

        ResultMessage rm = orderRoom.modifyQuantity(quantity);

        ArrayList<OrderRoomVO> rooms = order.rooms;
        OrderRoomVO room = null;

        for (OrderRoomVO roomItr : rooms) {
            if (roomItr.type == type) {
                room = roomItr;
                break;
            }
        }

        room.quantity += quantity;

        if (rm == ResultMessage.NULL) {
            orderRooms.remove(orderRoom);
            rooms.remove(room);
        }

        return rm;
    }

    /**
     * 得到账单
     * * @return BillVO
     */
    public BillVO getBill() {

        int quantity = 0;

        for (OrderRoomVO roomItr : order.rooms) {
            quantity += roomItr.quantity;
        }

        ClientVO client = userBLInfo.getClientByID(order.clientID);
        Hotel_DetailVO hotel = hotelBLInfo.getHotel(order.hotelID);

        BillVO billVO = bill.refresh(order.hotelID, hotel.city, hotel.place, new DateUtil(LocalDate.now()), client.level,
                client.birthday, client.enterprise, quantity);

        // calculate price
        DecimalFormat df = new DecimalFormat("#.0");

        double originPrice = 0;

        for (OrderRoom orderRoomItr : orderRooms) {
            originPrice += orderRoomItr.getTotal();
        }

        billVO.originPrice = Double.parseDouble(df.format(originPrice));
        double totalPrice = billVO.originPrice;

        if (billVO.hotelPromotion != null) {
            totalPrice = totalPrice * billVO.hotelPromotion.promotionDiscount;
        }

        if (billVO.websitePromotion != null) {
            totalPrice = totalPrice * billVO.websitePromotion.promotionDiscount;
        }


        billVO.totalPrice = Double.parseDouble(df.format(totalPrice));

        order.bill = billVO;

        return billVO;
    }

    /**
     * 生成订单
     *
     * @param latest
     * @param peopleQuantity
     * @param hasChildren
     * @return 是否成功生成
     */
    public ResultMessage generate(TimeUtil latest, int peopleQuantity, boolean hasChildren) {

        order.bookedTime = new TimeUtil(LocalDateTime.now());
        order.state = OrderState.Unexecuted;

        order.latestExecuteTime = latest;
        order.peopleQuantity = peopleQuantity;
        order.hasChildren = hasChildren;

        generateOrderID();

        //OrderRoom
        ArrayList<OrderRoomVO> roomVOs = order.rooms;

        for (OrderRoomVO orderRoomVO : roomVOs) {
            OrderRoomPO orderRoomPO = orderRoomVO.toPO(order.orderID + roomVOs.indexOf(orderRoomVO), order.orderID);
            orderClientNetworkService.addOrderRoom(orderRoomPO);
        }

        OrderPO orderPO = order.toPO();

        ResultMessage rm = orderClientNetworkService.addOrder(orderPO);

        if (rm == ResultMessage.SUCCESS) {

            //前一天
            LocalDate tmpDate = LocalDate.parse(order.checkOutDate.toString());
            DateUtil lastDate = new DateUtil(tmpDate.plusDays(-1));

            for (OrderRoomVO orderRoomVO : roomVOs) {
                RoomChangeInfoVO roomChangeInfo = new RoomChangeInfoVO(order.checkInDate, lastDate, order.hotelID, orderRoomVO.type, orderRoomVO.quantity);
                hotelBLInfo.updateHotelRoomQuantity(roomChangeInfo);
            }
        }
        return rm;
    }

    /**
     * 撤销订单
     *
     * @param orderID
     * @return 是否成功撤销
     */
    public ResultMessage revoke(String orderID) {
        OrderPO orderPO = orderClientNetworkService.searchOrderByOrderID(orderID);
        TimeUtil cancelledTime = new TimeUtil(LocalDateTime.now());
        TimeUtil latestExecuteTime = new TimeUtil(orderPO.getLatestExecuteTime());

        orderPO.setCancelledTime(cancelledTime.toString());
        orderPO.setState(OrderState.Cancelled);

        ResultMessage rm = orderClientNetworkService.updateOrder(orderPO);

        long hour = cancelledTime.getIntervalTime(latestExecuteTime) / 1000 / 60 / 60;

//        System.out.println("Time:" + hour);

        if (hour < 6) {
            CreditChangeInfoVO creditChangeInfoVO = new CreditChangeInfoVO((int) (-orderPO.getTotalPrice() / 2), CreditAction.DEDUCT_CREDIT, orderID, cancelledTime.date);
            userBLInfo.addCreditRecord(orderPO.getClientID(), creditChangeInfoVO);
        }
        return rm;
    }

    /**
     * 撤销异常订单
     *
     * @param orderID
     * @param credit
     * @return
     */
    public ResultMessage revokeAbnormal(String orderID, int credit) {
        OrderPO orderPO = orderClientNetworkService.searchOrderByOrderID(orderID);
        TimeUtil cancelledTime = new TimeUtil(LocalDateTime.now());

        orderPO.setCancelledTime(cancelledTime.toString());
        orderPO.setState(OrderState.Cancelled);

        ResultMessage rm = orderClientNetworkService.updateOrder(orderPO);

        CreditChangeInfoVO creditChangeInfoVO = new CreditChangeInfoVO(credit, CreditAction.REVOKE_CREDIT, orderID, cancelledTime.date);
        userBLInfo.addCreditRecord(orderPO.getClientID(), creditChangeInfoVO);

        return rm;
    }

    /**
     * 更新入住
     *
     * @param orderID
     * @param time
     * @return 是否成功
     */
    public ResultMessage checkIn(String orderID, TimeUtil time) {
        OrderPO orderPO = orderClientNetworkService.searchOrderByOrderID(orderID);
        orderPO.setCheckInTime(time.toString());
        orderPO.setState(OrderState.Executed);
        ResultMessage rm = orderClientNetworkService.updateOrder(orderPO);
        if (rm == ResultMessage.SUCCESS) {
            CreditChangeInfoVO creditChangeInfoVO = new CreditChangeInfoVO((int) orderPO.getTotalPrice(), CreditAction.ADD_CREDIT, orderID, time.date);
            userBLInfo.addCreditRecord(orderPO.getClientID(), creditChangeInfoVO);
        }
        return rm;
    }

    /**
     * 更新退房
     *
     * @param orderID
     * @param time
     * @return 是否成功
     */
    public ResultMessage checkOut(String orderID, TimeUtil time) {
        OrderPO orderPO = orderClientNetworkService.searchOrderByOrderID(orderID);
        orderPO.setCheckOutTime(time.toString());
        return orderClientNetworkService.updateOrder(orderPO);
    }


    /**
     * 编辑评分评价
     *
     * @param orderID
     * @param assessment
     * @return 是否成功
     */
    public ResultMessage editAssessment(String orderID, AssessmentVO assessment) {
        OrderVO orderVO = searchOrderByID(orderID);
        assessment.clientID = orderVO.clientID;

        ResultMessage rm = orderClientNetworkService.addAssessment(assessment.toPO(orderID));

        if (rm == ResultMessage.SUCCESS) {
            double score = (assessment.healthScore + assessment.healthScore + assessment.locationScore + assessment.facilityScore) / 4.0;
            hotelBLInfo.addScoreToHotelByHotelID(score, orderVO.hotelID);
        }

        return rm;
    }

    /**
     * 通过订单ID搜索订单
     *
     * @param orderID
     * @return OrderVO
     */
    public OrderVO searchOrderByID(String orderID) {
        OrderPO orderPO = orderClientNetworkService.searchOrderByOrderID(orderID);
        return orderPOToOrderVO(orderPO);
    }

    /**
     * 通过订单状态搜索订单
     *
     * @param os
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchOrder(OrderState os) {
        ArrayList<OrderPO> orderPOs = orderClientNetworkService.searchOrder(os);
        return orderPOsToOrderVOs(orderPOs);
    }

    /**
     * 通过客户ID、订单状态搜索订单
     *
     * @param clientID
     * @param os
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchClientOrder(String clientID, OrderState os) {
        ArrayList<OrderPO> orderPOs = orderClientNetworkService.searchOrderByClientID(clientID, os);
        return orderPOsToOrderVOs(orderPOs);
    }

    /**
     * 通过酒店ID、订单状态搜索订单
     *
     * @param hotelID
     * @param os
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchHotelOrder(String hotelID, OrderState os) {
        ArrayList<OrderPO> orderPOs = orderClientNetworkService.searchOrderByHotelID(hotelID, os);
        return orderPOsToOrderVOs(orderPOs);
    }

    /**
     * 通过客户ID得到其预订过的酒店ID
     * 注：接受方认为这些ID不重复
     *
     * @param clientID 客户ID
     * @return 客户预定过的酒店ID
     */
    public ArrayList<String> getBookedHotelIDByClientID(String clientID) {
        ArrayList<OrderVO> orderVOs = searchClientOrder(clientID, null);
        ArrayList<String> hotelIDs = new ArrayList<>();
        for (OrderVO orderVO : orderVOs) {
            if (!hotelIDs.contains(orderVO.hotelID)) {
                hotelIDs.add(orderVO.hotelID);
            }
        }

        return hotelIDs;
    }

    /**
     * 客户是否预定过该酒店
     *
     * @param hotelID
     * @param clientID
     * @return
     */
    public boolean isBookedHotelByClient(String hotelID, String clientID) {
        ArrayList<String> hotels = getBookedHotelIDByClientID(clientID);
        return hotels.contains(hotelID);
    }

    /**
     * 通过酒店ID得到该酒店的评价
     *
     * @param hotelID
     * @return
     */
    public ArrayList<Assessment_HotelVO> getAssessmentByHotelID(String hotelID) {
        ArrayList<OrderVO> orderVOs = searchHotelOrder(hotelID, null);
        ArrayList<Assessment_HotelVO> assessments = new ArrayList<>();
        for (OrderVO orderVO : orderVOs) {
            if (orderVO.assessment != null) {
                AssessmentVO assessmentVO = orderVO.assessment;
                assessments.add(new Assessment_HotelVO(orderVO.clientName,
                        assessmentVO.serviceScore, assessmentVO.facilityScore, assessmentVO.healthScore, assessmentVO.locationScore,
                        assessmentVO.comment, orderVO.checkInDate));
            }
        }
        return assessments;
    }


    /**
     * 按时间近远对订单排序
     * @param orders
     */
    private void sortOrderByTime(ArrayList<OrderVO> orders) {
        orders.sort(new OrderComparator());
    }

    private class OrderComparator implements Comparator<OrderVO> {
        @Override
        public int compare(OrderVO o1, OrderVO o2) {
            return o2.bookedTime.compareTime(o1.bookedTime);
        }
    }

    /**
     * orderPO 列表装化为 orderVO 列表
     * @param orderPOs
     * @return
     */
    private ArrayList<OrderVO> orderPOsToOrderVOs(ArrayList<OrderPO> orderPOs) {
        ArrayList<OrderVO> orderVOs = new ArrayList<>();

        for (OrderPO orderPO : orderPOs) {
            OrderVO orderVO = orderPOToOrderVO(orderPO);
            orderVOs.add(orderVO);
        }

        sortOrderByTime(orderVOs);
        return orderVOs;
    }


    /**
     * orderPO 转化为 orderVO
     * @param orderPO
     * @return
     */
    private OrderVO orderPOToOrderVO(OrderPO orderPO) {
        Hotel_DetailVO hotel = hotelBLInfo.getHotel(orderPO.getHotelID());
        String hotelName = hotel != null ? hotel.name : "不存在";
        ClientVO client = userBLInfo.getClientByID(orderPO.getClientID());
        String clientName = client != null ? client.clientName : "不存在";

        ArrayList<OrderRoomPO> orderRoomPOs = orderClientNetworkService.searchOrderRoomByOrderID(orderPO.getOrderID());
        ArrayList<OrderRoomVO> orderRoomVOs = new ArrayList<>();

        for (OrderRoomPO orderRoomPO : orderRoomPOs) {
            OrderRoomVO orderRoomVO = new OrderRoomVO(orderRoomPO);
            orderRoomVOs.add(orderRoomVO);
        }

        BillVO billVO = new BillVO(orderPO);

        AssessmentVO assessmentVO = getOrderAssessment(orderPO.getOrderID());

        return new OrderVO(orderPO, hotelName, clientName, orderRoomVOs, billVO, assessmentVO);

    }

    /**
     * 获得订单评价
     *
     * @param orderID
     * @return
     */
    private AssessmentVO getOrderAssessment(String orderID) {
        AssessmentPO assessmentPO = orderClientNetworkService.searchAssessmentByOrderID(orderID);
        AssessmentVO assessmentVO = null;
        if (assessmentPO != null) {
            assessmentVO = new AssessmentVO(assessmentPO);
        }
        return assessmentVO;
    }

    private void generateOrderID() {
        String prefix = order.bookedTime.date.toString().replace("-", "") + order.hotelID;

        int quantity = orderClientNetworkService.searchOrderQuantity(prefix);
        String quantityString = String.valueOf(quantity);


        int length = 4 - quantityString.length();
        for (int i = 0; i < length; i++) {
            quantityString = "0" + quantityString;
        }

        order.orderID = prefix + quantityString;
    }
}
