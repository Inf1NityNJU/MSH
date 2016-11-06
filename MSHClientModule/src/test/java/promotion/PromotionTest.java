package promotion;

import bl.promotionbl.MockPromotion;
import bl.promotionbl.Promotion;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import vo.PromotionVO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
        ResultMessage rm = promotion.add(new PromotionVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0));
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
        ResultMessage rm = promotion.update("201610120102",new PromotionVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = promotion.update("201610120103",new PromotionVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0));
        assertEquals(ResultMessage.FAILED,rm);
    }

    @Test
    public void testSearchByID(){
        PromotionVO pvo = promotion.searchByID("201610120102");
        assertEquals(new PromotionVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0), pvo);
        pvo = promotion.searchByID("201610120103");
        assertEquals(null, pvo);
    }

    @Test
    public void testSearch(){
        ArrayList<PromotionVO> pvos = promotion.search(PromotionType.Hotel_Birthday);
        ArrayList<PromotionVO> tempPvos = new ArrayList<PromotionVO>();
        tempPvos.add(new PromotionVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0));
        assertEquals(tempPvos, pvos);
        pvos = promotion.search(PromotionType.Hotel_Amount);
        assertEquals(null, pvos);
    }

    @Test
    public void testSearchHotelPromotions(){
        ArrayList<PromotionVO> pvos = promotion.searchHotelPromotions("00000000");
        ArrayList<PromotionVO> tempPvos = new ArrayList<PromotionVO>();
        tempPvos.add(new PromotionVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0));
        assertEquals(tempPvos, pvos);
        pvos = promotion.searchHotelPromotions("00000001");
        assertEquals(null, pvos);
    }

    @Test
    public void testSearchWebPromotions(){
        ArrayList<PromotionVO> pvos = promotion.searchWebPromotions();
        ArrayList<PromotionVO> tempPvos = new ArrayList<PromotionVO>();
        tempPvos.add(new PromotionVO("201610120103", PromotionType.Web_SpecilaDate, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, null, null, null, 0, 0));
        assertEquals(tempPvos, pvos);
    }

}
