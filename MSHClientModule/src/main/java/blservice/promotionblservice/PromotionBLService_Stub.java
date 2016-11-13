package blservice.promotionblservice;

import util.DateUtil;
import util.Place;
import util.PromotionType;
import util.ResultMessage;
import vo.PromotionHotelVO;
import vo.PromotionWebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/12.
 */
public class PromotionBLService_Stub implements PromotionBLService{

    @Override
    public ResultMessage addPromotion(PromotionHotelVO pvo) {
        if(pvo.promotionID.equals("201610130101")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }

    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        if(promotionID.equals("201610120102")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }

    }

    @Override
    public ResultMessage updatePromotion(String promotionID, PromotionHotelVO newPvo) {
        if(promotionID.equals("201610120202")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public PromotionHotelVO searchByPromotionID(String promotionID) {
        return new PromotionHotelVO(promotionID, PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, null, null, 0);
    }

    @Override
    public ArrayList<PromotionHotelVO> searchPromotions(PromotionType promotionType) {
        ArrayList<PromotionHotelVO> promotionVOs = new ArrayList<PromotionHotelVO>();
        PromotionHotelVO promotionVO1 = new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, 0);
        PromotionHotelVO promotionVO2 = new PromotionHotelVO("201610120103", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, 0);
        if(promotionType==PromotionType.Hotel_Birthday){
            promotionVOs.add(promotionVO1);
            promotionVOs.add(promotionVO2);
        }
        return promotionVOs;
    }


    @Override
    public ArrayList<PromotionHotelVO> searchHotelPromotions(String HotelID) {
        ArrayList<PromotionHotelVO> promotionVOs = new ArrayList<PromotionHotelVO>();
        PromotionHotelVO promotionVO1 = new PromotionHotelVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, 0);
        PromotionHotelVO promotionVO2 = new PromotionHotelVO("201610120103", PromotionType.Hotel_Amount, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, 0);
        if(HotelID.equals("00000000")){
            promotionVOs.add(promotionVO1);
            promotionVOs.add(promotionVO2);
        }
        return promotionVOs;
    }


    @Override
    public ArrayList<PromotionWebVO> searchWebPromotions() {
        ArrayList<PromotionWebVO> promotionWebVOs = new ArrayList<PromotionWebVO>();
        PromotionWebVO promotionWebVO1 = new PromotionWebVO("201610120202", PromotionType.Web_SpecilaDate, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, null,0);
        PromotionWebVO promotionWebVO2 = new PromotionWebVO("201610120203", PromotionType.Web_ClientGrade, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, null, 2);
        promotionWebVOs.add(promotionWebVO1);
        promotionWebVOs.add(promotionWebVO2);
        return promotionWebVOs;
    }

}
