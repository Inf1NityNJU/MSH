package dataimpl.promotiondataimpl;

import datahelper.DataHelperFactory;
import dataservice.promotiondataservice.PromotionDataService;

/**
 * Created by vivian on 16/11/22.
 */
public class PromotionDataServiceFactory {
    private static PromotionDataServiceImpl promotionDataService;

    public static synchronized PromotionDataService getPromotionDataService() {
        if (promotionDataService == null) {
            promotionDataService = new PromotionDataServiceImpl(DataHelperFactory.getHibernateDataHelper());
        }
        return promotionDataService;
    }
}
