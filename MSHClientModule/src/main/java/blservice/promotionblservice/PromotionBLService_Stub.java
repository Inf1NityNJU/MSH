package blservice.promotionblservice;

import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/12.
 */
public class PromotionBLService_Stub implements PromotionBLService {

    @Override
    public ResultMessage addPromotion(Promotion_HotelVO pvo) {
        if (pvo.promotionID.equals("201610130101")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }

    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        if (promotionID.equals("201610120102")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }

    }

    @Override
    public ResultMessage updatePromotion(String promotionID, Promotion_HotelVO newPvo) {
        if (promotionID.equals("201610120202")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    @Override
    public Promotion_HotelVO searchByPromotionID(String promotionID) {
        return new Promotion_HotelVO(promotionID, PromotionType.Hotel_Birthday, 0.80);
    }

    @Override
    public ArrayList<Promotion_HotelVO> searchPromotions(PromotionType promotionType) {
        ArrayList<Promotion_HotelVO> promotionVOs = new ArrayList<Promotion_HotelVO>();
        Promotion_HotelVO promotionVO1 = new Promotion_HotelVO("201610120102", PromotionType.Hotel_Birthday, 0.80);
        Promotion_HotelVO promotionVO2 = new Promotion_HotelVO("201610120103", PromotionType.Hotel_Birthday, 0.80);
        if (promotionType == PromotionType.Hotel_Birthday) {
            promotionVOs.add(promotionVO1);
            promotionVOs.add(promotionVO2);
        }
        return promotionVOs;
    }


    @Override
    public ArrayList<Promotion_HotelVO> searchHotelPromotions(String HotelID) {
        ArrayList<Promotion_HotelVO> promotionVOs = new ArrayList<Promotion_HotelVO>();
        Promotion_HotelVO promotionVO1 = new Promotion_HotelVO("201610120102", PromotionType.Hotel_Birthday, 0.80);
        Promotion_HotelVO promotionVO2 = new Promotion_HotelVO("201610120103", PromotionType.Hotel_Amount, 0.80);
        if (HotelID.equals("00000000")) {
            promotionVOs.add(promotionVO1);
            promotionVOs.add(promotionVO2);
        }
        return promotionVOs;
    }


    @Override
    public ArrayList<Promotion_WebVO> searchWebPromotions() {
        ArrayList<Promotion_WebVO> promotionWebVOs = new ArrayList<Promotion_WebVO>();
        Promotion_WebVO promotionWebVO1 = new Promotion_WebVO("201610120202", PromotionType.Web_SpecilaDate, 0.80, new DateUtil(2016, 10, 01), new DateUtil(2016, 10, 03));
        Promotion_WebVO promotionWebVO2 = new Promotion_WebVO("201610120203", PromotionType.Web_ClientGrade, 0.80, new DateUtil(2016, 10, 01), new DateUtil(2016, 10, 03));
        promotionWebVOs.add(promotionWebVO1);
        promotionWebVOs.add(promotionWebVO2);
        return promotionWebVOs;
    }

}
