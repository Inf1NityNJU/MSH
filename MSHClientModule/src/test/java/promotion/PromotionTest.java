package promotion;

import blimpl.promotionblimpl.Promotion;
import blimpl.promotionblimpl.PromotionBLFactory;
import org.junit.Test;
import util.*;
import vo.*;

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
        promotion = PromotionBLFactory.getPromotion();
    }

    @Test
    public void testAdd() {
        ResultMessage rm = promotion.add(new Promotion_BirthdayVO("1", PromotionType.Hotel_Birthday, 0.80, "00000000"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = promotion.add(new Promotion_ClientGradeVO("2", PromotionType.Web_ClientGrade, 0.70, new DateUtil(2016,01,02), new DateUtil(2016,02,02), 2));
        assertEquals(ResultMessage.SUCCESS, rm);
        promotion.add(new Promotion_WebSpecialDateVO("3", PromotionType.Web_SpecilaDate, 0.60, new DateUtil(2016,01,02), new DateUtil(2016,02,02)));
        assertEquals(ResultMessage.SUCCESS, rm);
        promotion.add(new Promotion_SpecialPlaceVO("4", PromotionType.Web_SpecilPlace, 0.40, new DateUtil(2016, 01, 02), new DateUtil(2016, 02, 02), City.NanJing, Place.TangShan));
        assertEquals(ResultMessage.SUCCESS, rm);
        promotion.add(new Promotion_HotelSpecialDateVO("5", PromotionType.Hotel_SpecilaDate, 0.60, new DateUtil(2016,01,02), new DateUtil(2016,02,02),"01"));
        assertEquals(ResultMessage.SUCCESS, rm);
        promotion.add(new Promotion_EnterpriseVO("6", PromotionType.Hotel_Enterprise, 0.80, new DateUtil(2016,01,02), new DateUtil(2016,02,02),"001","01"));
        assertEquals(ResultMessage.SUCCESS, rm);
        promotion.add(new Promotion_RoomQuantityVO("7", PromotionType.Hotel_RoomQuantity, 0.65, new DateUtil(2016,01,02), new DateUtil(2016,02,02),"01",5));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testDelete() {
        ResultMessage rm = promotion.delete("10003");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = promotion.delete("10003");
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testUpdate() {
        ResultMessage rm = promotion.update(new Promotion_BirthdayVO("10001", "1", PromotionType.Hotel_Birthday, 0.85, "00000001"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = promotion.update(new Promotion_ClientGradeVO("20001", "2", PromotionType.Web_ClientGrade, 0.75, new DateUtil(2016,01,02), new DateUtil(2016,02,02), 2));
        assertEquals(ResultMessage.SUCCESS, rm);
        promotion.update(new Promotion_WebSpecialDateVO("20002", "3", PromotionType.Web_SpecilaDate, 0.65, new DateUtil(2016,01,02), new DateUtil(2016,02,02)));
        assertEquals(ResultMessage.SUCCESS, rm);
        promotion.update(new Promotion_HotelSpecialDateVO("10002", "5", PromotionType.Hotel_SpecilaDate, 0.65, new DateUtil(2016,01,02), new DateUtil(2016,02,02),"00000001"));
        assertEquals(ResultMessage.SUCCESS, rm);
        promotion.update(new Promotion_RoomQuantityVO("10004", "7", PromotionType.Hotel_RoomQuantity, 0.60, new DateUtil(2016,01,02), new DateUtil(2016,02,02),"00000001",5));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testSearchByID() {
        PromotionVO pvo = promotion.searchByID("10001");
        assertEquals(new Promotion_BirthdayVO("10001", "1", PromotionType.Hotel_Birthday, 0.85, "00000000"), pvo);
        pvo = promotion.searchByID("20001");
        assertEquals(new Promotion_ClientGradeVO("20001", "2", PromotionType.Web_ClientGrade, 0.75, new DateUtil(2016,01,02), new DateUtil(2016,02,02), 2), pvo);
    }

    @Test
    public void testSearch() {
        ArrayList<PromotionVO> pvos = promotion.search(PromotionType.Hotel_Birthday);
        ArrayList<PromotionVO> tempPvos1 = new ArrayList<PromotionVO>();
        tempPvos1.add(new Promotion_BirthdayVO("10001", "1", PromotionType.Hotel_Birthday, 0.85, "00000001"));
        assertEquals(tempPvos1, pvos);
        pvos = promotion.search(PromotionType.Web_SpecilaDate);
        ArrayList<PromotionVO> tempPvos2 = new ArrayList<PromotionVO>();
        tempPvos2.add(new Promotion_WebSpecialDateVO("20002", "3", PromotionType.Web_SpecilaDate, 0.65, new DateUtil(2016,01,02), new DateUtil(2016,02,02)));
        assertEquals(tempPvos2, pvos);
    }


    @Test
    public void testSearchHotelPromotions() {
        ArrayList<PromotionVO> pvos = promotion.searchHotelPromotions("00000001");
        ArrayList<PromotionVO> tempPvos = new ArrayList<PromotionVO>();
        tempPvos.add(new Promotion_BirthdayVO("10001", "1", PromotionType.Hotel_Birthday, 0.85, "00000001"));
        tempPvos.add(new Promotion_HotelSpecialDateVO("10002", "5", PromotionType.Hotel_SpecilaDate, 0.65, new DateUtil(2016,01,02), new DateUtil(2016,02,02),"00000001"));
        tempPvos.add(new Promotion_RoomQuantityVO("10004", "7", PromotionType.Hotel_RoomQuantity, 0.60, new DateUtil(2016,01,02), new DateUtil(2016,02,02),"00000001",5));
        assertEquals(tempPvos, pvos);
    }

    @Test
    public void testSearchWebPromotions() {
        ArrayList<PromotionVO> pvos = promotion.searchWebPromotions();
        ArrayList<PromotionVO> tempPvos = new ArrayList<PromotionVO>();
        tempPvos.add(new Promotion_ClientGradeVO("20001", "2", PromotionType.Web_ClientGrade, 0.75, new DateUtil(2016,01,02), new DateUtil(2016,02,02), 2));
        tempPvos.add(new Promotion_WebSpecialDateVO("20002", "3", PromotionType.Web_SpecilaDate, 0.65, new DateUtil(2016,01,02), new DateUtil(2016,02,02)));
        assertEquals(tempPvos, pvos);
    }

    @Test
    public void testOrder(){
        promotion.POToVO(null);
    }
}
