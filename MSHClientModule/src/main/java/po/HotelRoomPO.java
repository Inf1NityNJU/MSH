package po;

import util.RoomType;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class HotelRoomPO {
    private String hotelID;
    private RoomType roomType;
    private double price;
    private int totalQuantity;

    public HotelRoomPO(String hotelID, RoomType roomType, double price, int totalQuantity) {
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.price = price;
        this.totalQuantity = totalQuantity;
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
}
