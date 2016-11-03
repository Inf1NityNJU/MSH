package bl.orderbl;

import util.ResultMessage;
import util.RoomType;

/**
 * Created by Sorumi on 16/11/1.
 */
public class OrderRoom {

    public ResultMessage modifyQuantity(RoomType roomtype, int quantity) {
        return ResultMessage.SUCCESS;
    }

    public double getTotal() {
        return 0.0;
    }
}
