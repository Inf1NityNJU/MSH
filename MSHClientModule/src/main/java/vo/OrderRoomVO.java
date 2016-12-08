package vo;

import po.OrderRoomPO;
import util.RoomType;

/**
 * Created by Kray on 2016/10/12.
 */
public class OrderRoomVO {
    /**
     * 房间类型
     */
    public RoomType type;

    /**
     * 数量
     */
    public int quantity;

    /**
     * 价格
     */
    public double price;

    /**
     * 构造方法
     * @param type
     * @param quantity
     * @param price
     */
    public OrderRoomVO(RoomType type, int quantity, double price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderRoomPO toPO(String ID, String orderID) {
        return new OrderRoomPO(ID, orderID, type, quantity, price);
    }

    public OrderRoomVO(OrderRoomPO orderRoomPO) {
        this.type = orderRoomPO.getRoomType();
        this.quantity = orderRoomPO.getQuantity();
        this.price = orderRoomPO.getPrice();
    }
}
