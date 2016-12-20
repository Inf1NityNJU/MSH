package vo;

import util.City;
import util.DateUtil;
import util.Place;
import util.RoomType;

import java.lang.reflect.Field;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class FilterFlagsVO {
    /**
     * 酒店所属城市
     */
    public City city;
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
    public int quantity;
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

    public FilterFlagsVO(City city, Place place, String name, RoomType roomType, double minPrice, double maxPrice, DateUtil checkInDate, DateUtil checkOutDate, int quantity, int star, double minScore, double maxScore, String bookedClientID) {
        this.city = city;
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

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof FilterFlagsVO) {
            FilterFlagsVO filterFlagsVO = (FilterFlagsVO) o;
            return compareData(filterFlagsVO);
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
        return star;
    }

    /**
     * 分别比较每个数据
     *
     * @param filterFlagsVO
     * @return 比较结果
     */
    private boolean compareData(FilterFlagsVO filterFlagsVO) {
        return judgeEqual(place, filterFlagsVO.place)
                && judgeEqual(name, filterFlagsVO.name)
                && judgeEqual(roomType, filterFlagsVO.roomType)
                && judgeEqual(minPrice, filterFlagsVO.minPrice)
                && judgeEqual(maxPrice, filterFlagsVO.maxPrice)
                && judgeEqual(checkInDate, filterFlagsVO.checkInDate)
                && judgeEqual(checkOutDate, filterFlagsVO.checkOutDate)
                && judgeEqual(quantity, filterFlagsVO.quantity)
                && judgeEqual(star, filterFlagsVO.star)
                && judgeEqual(minScore, filterFlagsVO.minPrice)
                && judgeEqual(maxScore, filterFlagsVO.maxPrice)
                && judgeEqual(bookedClientID, filterFlagsVO.bookedClientID);
    }

    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator", "\n");

        String result = "----------" + this.getClass().getName() + "----------" + lineSeparator;
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                if (field.get(this) == null) {
                    result += field.getName() + ": null" + "    ";
                } else {
                    result += field.getName() + ": " + field.get(this).toString() + "    ";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        result += lineSeparator + "--------------------" + lineSeparator;

        return result;
    }

    /**
     * 判断是否有对房间的搜索条件
     *
     * @return true-有对房间的搜索条件<br>
     * false-没有对房间的搜索条件
     */
    public boolean roomIsSet() {
        return !(minPrice == 0
                && maxPrice == 0
                && roomType == null
                && checkInDate == null
                && checkOutDate == null
                && quantity == 0);
    }
}
