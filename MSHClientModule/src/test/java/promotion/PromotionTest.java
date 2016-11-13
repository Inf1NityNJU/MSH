package promotion;

import bl.promotionbl.MockPromotion;
import bl.promotionbl.Promotion;
import org.junit.Test;
import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import vo.PromotionHotelVO;
import vo.PromotionWebVO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by vivian on 16/11/6.
 */
public class PromotionTest {
    private Promotion promotion;

    public PromotionTest(){
        promotion = new MockPromotion();
    }

    @Test
    public void testAdd(){
        ResultMessage rm = promotion.add(new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null,0));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testDelete(){
        ResultMessage rm = promotion.delete("201610120102");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = promotion.delete("201610120103");
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testUpdate(){
        ResultMessage rm = promotion.update("201610120102",new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null,  0));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = promotion.update("201610120103",new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, 0));
        assertEquals(ResultMessage.FAILED,rm);
    }

    @Test
    public void testSearchByID(){
        PromotionHotelVO pvo = promotion.searchByID("201610120102");
//        assertFalse(!pvo.equals(new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0)));
        assertEquals(new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, 0), pvo);
//        assertNotNull(pvo);
        pvo = promotion.searchByID("201610120103");
        assertEquals(null, pvo);
    }

    @Test
    public void testSearch(){
        ArrayList<PromotionHotelVO> pvos = promotion.search(PromotionType.Hotel_Birthday);
        ArrayList<PromotionHotelVO> tempPvos = new ArrayList<PromotionHotelVO>();
        tempPvos.add(new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, 0));
        assertEquals(tempPvos, pvos);
        pvos = promotion.search(PromotionType.Hotel_Amount);
        assertEquals(null, pvos);
    }

    @Test
    public void testSearchHotelPromotions(){
        ArrayList<PromotionHotelVO> pvos = promotion.searchHotelPromotions("00000000");
        ArrayList<PromotionHotelVO> tempPvos = new ArrayList<PromotionHotelVO>();
        tempPvos.add(new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null,0));
        assertEquals(tempPvos, pvos);
        pvos = promotion.searchHotelPromotions("00000001");
        assertEquals(null, pvos);
    }

    @Test
    public void testSearchWebPromotions(){
        ArrayList<PromotionWebVO> pvos = promotion.searchWebPromotions();
        ArrayList<PromotionWebVO> tempPvos = new ArrayList<PromotionWebVO>();
        tempPvos.add(new PromotionWebVO("201610120103", PromotionType.Web_SpecilaDate, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, null,  0));
        assertEquals(tempPvos, pvos);
    }

}
