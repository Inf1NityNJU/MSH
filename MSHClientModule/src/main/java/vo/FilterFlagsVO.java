package vo;

import util.DateUtil;
import util.Place;
import util.RoomType;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class FilterFlagsVO {
    public Place place;
    public String name;
    public RoomType roomType;
    public double minPrice;
    public double maxPrice;
    public DateUtil checkInDate;
    public DateUtil checkOutDate;
    public double quantity;
    public int star;
    public double minScore;
    public double maxScore;
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
