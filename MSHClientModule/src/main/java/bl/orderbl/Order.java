package bl.orderbl;

import bl.blfactory.BLFactoryImpl;
import blservice.hotelblservice.HotelBLInfo;
import blservice.userblservice.UserBLInfo;
import dataimpl.orderdataimpl.OrderDataServiceFactory;
import dataservice.orderdataservice.OrderDataService;
import po.AssessmentPO;
import po.HotelPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.*;
import vo.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Sorumi on 16/10/30.
 */
public class Order {

    private OrderVO order;

    private ArrayList<OrderRoom> orderRooms;
    private Bill bill;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo();
    private HotelBLInfo hotelBLInfo = new BLFactoryImpl().getHotelBLInfo();

    //TODO
    private OrderDataService orderDataService = OrderDataServiceFactory.getOrderDataService();

    public ResultMessage startOrder(OrderVO order) {
        this.order = order;

        order.clientID = userBLInfo.getCurrentID();

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

//        Bill mockbill = new MockBill();

        int quantity = 0;

        for (OrderRoomVO roomItr : order.rooms) {
            quantity += roomItr.quantity;
        }

        ClientVO client = userBLInfo.getClientByID(order.clientID);
        Hotel_DetailVO hotel = hotelBLInfo.getHotel(order.hotelID);

        BillVO billVO = bill.refresh(order.hotelID, hotel.place, new DateUtil(LocalDate.now()), client.level,
                client.birthday, client.enterprise, quantity);

//        ClientVO client = new ClientVO("000000001", "哈哈哈", 3, new DateUtil(2011, 02, 14), 200, 0, "12", "1111111", "123");
//        BillVO billVO = mockbill.refresh(order.hotelID, new DateUtil(LocalDate.now()), client.birthday, client.enterprise, quantity);

        double originPrice = 0;


        for (OrderRoom orderRoomItr : orderRooms) {
            originPrice += orderRoomItr.getTotal();
        }

        billVO.originPrice = originPrice;
        double totalPrice = originPrice;

        if (billVO.hotelPromotion != null) {
            totalPrice = totalPrice * billVO.hotelPromotion.promotionDiscount;
        }

        if (billVO.websitePromotion != null) {
            totalPrice = totalPrice * billVO.websitePromotion.promotionDiscount;
        }

        DecimalFormat df = new DecimalFormat("#.00");
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

        //OrderRoom
        ArrayList<OrderRoomVO> roomVOs = order.rooms;

        for (OrderRoomVO orderRoomVO : roomVOs) {
            OrderRoomPO orderRoomPO = orderRoomVO.toPO(order.orderID + roomVOs.indexOf(orderRoomVO), order.orderID);
            orderDataService.addOrderRoom(orderRoomPO);
        }

        order.bookedTime = new TimeUtil(LocalDateTime.now());
        order.state = OrderState.Unexecuted;

        order.latestExecuteTime = latest;
        order.peopleQuantity = peopleQuantity;
        order.hasChildren = hasChildren;

        generateOrderID();

        OrderPO orderPO = order.toPO();
        return orderDataService.addOrder(orderPO);
    }

    /**
     * 撤销订单
     *
     * @param orderID
     * @return 是否成功撤销
     */
    public ResultMessage revoke(String orderID) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 更新入住
     *
     * @param orderID
     * @param time
     * @return 是否成功
     */
    public ResultMessage checkIn(String orderID, TimeUtil time) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 更新退房
     *
     * @param orderID
     * @param time
     * @return 是否成功
     */
    public ResultMessage checkOut(String orderID, TimeUtil time) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 编辑评分评价
     *
     * @param orderID
     * @param assessment
     * @return 是否成功
     */
    public ResultMessage editAssessment(String orderID, AssessmentVO assessment) {
        return ResultMessage.SUCCESS;
    }

    /**
     * 通过订单ID搜索订单
     *
     * @param orderID
     * @return OrderVO
     */
    public OrderVO searchOrderByID(String orderID) {
        return null;
    }

    /**
     * 通过订单状态、关键字搜索订单
     *
     * @param os
     * @param keyword
     * @return OrderVO列表
     */
    public ArrayList<OrderVO> searchOrder(OrderState os, String keyword) {
//        ArrayList<OrderPO> orderPOs = orderDataService.s
        return null;
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
        ArrayList<OrderPO> orderPOs = orderDataService.searchOrderByClientID(clientID, os);
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
        ArrayList<OrderPO> orderPOs = orderDataService.searchOrderByHotelID(hotelID, os);
        return orderPOsToOrderVOs(orderPOs);
    }


    private ArrayList<OrderVO> orderPOsToOrderVOs(ArrayList<OrderPO> orderPOs) {
        ArrayList<OrderVO> orderVOs = new ArrayList<>();

        for (OrderPO orderPO : orderPOs) {
            Hotel_DetailVO hotel = hotelBLInfo.getHotel(orderPO.getHotelID());
            String hotelName = hotel != null ? hotel.name : "不存在";
            ClientVO client = userBLInfo.getClientByID(orderPO.getClientID());
            String clientName = client != null ? client.clientName : "不存在";

            ArrayList<OrderRoomPO> orderRoomPOs = orderDataService.searchOrderRoomByOrderID(orderPO.getOrderID());
            ArrayList<OrderRoomVO> orderRoomVOs = new ArrayList<>();

            for (OrderRoomPO orderRoomPO : orderRoomPOs) {
                OrderRoomVO orderRoomVO = new OrderRoomVO(orderRoomPO);
                orderRoomVOs.add(orderRoomVO);
            }

            BillVO billVO = new BillVO(orderPO);

            AssessmentPO assessmentPO = orderDataService.searchAssessmentByOrderID(orderPO.getOrderID());
            AssessmentVO assessmentVO = null;
            if (assessmentPO != null) {
                assessmentVO = new AssessmentVO(assessmentPO);
            }

            OrderVO orderVO = new OrderVO(orderPO, hotelName, clientName, orderRoomVOs, billVO, assessmentVO);

            orderVOs.add(orderVO);
        }

        return orderVOs;
    }

    private void generateOrderID() {
        String prefix = order.bookedTime.date.toString().replace("-", "") + order.hotelID;

        int quantity = orderDataService.searchOrderQuantity(prefix);
        String quantityString = String.valueOf(quantity);


        int length = 4 - quantityString.length();
        for (int i = 0; i < length; i++) {
            quantityString = "0" + quantityString;
        }

        order.orderID = prefix + quantityString;
    }
}
