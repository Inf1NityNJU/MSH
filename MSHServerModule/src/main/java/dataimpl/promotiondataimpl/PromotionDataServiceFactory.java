package dataimpl.promotiondataimpl;

import datahelper.HibernateHelper;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;

/**
 * Created by vivian on 16/11/22.
 */
public class PromotionDataServiceFactory {
    private static PromotionDataServiceImpl promotionDataService;

    public static synchronized PromotionDataService getPromotionDataService() {
        if (promotionDataService == null) {
            promotionDataService = new PromotionDataServiceImpl(new HibernateHelper<PromotionPO>(PromotionPO.class));
        }
        return promotionDataService;
    }
}
