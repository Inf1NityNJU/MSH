package po;

import util.RoomType;

import java.io.Serializable;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class OrderRoomPO implements Serializable {

    /**
     * ID
     */
    private String ID;

    /**
     * 订单ID
     */
    private String orderID;

    /**
     * 房间类型
     */
    private RoomType roomType;

    /**
     * 数量
     */
    private int quantity;

    /**
     * 价格
     */
    private double price;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderRoomPO() {

    }

    public OrderRoomPO(String orderID, RoomType roomType, int quantity, double price) {
        this.orderID = orderID;
        this.roomType = roomType;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderRoomPO(String ID, String orderID, RoomType roomType, int quantity, double price) {
        this(orderID, roomType, quantity, price);
        this.ID = ID;
    }
}
