package promotion;

import blimpl.promotionblimpl.MinPromotion;
import blimpl.promotionblimpl.PromotionBLFactory;
import org.junit.Test;
import util.City;
import util.DateUtil;
import util.Place;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by vivian on 16/11/6.
 */
public class MinPromotionTest {
    private MinPromotion minPromotion;

    public MinPromotionTest(){
        minPromotion = PromotionBLFactory.getMinPromotion();
    }

    @Test
    public void testMinWebProm(){
        Promotion_WebVO promotion = minPromotion.getMinWebProm(new DateUtil(2016,1,10), 2, City.NanJing, Place.XianLin);
        assertEquals(0.65, promotion.promotionDiscount, 0);
        promotion = minPromotion.getMinWebProm(new DateUtil(2016,12,10), 2, City.NanJing, Place.XianLin);
        assertNull(promotion);
        promotion = minPromotion.getMinWebProm(new DateUtil(2016,12,10), 2, City.NanJing, Place.XianLin);
    }




    @Test
        public void testMinHotelProm(){
        Promotion_HotelVO promotion = minPromotion.getMinHotelProm("00000001", new DateUtil(2016,1,11), new DateUtil(1997,9,15), "名字", 6);
        assertEquals(0.6, promotion.promotionDiscount, 0);
        promotion = minPromotion.getMinHotelProm("00000001", new DateUtil(2016,1,11), new DateUtil(1997,9,15), "ysj", 2);
        assertEquals(0.65, promotion.promotionDiscount, 0);
    }
}
