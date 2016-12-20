package promotion;

import bl.promotionbl.MinPromotion;
import bl.promotionbl.MockMinPromotion;
import org.junit.Test;
import util.City;
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
        Promotion_WebVO promotion = minPromotion.getMinWebProm(new DateUtil(2016,1,10), 2, City.NanJing, Place.XianLin);
        assertEquals(0.50, promotion.promotionDiscount, 0);
    }




    @Test
        public void testMinHotelProm(){
        Promotion_HotelVO promotion = minPromotion.getMinHotelProm("00000001", new DateUtil(2016,01,12), new DateUtil(2016,01,11), "001", 3);
        assertEquals(0.8, promotion.promotionDiscount, 0);
    }
}
