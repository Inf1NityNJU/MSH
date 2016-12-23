package blimpl.promotionblimpl;

import util.DateUtil;
import vo.PromotionVO;
import vo.Promotion_HotelSpecialDateVO;

/**
 * Created by vivian on 16/11/25.
 */
public class Promotion_HotelSpecialDate extends ConcretePromotion {
    public Promotion_HotelSpecialDate(DateUtil dateUtil) {
        super(dateUtil);
    }

    @Override
    public double getPromotionDiscount(PromotionVO promotionVO, Object o) {
        double discount = 0;
        if (o instanceof DateUtil) {
            Promotion_HotelSpecialDateVO promotion_hotelSpecialDateVO = (Promotion_HotelSpecialDateVO) promotionVO;
            if (dateUtil.isInRange(promotion_hotelSpecialDateVO.startDate, promotion_hotelSpecialDateVO.endDate)) {
                discount = promotion_hotelSpecialDateVO.promotionDiscount;
            }
        }
        return discount;
    }
}
