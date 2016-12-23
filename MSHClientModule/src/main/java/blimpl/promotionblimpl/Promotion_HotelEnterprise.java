package blimpl.promotionblimpl;

import util.DateUtil;
import vo.PromotionVO;
import vo.Promotion_EnterpriseVO;

/**
 * Created by vivian on 16/11/25.
 */
public class Promotion_HotelEnterprise extends ConcretePromotion {
    public Promotion_HotelEnterprise(DateUtil dateUtil) {
        super(dateUtil);
    }

    @Override
    public double getPromotionDiscount(PromotionVO promotionVO, Object o) {
        double discount = 0;
        if (o instanceof String) {
            String currentEnterprise = (String) o;
            Promotion_EnterpriseVO promotion_enterpriseVO = (Promotion_EnterpriseVO) promotionVO;
            if (dateUtil.isInRange(promotion_enterpriseVO.startDate, promotion_enterpriseVO.endDate)) {

                System.out.println();
                if (promotion_enterpriseVO.enterpriseName.equals(currentEnterprise)) {
                    discount = promotion_enterpriseVO.promotionDiscount;
                }
            }
        }
        return discount;
    }
}
