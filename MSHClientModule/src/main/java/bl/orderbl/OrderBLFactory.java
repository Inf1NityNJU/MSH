package bl.orderbl;

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

    public synchronized static Order getOrder() {
        return new Order();
    }
}
