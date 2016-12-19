package bl.promotionbl;

import util.DateUtil;
import util.Place;
import vo.PromotionVO;
import vo.Promotion_SpecialPlaceVO;

/**
 * Created by vivian on 16/11/25.
 */
public class Promotion_WebSpecialPlace extends ConcretePromotion {
    public Promotion_WebSpecialPlace(DateUtil dateUtil) {
        super(dateUtil);
    }

    @Override
    public double getPromotionDiscount(PromotionVO promotionVO, Object o) {
        double discount = 0;
        if (o instanceof Place) {
            Place currentPlace = (Place) o;
            Promotion_SpecialPlaceVO promotion_specialPlaceVO = (Promotion_SpecialPlaceVO) promotionVO;
            if (dateUtil.isInRange(promotion_specialPlaceVO.startDate, promotion_specialPlaceVO.endDate)) {
                if (promotion_specialPlaceVO.place == currentPlace) {
                    discount = promotion_specialPlaceVO.promotionDiscount;
                }
            }
        }
        return discount;
    }
}
