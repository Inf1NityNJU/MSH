package blimpl.orderbl;

import blservice.orderblservice.OrderBLInfo;

/**
 * Created by Sorumi on 16/12/3.
 */
public class OrderBLFactory {

    private static OrderBLServiceImpl orderBLServiceImpl;

    public synchronized static OrderBLServiceImpl getOrderBLServiceImpl() {
        if (orderBLServiceImpl == null) {
            orderBLServiceImpl = new OrderBLServiceImpl(getOrder());
        }
        return orderBLServiceImpl;
    }

    public synchronized static OrderBLInfo getOrderBLInfo() {
        if (orderBLServiceImpl == null) {
            orderBLServiceImpl = new OrderBLServiceImpl(getOrder());
        }
        return orderBLServiceImpl;
    }

    public synchronized static Order getOrder() {
        return new Order();
    }
}
