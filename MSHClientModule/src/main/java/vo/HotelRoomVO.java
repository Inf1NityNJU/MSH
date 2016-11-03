package vo;

import util.RoomType;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/12.
 */
public class HotelRoomVO {
    public RoomType roomType;
    public double price;
    public int totalQuantity;
    public ArrayList<RoomStockVO> roomStockVOs;

    public HotelRoomVO(RoomType roomType, double price, int totalQuantity, ArrayList<RoomStockVO> roomStockVOs) {
        this.roomType = roomType;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.roomStockVOs = roomStockVOs;
    }
}
