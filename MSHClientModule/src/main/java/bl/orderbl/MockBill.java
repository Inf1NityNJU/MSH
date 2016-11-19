package bl.orderbl;

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
     * @param date
     * @param start
     * @param end
     * @param birthday
     * @param hotelID
     * @param quantity
     * @return BillVO
     */
    public BillVO refresh(DateUtil date, DateUtil start, DateUtil end, DateUtil birthday, String hotelID, int quantity) {
        Promotion_HotelVO hotelPromotion = new Promotion_HotelVO("201610130101", PromotionType.Hotel_Birthday, 0.80);
        return new BillVO(null, hotelPromotion, 300, 240);
    }

//    public MockBill(Promotion_HotelVO websitePromotion, Promotion_HotelVO hotelPromotion, double originPrice, double totalPrice) {
//        this.websitePromotion = websitePromotion;
//        this.hotelPromotion = hotelPromotion;
//        this.originPrice = originPrice;
//        this.totalPrice = totalPrice;
//    }
}
