package bl.orderbl;

import util.ResultMessage;
import util.RoomType;

/**
 * Created by Sorumi on 16/11/1.
 */
public class OrderRoom {

    /**
     * 修改数量
     * @param quantity
     * @return 是否修改成功
     */
    public ResultMessage modifyQuantity(int quantity) {
        return ResultMessage.SUCCESS;
    }


    /**
     * 计算房间价格小计
     * @return 房间价格小计
     */
    public double getTotal() {
        return 0.0;
    }
}
