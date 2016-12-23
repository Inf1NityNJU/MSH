package blimpl.promotionblimpl;

import util.DateUtil;
import vo.PromotionVO;
import vo.Promotion_WebSpecialDateVO;

/**
 * Created by vivian on 16/11/25.
 */
public class Promotion_WebSpecialDate extends ConcretePromotion {
    public Promotion_WebSpecialDate(DateUtil dateUtil) {
        super(dateUtil);
    }

    @Override
    public double getPromotionDiscount(PromotionVO promotionVO, Object o) {
        double discount = 0;
        if (o instanceof DateUtil) {
            DateUtil currentDate = (DateUtil) o;
            Promotion_WebSpecialDateVO promotion_webSpecialDateVO = (Promotion_WebSpecialDateVO) promotionVO;
            if (dateUtil.isInRange(promotion_webSpecialDateVO.startDate, promotion_webSpecialDateVO.endDate)) {
                discount = promotion_webSpecialDateVO.promotionDiscount;
            }
        }
        return discount;
    }
}
