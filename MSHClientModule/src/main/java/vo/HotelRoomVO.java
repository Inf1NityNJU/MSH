package vo;

import util.RoomType;

import java.util.ArrayList;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class HotelRoomVO {
    /**
     * 房间所属酒店编号
     */
    public String hotelID;
    /**
     * 房间类型
     */
    public RoomType roomType;
    /**
     * 房间单价
     */
    public double price;
    /**
     * 房间总数
     */
    public int totalQuantity;
    /**
     * 是否被取消
     */
    public boolean isCancelled;
    /**
     * 可被预订的房间存量
     */
    public ArrayList<RoomStockVO> roomStockVOs;

    public HotelRoomVO(String hotelID, RoomType roomType, double price, int totalQuantity, ArrayList<RoomStockVO> roomStockVOs,boolean isCancelled) {
        this(hotelID,roomType,price,totalQuantity,roomStockVOs);
        this.isCancelled=isCancelled;
    }

    public HotelRoomVO(String hotelID, RoomType roomType, double price, int totalQuantity, ArrayList<RoomStockVO> roomStockVOs) {
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.roomStockVOs = roomStockVOs;
        this.isCancelled=false;
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
