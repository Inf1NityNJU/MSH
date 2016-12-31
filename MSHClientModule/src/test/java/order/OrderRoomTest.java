package order;

import blimpl.orderblimpl.MockOrderRoom;
import blimpl.orderblimpl.OrderRoom;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import util.ResultMessage;
import util.RoomType;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Sorumi on 16/11/6.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderRoomTest {

    private OrderRoom orderRoom = new OrderRoom(RoomType.SingleRoom, 200, 1);

    @Test
    public void a_testModifyQuantity() {
        ResultMessage rm = orderRoom.modifyQuantity(1);
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void b_testGetTotal() {
        double total = orderRoom.getTotal();
        assertEquals(200, total, 0);
    }
}
