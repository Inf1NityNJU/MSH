package blservice.promotionblservice;

import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import util.RoomType;
import vo.OrderRoomVO;
import vo.PromotionVO;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/13.
 */
public class PromotionBLService_Driver {

    public void drive(PromotionBLService promotionBLService) {
        Promotion_HotelVO pvo = new Promotion_HotelVO("201610130101", PromotionType.Hotel_Birthday, 0.80,"00000000");

        ArrayList<OrderRoomVO> rooms = new ArrayList<OrderRoomVO>();
        OrderRoomVO room1 = new OrderRoomVO(RoomType.DoubleRoom, 300, 1);
        rooms.add(room1);

        ResultMessage result = promotionBLService.addPromotion(pvo);
        if (result == ResultMessage.SUCCESS) {
            System.out.println("Add Success");
        } else {
            System.out.println("Add Failed");
        }

        result = promotionBLService.deletePromotion("201610120102");
        if (result == ResultMessage.SUCCESS) {
            System.out.println("Delete Success");
        } else {
            System.out.println("Delete Failed");
        }

        result = promotionBLService.updatePromotion(pvo);
        if (result == ResultMessage.SUCCESS) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failed");
        }

        PromotionVO promotionVO = promotionBLService.searchByPromotionID("201610120201");
        if (promotionVO != null) {
            System.out.println("Get Success");
        } else {
            System.out.println("Get Failed");
        }

        ArrayList<PromotionVO> pvos = promotionBLService.searchPromotions(PromotionType.Hotel_Birthday);
        if (pvos != null) {
            System.out.println("Get Promotions Success");
        } else {
            System.out.println("Get Promotions Failed");
        }

        pvos = promotionBLService.searchHotelPromotions("00000000");
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
