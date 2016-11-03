package vo;

import util.DateUtil;

/**
 * Created by SilverNarcissus on 16/10/14.
 */
public class RoomStockVO {
    public int availableQuantity;
    public DateUtil date;

    public RoomStockVO(int availableQuantity, DateUtil date) {
        this.availableQuantity = availableQuantity;
        this.date = date;
    }
}
