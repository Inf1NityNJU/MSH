package blimpl.orderblimpl;

import util.ResultMessage;
import util.RoomType;

/**
 * Created by Sorumi on 16/11/1.
 */
public class OrderRoom {

    private RoomType type;
    private double price;
    private int quantity;

    public OrderRoom(RoomType roomType, int quantity, double price) {
        this.type = roomType;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * 修改数量
     *
     * @param quantity
     * @return 是否修改成功
     */
    public ResultMessage modifyQuantity(int quantity) {
        this.quantity += quantity;

        if (this.quantity > 0) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.NULL;
        }

    }


    /**
     * 计算房间价格小计
     *
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
