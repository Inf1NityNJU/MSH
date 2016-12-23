package blimpl.promotionblimpl;

import util.DateUtil;
import vo.PromotionVO;

/**
 * Created by vivian on 16/11/25.
 */
public class ConcretePromotion {
    public DateUtil dateUtil;

    public ConcretePromotion(DateUtil dateUtil){
        this.dateUtil = dateUtil;
    }

    public double getPromotionDiscount(PromotionVO promotionVO , Object o){return 0;}

    public double getPromotionDiscount(PromotionVO promotionVO , Object o1, Object o2){return 0;}
}
