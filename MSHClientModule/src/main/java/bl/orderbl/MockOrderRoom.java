package bl.orderbl;

import util.ResultMessage;
import util.RoomType;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockOrderRoom extends OrderRoom {

    private RoomType type;

    private double price;

    private int quantity;

    public ResultMessage modifyQuantity(RoomType roomtype, int quantity) {

        if (roomtype == RoomType.SingleRoom) {
            return ResultMessage.FAILED;
        } else {
            return ResultMessage.SUCCESS;
        }

    }

    public double getTotal() {
        return this.price * this.quantity;
    }

    public MockOrderRoom(RoomType type, double price, int quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
}
