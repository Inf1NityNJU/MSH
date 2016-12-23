package blimpl.promotionblimpl;

import network.PromotionClientNetworkImpl;
import network.PromotionClientNetworkService;

/**
 * Created by vivian on 16/12/3.
 */
public class PromotionBLFactory {
    private static PromotionBLServiceImpl promotionBLServiceImpl;

    public synchronized static PromotionBLServiceImpl getPromotionBLService() {
        if (promotionBLServiceImpl == null) {
            promotionBLServiceImpl = new PromotionBLServiceImpl(getPromotion(), getMinPromotion());
        }
        return promotionBLServiceImpl;
    }

    public synchronized static Promotion getPromotion() {
        return new Promotion(getPromotionClientNetworkService());
    }

    public synchronized static MinPromotion getMinPromotion() {
        return new MinPromotion(getPromotion());
    }

    //test
    public synchronized static PromotionBLServiceImpl getPromotionBLServiceForTest() {
       return new PromotionBLServiceImpl(getPromotion(), getMinPromotion());
    }


    //private
    private synchronized static PromotionClientNetworkService getPromotionClientNetworkService() {
        return new PromotionClientNetworkImpl();
    }
}
