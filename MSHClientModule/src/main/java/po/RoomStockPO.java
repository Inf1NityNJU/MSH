package po;

import util.DateUtil;
import util.RoomType;

/**
 * Created by SilverNarcissus on 16/10/14.
 */
public class RoomStockPO {
    private String hotelID;
    private RoomType roomType;
    private int availableQuantity;
    private DateUtil date;

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

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public DateUtil getDate() {
        return date;
    }

    public void setDate(DateUtil date) {
        this.date = date;
    }

    public RoomStockPO(String hotelID, RoomType roomType, int availableQuantity, DateUtil date) {
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.availableQuantity = availableQuantity;
        this.date = date;
    }
}
