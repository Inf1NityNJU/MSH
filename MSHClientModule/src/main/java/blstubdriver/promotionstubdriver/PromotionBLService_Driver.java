package blstubdriver.promotionstubdriver;

import blimpl.promotionblimpl.PromotionBLFactory;
import blservice.promotionblservice.PromotionBLService;
import org.junit.Test;
import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import util.RoomType;
import vo.*;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/13.
 */
public class PromotionBLService_Driver {

    @Test
    public void test() {
        PromotionBLService promotionBLService = PromotionBLFactory.getPromotionBLServiceForTest();
        driver(promotionBLService);
    }

    public void driver(PromotionBLService promotionBLService) {
        PromotionVO pvo = new Promotion_BirthdayVO("test1", PromotionType.Hotel_Birthday, 0.80, "00000001");

        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);

        ResultMessage result = promotionBLService.addPromotion(pvo);
        if (result == ResultMessage.SUCCESS) {
            System.out.println("Add Promotion Success");
        } else {
            System.out.println("Add Promotion Failed");
        }
        promotionBLService.addPromotion(new Promotion_ClientGradeVO("test2", PromotionType.Web_ClientGrade, 0.7, new DateUtil("2016-10-01"), new DateUtil("2016-11-11"), 3));

        result = promotionBLService.deletePromotion("20001");
        if (result == ResultMessage.SUCCESS) {
            System.out.println("Delete Promotion Success");
        } else {
            System.out.println("Delete Promotion Failed");
        }

        pvo = new Promotion_BirthdayVO("10001", "test1", PromotionType.Hotel_Birthday, 0.60, "00000001");
        result = promotionBLService.updatePromotion(pvo);
        if (result == ResultMessage.SUCCESS) {
            System.out.println("Update Promotion Success");
        } else {
            System.out.println("Update Promotion Failed");
        }

        PromotionVO promotionVO = promotionBLService.searchByPromotionID("10001");
        if (promotionVO != null) {
            System.out.println("Get Promotion Success");
        } else {
            System.out.println("Get Promotion Failed");
        }

        ArrayList<PromotionVO> pvos = promotionBLService.searchPromotions(PromotionType.Hotel_Birthday);
        if (pvos != null) {
            System.out.println("Get Promotions for certain type Success");
        } else {
            System.out.println("Get Promotions for certain type Failed");
        }

        pvos = promotionBLService.searchHotelPromotions("00000001");
        if (pvos != null) {
            System.out.println("Get HotelPromotions Success");
        } else {
            System.out.println("Get HotelPromotions Failed");
        }

        ArrayList<PromotionVO> pwvos = promotionBLService.searchWebPromotions();
        if (pvos != null) {
            System.out.println("Get WebPromotions Success");
        } else {
            System.out.println("Get WebPromotions Failed");
        }

    }
}
