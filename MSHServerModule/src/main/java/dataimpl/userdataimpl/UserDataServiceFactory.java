package dataimpl.userdataimpl;

import datahelper.DataHelper;
import datahelper.HibernateHelper;
import dataservice.userdataservice.UserDataService;
import po.ClientPO;
import po.SalesmanPO;
import po.StaffPO;

/**
 * Created by Kray on 2016/11/17.
 */
public class UserDataServiceFactory {
    private static UserDataServiceImpl userDataService;

    //C,S,S DataHelper都继承UserDataHelper或者实现DataHelper接口
    //然后每个工厂方法返回特定的那个Helper

    /**
     * 得到一个UserDataService实例
     *
     * @return UserDataService实例
     */
    public static synchronized UserDataService getClientDataService() {
        if (userDataService == null || userDataService.getUserDataState() != UserDataState.CLIENT) {
            DataHelper<ClientPO> clientDataHelper = new HibernateHelper<ClientPO>(ClientPO.class);
            userDataService = new UserDataServiceImpl();
            userDataService.setClient(clientDataHelper);
        }
        return userDataService;
    }

    public static synchronized UserDataService getStaffDataService() {
        if (userDataService == null || userDataService.getUserDataState() != UserDataState.STAFF) {
            DataHelper<StaffPO> staffDataHelper = new HibernateHelper<StaffPO>(StaffPO.class);
            userDataService = new UserDataServiceImpl();
            userDataService.setStaff(staffDataHelper);
        }
        return userDataService;
    }

    public static synchronized UserDataService getSalesmanDataService() {
        if (userDataService == null || userDataService.getUserDataState() != UserDataState.SALESMAN) {
            DataHelper<SalesmanPO> salesmanDataHelper = new HibernateHelper<SalesmanPO>(SalesmanPO.class);
            userDataService = new UserDataServiceImpl();
            userDataService.setSalesman(salesmanDataHelper);
        }
        return userDataService;
    }
}
