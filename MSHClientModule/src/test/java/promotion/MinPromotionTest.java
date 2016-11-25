package promotion;

import bl.promotionbl.MinPromotion;
import bl.promotionbl.MockMinPromotion;
import org.junit.Test;
import util.DateUtil;
import util.Place;
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
    public void testMinWebProm(){
        double discount = minPromotion.getMinWebProm(new DateUtil(2016,11,06), 2, Place.TangShan);
        assertEquals(0.80, discount, 0);
    }




    @Test
        public void testMinHotelProm(){
        double discount = minPromotion.getMinHotelProm("00000000", new DateUtil(2016,11,06), new DateUtil(1997,06,14), "SIJIA", 2);
        assertEquals(0.80, discount, 0);
    }
}
