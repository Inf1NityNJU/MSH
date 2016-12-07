package vo;

import util.RoomType;

/**
 * Created by Sorumi on 16/12/7.
 */
public class OrderRoomStockVO {

    public RoomType type;

    public double price;

    public int availableQuantity;

    public OrderRoomStockVO(RoomType type, double price, int availableQuantity) {
        this.type = type;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }
}
