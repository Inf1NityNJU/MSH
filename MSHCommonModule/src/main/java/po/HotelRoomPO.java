package po;

import util.RoomType;

import java.io.Serializable;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class HotelRoomPO implements Serializable {
    /**
     * 酒店房间编号
     */
    private String ID;

    /**
     * 房间所属酒店编号
     */
    private String hotelID;
    /**
     * 房间类型
     */
    private RoomType roomType;
    /**
     * 房间单价
     */
    private double price;
    /**
     * 房间总数
     */
    private int totalQuantity;
    /**
     * 是否被取消
     */
    private boolean isCancelled;

    public HotelRoomPO() {

    }

    public HotelRoomPO(String hotelID, RoomType roomType, double price, int totalQuantity) {
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.price = price;
        this.totalQuantity = totalQuantity;
    }

    public HotelRoomPO(String ID, String hotelID, RoomType roomType, double price, int totalQuantity, boolean isCancelled) {
        this(hotelID, roomType, price, totalQuantity);
        this.ID = ID;
        this.isCancelled = isCancelled;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
