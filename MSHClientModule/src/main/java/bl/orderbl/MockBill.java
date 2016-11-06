package bl.orderbl;

import util.DateUtil;
import util.PromotionType;
import vo.BillVO;
import vo.PromotionVO;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockBill extends Bill {

    private PromotionVO websitePromotion;
    private PromotionVO hotelPromotion;
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
        PromotionVO hotelPromotion = new PromotionVO("201610130101", PromotionType.Hotel_Birthday, new DateUtil(2016,10,01), new DateUtil(2016,10,03), 0.80, "01011234", null, null, 0, 0);
        return new BillVO(null, hotelPromotion, 300, 240);
    }

//    public MockBill(PromotionVO websitePromotion, PromotionVO hotelPromotion, double originPrice, double totalPrice) {
//        this.websitePromotion = websitePromotion;
//        this.hotelPromotion = hotelPromotion;
//        this.originPrice = originPrice;
//        this.totalPrice = totalPrice;
//    }
}
