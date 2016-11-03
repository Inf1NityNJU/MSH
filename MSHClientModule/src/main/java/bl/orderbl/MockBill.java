package bl.orderbl;

import util.DateUtil;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockBill extends Bill {

    private double originPrice;
    private double totalPrice;

    public Bill refresh(DateUtil date, DateUtil start, DateUtil end, DateUtil birthday, String hotelID, int quantity) {
        return new MockBill(300, 300);
    }

    public MockBill(double originPrice, double totalPrice) {
        this.originPrice = originPrice;
        this.totalPrice = totalPrice;
    }
}
