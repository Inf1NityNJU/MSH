package vo;

import bl.hotelbl.HotelRoom;
import util.RoomType;

import java.util.ArrayList;

import static util.EqualJudgeHelper.judgeEqual;

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


    @Override
    public boolean equals(Object o){
        if(o instanceof HotelRoomVO){
            HotelRoomVO rvo=(HotelRoomVO) o;
            return compareData(rvo);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (int)price;
    }

    private boolean compareData(HotelRoomVO rvo){
        return judgeEqual(roomType, rvo.roomType)&&judgeEqual(price,rvo.price)&&judgeEqual(totalQuantity,rvo.totalQuantity)&&judgeEqual(roomStockVOs,rvo.roomStockVOs);
    }
}
