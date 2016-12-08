package vo;

import util.RoomType;

/**
 * Created by Sorumi on 16/12/7.
 */
public class OrderRoomStockVO {

    public OrderRoomVO orderRoom;

    public int availableQuantity;

    public OrderRoomStockVO(RoomType type, double price, int availableQuantity) {
        this.orderRoom = new OrderRoomVO(type, 0, price);
        this.availableQuantity = availableQuantity;
    }
}
