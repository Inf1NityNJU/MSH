package blimpl.promotionblimpl;

import util.DateUtil;
import vo.PromotionVO;
import vo.Promotion_BirthdayVO;

/**
 * Created by vivian on 16/11/25.
 */
public class Promotion_HotelBirthday extends ConcretePromotion {
    public Promotion_HotelBirthday(DateUtil dateUtil) {
        super(dateUtil);
    }

    @Override
    public double getPromotionDiscount(PromotionVO promotionVO, Object o) {
        double discount = 0;
        if (o instanceof DateUtil) {
            DateUtil birthday = (DateUtil) o;
            Promotion_BirthdayVO promotion_birthdayVO = (Promotion_BirthdayVO) promotionVO;
            if (dateUtil.month == birthday.month && dateUtil.day == birthday.day) {
                discount = promotion_birthdayVO.promotionDiscount;
            }
        }
        return discount;
    }
}
