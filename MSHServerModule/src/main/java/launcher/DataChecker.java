package launcher;

import dataimpl.orderdataimpl.OrderDataServiceFactory;
import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.orderdataservice.OrderDataService;
import dataservice.userdataservice.UserDataService;
import po.CreditPO;
import po.OrderPO;
import util.CreditAction;
import util.DateUtil;
import util.OrderState;
import util.TimeUtil;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SilverNarcissus on 2016/12/5.<br>
 * All Done on 2016/12/5
 */
public class DataChecker {

    /**
     * 检查时间间隔
     */
    private static final int checkInterval = 1000*60*60;

    /**
     * 计时器
     */
    private Timer timer;

    public DataChecker() {
        timer = new Timer();
    }

    /**
     * 启动checker
     */
    public void launch() {
        checkOrder();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkOrder();
                System.out.println("check!");
            }
        }, checkInterval, checkInterval);
    }

    /**
     * 检查订单状况
     */
    private void checkOrder() {
        //得到订单数据库接口

        //修改信用值所用接口
        UserDataService userDataService= UserDataServiceFactory.getClientDataService();
        OrderDataService orderDataService = OrderDataServiceFactory.getOrderDataService();
        //
        //得到现在时间
        Calendar calendar = Calendar.getInstance();
        TimeUtil now = new TimeUtil(calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH) + 1
                , calendar.get(Calendar.DAY_OF_MONTH)
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                , calendar.get(Calendar.SECOND));
        //依次检测每一个未执行的Order
        for (OrderPO orderPO : orderDataService.searchOrder(OrderState.Unexecuted)) {
            TimeUtil lastTime = new TimeUtil(orderPO.getLatestExecuteTime());
            if (now.getIntervalTime(lastTime) < 0) {
                orderPO.setState(OrderState.Abnormal);
                orderDataService.updateOrder(orderPO);
                //扣除信用值
                userDataService.addCreditRecord(orderPO.getClientID(),new CreditPO(orderPO.getOrderID()
                        ,new DateUtil(LocalDate.now()).toString()
                        ,-(int)orderPO.getTotalPrice()
                        ,0
                        , CreditAction.OVERDUE_CREDIT
                        ,orderPO.getClientID()));
            }
        }
        //
    }
}
