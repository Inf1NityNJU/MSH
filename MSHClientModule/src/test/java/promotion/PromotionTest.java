package promotion;

import bl.promotionbl.MockPromotion;
import bl.promotionbl.Promotion;
import org.junit.Test;
import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import vo.PromotionVO;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by vivian on 16/11/6.
 */
public class PromotionTest {
    private Promotion promotion;

    public PromotionTest() {
        promotion = new MockPromotion();
    }

    @Test
    public void testAdd() {
        ResultMessage rm = promotion.add(new Promotion_HotelVO("201610120102", PromotionType.Hotel_Birthday, 0.80, "00000000"));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testDelete() {
        ResultMessage rm = promotion.delete("201610120102");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = promotion.delete("201610120103");
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testUpdate() {
        ResultMessage rm = promotion.update(new Promotion_HotelVO("201610120102", PromotionType.Hotel_Birthday, 0.80, "00000000"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = promotion.update(new Promotion_HotelVO("201610120103", PromotionType.Hotel_Birthday, 0.80, "00000000"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchByID() {
        PromotionVO pvo = promotion.searchByID("201610120102");
//        assertFalse(!pvo.equals(new Promotion_HotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0)));
        assertEquals(new Promotion_HotelVO("201610120102", PromotionType.Hotel_Birthday, 0.80, "00000000"), pvo);
//        assertNotNull(pvo);
        pvo = promotion.searchByID("201610120103");
        assertEquals(null, pvo);
    }

    @Test
    public void testSearch() {
        ArrayList<PromotionVO> pvos = promotion.search(PromotionType.Hotel_Birthday);
        ArrayList<PromotionVO> tempPvos = new ArrayList<PromotionVO>();
        tempPvos.add(new Promotion_HotelVO("201610120102", PromotionType.Hotel_Birthday, 0.80, "00000000"));
        assertEquals(tempPvos, pvos);
        pvos = promotion.search(PromotionType.Hotel_RoomQuantity);
        assertEquals(null, pvos);
    }

    @Test
    public void testSearchHotelPromotions() {
        ArrayList<PromotionVO> pvos = promotion.searchHotelPromotions("00000000");
        ArrayList<PromotionVO> tempPvos = new ArrayList<PromotionVO>();
        tempPvos.add(new Promotion_HotelVO("201610120102", PromotionType.Hotel_Birthday, 0.80, "00000000"));
        assertEquals(tempPvos, pvos);
        pvos = promotion.searchHotelPromotions("00000001");
        assertEquals(null, pvos);
    }

    @Test
    public void testSearchWebPromotions() {
        ArrayList<PromotionVO> pvos = promotion.searchWebPromotions();
        ArrayList<PromotionVO> tempPvos = new ArrayList<PromotionVO>();
        tempPvos.add(new Promotion_WebVO("201610120103", PromotionType.Web_SpecilaDate, 0.80, new DateUtil(2016, 10, 01), new DateUtil(2016, 10, 03)));
        assertEquals(tempPvos, pvos);
    }

}
