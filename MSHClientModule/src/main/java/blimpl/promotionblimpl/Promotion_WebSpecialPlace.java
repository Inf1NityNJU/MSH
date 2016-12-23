package blimpl.promotionblimpl;

import util.City;
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
    public double getPromotionDiscount(PromotionVO promotionVO, Object o1, Object o2) {
        double discount = 0;
        if (o1 instanceof City && o2 instanceof Place) {
            City currentCity = (City) o1;
            Place currentPlace = (Place) o2;
            Promotion_SpecialPlaceVO promotion_specialPlaceVO = (Promotion_SpecialPlaceVO) promotionVO;
            if (dateUtil.isInRange(promotion_specialPlaceVO.startDate, promotion_specialPlaceVO.endDate)) {
                if (promotion_specialPlaceVO.city == currentCity
                        && promotion_specialPlaceVO.place == currentPlace) {
                    discount = promotion_specialPlaceVO.promotionDiscount;
                }
            }
        }
        return discount;
    }
}
