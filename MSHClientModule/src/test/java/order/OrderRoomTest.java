package order;

import blimpl.orderblimpl.MockOrderRoom;
import blimpl.orderblimpl.OrderRoom;
import util.ResultMessage;
import util.RoomType;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Sorumi on 16/11/6.
 */
public class OrderRoomTest {

    @Test
    public void TestModifyQuantity() {
        OrderRoom orderroom = new MockOrderRoom(RoomType.SingleRoom, 200, 1);
        ResultMessage rm = orderroom.modifyQuantity(1);
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = orderroom.modifyQuantity(1);
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void TestGetTotal() {
        OrderRoom orderroom = new MockOrderRoom(RoomType.SingleRoom, 200, 3);
        double total = orderroom.getTotal();
        assertEquals(600, total, 0);
    }
}
