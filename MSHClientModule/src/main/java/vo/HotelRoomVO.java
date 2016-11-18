package vo;

import bl.hotelbl.HotelRoom;
import util.RoomType;

import java.util.ArrayList;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class HotelRoomVO {
    public String hotelID;
    public RoomType roomType;
    public double price;
    public int totalQuantity;
    public ArrayList<RoomStockVO> roomStockVOs;

    public HotelRoomVO(String hotelID,RoomType roomType, double price, int totalQuantity, ArrayList<RoomStockVO> roomStockVOs) {
        this.hotelID=hotelID;
        this.roomType = roomType;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.roomStockVOs = roomStockVOs;
    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof HotelRoomVO) {
            HotelRoomVO rvo = (HotelRoomVO) o;
            return compareData(rvo);
        }
        return false;
    }

    /**
     * 生成对象的hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return (int) price;
    }

    /**
     * 分别比较每个数据
     *
     * @param rvo
     * @return 比较结果
     */
    private boolean compareData(HotelRoomVO rvo) {
        return judgeEqual(roomType, rvo.roomType)
                && judgeEqual(price, rvo.price)
                && judgeEqual(totalQuantity, rvo.totalQuantity)
                && judgeEqual(roomStockVOs, rvo.roomStockVOs);
    }
}
