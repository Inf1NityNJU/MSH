package blimpl.orderblimpl;

import util.DateUtil;
import util.PromotionType;
import vo.BillVO;
import vo.Promotion_HotelVO;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockBill extends Bill {

    private Promotion_HotelVO websitePromotion;
    private Promotion_HotelVO hotelPromotion;
    private double originPrice;
    private double totalPrice;

    /**
     * 更新账单信息
     * @param hotelID
     * @param date
     * @param birthday
     * @param enterpriseName
     * @param roomQuantity
     * @return
     */
    public BillVO refresh(String hotelID, DateUtil date, DateUtil birthday, String enterpriseName, int roomQuantity) {
        Promotion_HotelVO hotelPromotion = new Promotion_HotelVO("生日折扣", PromotionType.Hotel_Birthday, 0.80,"00000000");
        return new BillVO(null, hotelPromotion, 0, 0);
    }

//    public MockBill(Promotion_HotelVO websitePromotion, Promotion_HotelVO hotelPromotion, double originPrice, double totalPrice) {
//        this.websitePromotion = websitePromotion;
//        this.hotelPromotion = hotelPromotion;
//        this.originPrice = originPrice;
//        this.totalPrice = totalPrice;
//    }
}
