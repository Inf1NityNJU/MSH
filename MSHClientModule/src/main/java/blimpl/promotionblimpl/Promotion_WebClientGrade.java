package blimpl.promotionblimpl;

import util.DateUtil;
import vo.PromotionVO;
import vo.Promotion_ClientGradeVO;

/**
 * Created by vivian on 16/11/25.
 */
public class Promotion_WebClientGrade extends ConcretePromotion {

    public Promotion_WebClientGrade(DateUtil dateUtil) {
        super(dateUtil);
    }

    @Override
    public double getPromotionDiscount(PromotionVO promotionVO, Object o) {
        double discount = 0;
        if (o instanceof Integer) {
            int clientGrade = (Integer) o;
            Promotion_ClientGradeVO promotion_clientGradeVO = (Promotion_ClientGradeVO) promotionVO;
            if (dateUtil.isInRange(promotion_clientGradeVO.startDate, promotion_clientGradeVO.endDate)
                    && clientGrade >= promotion_clientGradeVO.clientGrade) {
                discount = promotion_clientGradeVO.promotionDiscount;
            }
        }
        return discount;
    }
}
