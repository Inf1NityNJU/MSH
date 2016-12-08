package dataimpl.orderdataimpl;

import datahelper.HibernateHelper;
import po.*;

/**
 * Created by Sorumi on 16/11/28.
 */
public class OrderDataServiceFactory {

    private static OrderDataServiceImpl orderDataService;

    /**
     * 得到一个OrderDataService实例
     *
     * @return OrderDataService实例
     */
    public static synchronized OrderDataServiceImpl getOrderDataService() {
        if (orderDataService == null) {
            orderDataService = new OrderDataServiceImpl(new HibernateHelper<OrderPO>(OrderPO.class),
                    new HibernateHelper<OrderRoomPO>(OrderRoomPO.class),
                    new HibernateHelper<AssessmentPO>(AssessmentPO.class));
        }
        return orderDataService;
    }


}
