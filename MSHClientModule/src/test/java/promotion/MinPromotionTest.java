package promotion;

import bl.promotionbl.MinPromotion;
import bl.promotionbl.MockMinPromotion;
import org.junit.Test;
import util.DateUtil;
import vo.OrderRoomVO;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * Created by vivian on 16/11/6.
 */
public class MinPromotionTest {
    private MinPromotion minPromotion;

    public MinPromotionTest(){
        minPromotion = new MockMinPromotion();
    }


    @Test
    private void testMinWebProm(){
        double discount = minPromotion.getMinWebProm(new DateUtil(2016,11,06), new ArrayList<OrderRoomVO>(), "000000007", "00000000");
        assertEquals(0.80, discount, 0);
    }

    private void testMinHotelProm(){
        double discount = minPromotion.getMinWebProm(new DateUtil(2016,11,06), new ArrayList<OrderRoomVO>(), "000000007", "00000000");
        assertEquals(0.80, discount, 0);
    }
}
