package bl.orderbl;

import util.DateUtil;
import util.PromotionType;
import vo.BillVO;
import vo.PromotionHotelVO;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockBill extends Bill {

    private PromotionHotelVO websitePromotion;
    private PromotionHotelVO hotelPromotion;
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
        PromotionHotelVO hotelPromotion = new PromotionHotelVO("201610130101", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "01011234", null, 0);
        return new BillVO(null, hotelPromotion, 300, 240);
    }

//    public MockBill(PromotionHotelVO websitePromotion, PromotionHotelVO hotelPromotion, double originPrice, double totalPrice) {
//        this.websitePromotion = websitePromotion;
//        this.hotelPromotion = hotelPromotion;
//        this.originPrice = originPrice;
//        this.totalPrice = totalPrice;
//    }
}
