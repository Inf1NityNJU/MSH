package bl.orderbl;

import bl.hotelbl.HotelBLFactory;
import blservice.hotelblservice.HotelBLInfo;
import util.ResultMessage;
import util.RoomType;

/**
 * Created by Sorumi on 16/11/1.
 */
public class OrderRoom {


    private HotelBLInfo hotelBLInfo;

    private RoomType type;
    private double price;
    private int quantity;


    public OrderRoom() {
        hotelBLInfo = HotelBLFactory.getHotelBLService();
    }

    public OrderRoom(RoomType roomType, int quantity, double price) {
        hotelBLInfo = HotelBLFactory.getHotelBLService();
        this.type = roomType;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * 修改数量
     * @param quantity
     * @return 是否修改成功
     */
    public ResultMessage modifyQuantity(int quantity) {
        this.quantity += quantity;
        return ResultMessage.SUCCESS;
    }


    /**
     * 计算房间价格小计
     * @return 房间价格小计
     */
    public double getTotal() {
        return price * quantity;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}
