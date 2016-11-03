package blservice.promotionblservice;

import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import vo.OrderRoomVO;
import vo.PromotionVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/12.
 */
public class PromotionBLService_Stub implements PromotionBLService{

    @Override
    public ResultMessage addPromotion(PromotionVO pvo) {
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
    public ResultMessage updatePromotion(String promotionID, PromotionVO newPvo) {
        if(promotionID.equals("201610120202")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public PromotionVO searchByPromotionID(String promotionID) {
        return new PromotionVO(promotionID, PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, null, null, null, 0, 0);
    }

    @Override
    public ArrayList<PromotionVO> searchHotelPromotionsByHotelID(String HotelID) {
        ArrayList<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();
        PromotionVO promotionVO1 = new PromotionVO("201610120102", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0);
        PromotionVO promotionVO2 = new PromotionVO("201610120103", PromotionType.Hotel_Amount, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0);
        if(HotelID.equals("00000000")){
            promotionVOs.add(promotionVO1);
            promotionVOs.add(promotionVO2);
        }
        return promotionVOs;
    }


    @Override
    public ArrayList<PromotionVO> searchWebPromotions() {
        ArrayList<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();
        PromotionVO promotionVO1 = new PromotionVO("201610120202", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0);
        PromotionVO promotionVO2 = new PromotionVO("201610120203", PromotionType.Hotel_Amount, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "00000000", null, null, 0, 0);
        promotionVOs.add(promotionVO1);
        promotionVOs.add(promotionVO2);
        return promotionVOs;
    }

    @Override
    public double getMinHotelProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID) {
        return 0.80;
    }

    @Override
    public double getMinWebProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID) {
        return 0.80;
    }
}
