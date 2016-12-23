package blimpl.promotionblimpl;

/**
 * Created by vivian on 16/12/3.
 */
public class PromotionBLFactory {
    private static PromotionBLServiceImpl promotionBLServiceImpl;

    public synchronized static PromotionBLServiceImpl getPromotionBLService() {
        if (promotionBLServiceImpl == null) {
            promotionBLServiceImpl = new PromotionBLServiceImpl();
        }
        return promotionBLServiceImpl;
    }
}
