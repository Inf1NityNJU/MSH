package promotion;

import bl.promotionbl.MinPromotion;
import bl.promotionbl.MockMinPromotion;
import org.junit.Test;
import util.DateUtil;
import util.Place;
import vo.OrderRoomVO;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * Created by vivian on 16/11/6.
 */
public class MinPromotionTest {
    private MinPromotion minPromotion;

    public MinPromotionTest(){
        minPromotion = new MinPromotion();
    }

    @Test
    public void testMinWebProm(){
        Promotion_WebVO promotion = minPromotion.getMinWebProm(new DateUtil(2016,01,11), 2, Place.TangShan);
        assertEquals(0.50, promotion.promotionDiscount, 0);
    }




    @Test
        public void testMinHotelProm(){
        Promotion_HotelVO promotion = minPromotion.getMinHotelProm("02", new DateUtil(2016,01,11), new DateUtil(2016,01,11), "001", 5);
        assertEquals(0.40, promotion.promotionDiscount, 0);
    }
}
