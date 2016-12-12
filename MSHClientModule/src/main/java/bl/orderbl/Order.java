package bl.orderbl;

import bl.blfactory.BLFactoryImpl;
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

/**
 * Created by Sorumi on 16/10/30.
 */
public class Order {

    private OrderVO order;

    private ArrayList<OrderRoom> orderRooms;
    private Bill bill;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();
    private HotelBLInfo hotelBLInfo = new BLFactoryImpl().getHotelBLInfo();

    private OrderClientNetworkService orderClientNetworkService = new OrderClientNetworkImpl();

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

        orderRoom.modifyQuantity(quantity);

        ArrayList<OrderRoomVO> rooms = order.rooms;
        OrderRoomVO room = null;

        for (OrderRoomVO roomItr : rooms) {
            if (roomItr.type == type) {
                room = roomItr;
                break;
            }
        }

        room.quantity += quantity;

        return ResultMessage.SUCCESS;
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

        BillVO billVO = bill.refresh(order.hotelID, hotel.place, new DateUtil(LocalDate.now()), client.level,
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

            //TODO
//            LocalDate tmpDate = LocalDate.parse(order.checkOutDate.toString());
//            DateUtil lastDate = new DateUtil(tmpDate.plusDays(1));

            for (OrderRoomVO orderRoomVO : roomVOs) {
                RoomChangeInfoVO roomChangeInfo = new RoomChangeInfoVO(order.checkInDate, order.checkOutDate, order.hotelID, orderRoomVO.type, orderRoomVO.quantity);
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
        orderPO.setCancelledTime(new TimeUtil(LocalDateTime.now()).toString());
        orderPO.setState(OrderState.Cancelled);
//        userBLInfo.
        //TODO
        //update manager credit
        return orderClientNetworkService.updateOrder(orderPO);
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
        return orderClientNetworkService.updateOrder(orderPO);

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
        return  orderClientNetworkService.addAssessment(assessment.toPO(orderID));
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
     * 通过订单状态、关键字搜索订单
     *
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchOrder(OrderState os, String keyword) {
        ArrayList<OrderPO> orderPOs = orderClientNetworkService.searchOrder(os, null, null);
        return orderPOsToOrderVOs(orderPOs);
    }

    /**
     * 通过客户ID、订单状态、关键字搜索订单
     *
     * @param clientID
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchClientOrder(String clientID, OrderState os, String keyword) {
        ArrayList<OrderPO> orderPOs = orderClientNetworkService.searchOrderByClientID(clientID, os);
        return orderPOsToOrderVOs(orderPOs);

    }

    /**
     * 通过酒店ID、订单状态、关键字搜索订单
     *
     * @param hotelID
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchHotelOrder(String hotelID, OrderState os, String keyword) {
        ArrayList<OrderPO> orderPOs = orderClientNetworkService.searchOrderByHotelID(hotelID, os);
        return orderPOsToOrderVOs(orderPOs);
    }

    private ArrayList<OrderVO> orderPOsToOrderVOs(ArrayList<OrderPO> orderPOs) {
        ArrayList<OrderVO> orderVOs = new ArrayList<>();

        for (OrderPO orderPO : orderPOs) {
            OrderVO orderVO = orderPOToOrderVO(orderPO);
            orderVOs.add(orderVO);
        }

        return orderVOs;
    }

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

        AssessmentPO assessmentPO = orderClientNetworkService.searchAssessmentByOrderID(orderPO.getOrderID());
        AssessmentVO assessmentVO = null;
        if (assessmentPO != null) {
            assessmentVO = new AssessmentVO(assessmentPO);
        }

        return new OrderVO(orderPO, hotelName, clientName, orderRoomVOs, billVO, assessmentVO);

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
