package bl.orderbl;

import util.DateUtil;
import vo.BillVO;

/**
 * Created by Sorumi on 16/11/1.
 */
public class MockBill extends Bill {

    private double originPrice;
    private double totalPrice;

    public BillVO refresh(DateUtil date, DateUtil start, DateUtil end, DateUtil birthday, String hotelID, int quantity) {
        return new BillVO(null, null , 300, 300);
    }

    public MockBill(double originPrice, double totalPrice) {
        this.originPrice = originPrice;
        this.totalPrice = totalPrice;
    }
}
