package po;

import util.DateUtil;
import util.RoomType;

import java.io.Serializable;

/**
 * Created by SilverNarcissus on 16/10/14.
 */
public class RoomStockPO  implements Serializable {
    /**
     * 库存房间ID
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
     * 可用房间数量
     */
    private int availableQuantity;
    /**
     * 记录日期
     */
    private String date;


    public RoomStockPO(String hotelID, RoomType roomType, int availableQuantity, String date) {
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.availableQuantity = availableQuantity;
        this.date = date;
    }

    public RoomStockPO(String ID, String hotelID, RoomType roomType, int availableQuantity, String date) {
        this(hotelID, roomType, availableQuantity, date);
        this.ID = ID;
    }

    public RoomStockPO() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
