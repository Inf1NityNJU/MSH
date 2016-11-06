package order;

import bl.orderbl.Bill;
import bl.orderbl.MockBill;
import util.DateUtil;
import vo.BillVO;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Sorumi on 16/11/6.
 */
public class BillTest {

    private Bill bill;

    public BillTest() {
        bill = new MockBill();
    }

    @Test
    public void TestRefresh() {
        BillVO billVO = bill.refresh(new DateUtil(2016, 10, 26), new DateUtil(2016, 10, 29), new DateUtil(2016, 10, 30),
                new DateUtil(1996, 6, 11), "01011234", 3);
        assertNotNull(billVO);
    }


}
