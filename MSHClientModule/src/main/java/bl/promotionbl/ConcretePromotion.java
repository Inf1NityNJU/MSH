package bl.promotionbl;

import util.DateUtil;
import vo.PromotionVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/25.
 */
public class ConcretePromotion {
    public DateUtil dateUtil;

    public ConcretePromotion(DateUtil dateUtil){
        this.dateUtil = dateUtil;
    }

    public double getPromotionDiscount(PromotionVO promotionVO , Object o){return 0;}
}
