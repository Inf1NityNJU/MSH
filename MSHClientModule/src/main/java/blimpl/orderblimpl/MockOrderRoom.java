package blimpl.orderblimpl;

import util.ResultMessage;
import util.RoomType;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockOrderRoom extends OrderRoom {

    private RoomType type;

    private double price;

    private int quantity;

    /**
     * 修改数量
     * @param quantity
     * @return 是否修改成功
     */
    public ResultMessage modifyQuantity(int quantity) {
        this.quantity += quantity;

        if (this.quantity > 2) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }

    }

    /**
     * 计算房间价格小计
     * @return 房间价格小计
     */
    public double getTotal() {
        return this.price * this.quantity;
    }

    public MockOrderRoom(RoomType type, int quantity, double price) {
        super(type, quantity, price);
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
}
