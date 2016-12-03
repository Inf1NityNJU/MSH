package bl.orderbl;

import blservice.promotionblservice.PromotionBLInfo;
import util.DateUtil;
import vo.BillVO;

/**
 * Created by Sorumi on 16/11/1.
 */
public class Bill {

    private PromotionBLInfo promotionBLInfo;

    public Bill() {
//        promotionBLInfo = new Prom
    }


    /**
     * 更新账单日期
     * @param hotelID
     * @param date
     * @param birthday
     * @param enterpriseName
     * @param roomQuantity
     * @return
     */
    public BillVO refresh(String hotelID, DateUtil date, DateUtil birthday, String enterpriseName, int roomQuantity) {
        return null;
    }
}
