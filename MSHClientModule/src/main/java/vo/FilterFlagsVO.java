package vo;

import util.DateUtil;
import util.Place;
import util.RoomType;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class FilterFlagsVO {
    /**
     * 酒店所属商圈
     */
    public Place place;
    /**
     * 酒店名称
     */
    public String name;
    /**
     * 酒店房间类型
     */
    public RoomType roomType;
    /**
     * 最小价格
     */
    public double minPrice;
    /**
     * 最大价格
     */
    public double maxPrice;
    /**
     * 入住日期
     */
    public DateUtil checkInDate;
    /**
     * 退房日期
     */
    public DateUtil checkOutDate;
    /**
     * 房间数量
     */
    public double quantity;
    /**
     * 酒店星级
     */
    public int star;
    /**
     * 最小评分
     */
    public double minScore;
    /**
     * 最大评分
     */
    public double maxScore;
    /**
     * 预订该酒店的客户ID
     */
    public String bookedClientID;

    public FilterFlagsVO(Place place, String name, RoomType roomType, double minPrice, double maxPrice, DateUtil checkInDate, DateUtil checkOutDate, double quantity, int star, double minScore, double maxScore, String bookedClientID) {
        this.place = place;
        this.name = name;
        this.roomType = roomType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.quantity = quantity;
        this.star = star;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.bookedClientID = bookedClientID;
    }
}
