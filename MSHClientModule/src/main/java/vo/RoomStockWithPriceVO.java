//package vo;
//
//import po.RoomStockPO;
//import util.DateUtil;
//
///**
// * Created by SilverNarcissus on 2016/12/3.
// */
//public class RoomStockWithPriceVO {
//    public double price;
//
//    public DateUtil date;
//
//    public int availableQuantity;
//
//    public RoomStockWithPriceVO(double price, DateUtil date, int availableQuantity) {
//        this.price = price;
//        this.date = date;
//        this.availableQuantity = availableQuantity;
//    }
//
//    public RoomStockWithPriceVO(double price, RoomStockPO roomStockPO) {
//        this(price, new DateUtil(roomStockPO.getDate()), roomStockPO.getAvailableQuantity());
//    }
//}
