package dataimpl.User;

import datahelper.DataHelperFactory;
import datahelper.DataHelper;
import dataservice.userdataservice.UserDataService;

/**
 * Created by Kray on 2016/11/17.
 */
public class UserDataServiceFactory {
    private static UserDataServiceImpl userDataService;

    /**
     * 得到一个UserDataService实例
     *
     * @return UserDataService实例
     */
    public static synchronized UserDataService getStaffDataService() {
        if (userDataService == null) {
            DataHelper staffDataHelper = DataHelperFactory.getHibernateDataHelper();
            userDataService = new UserDataServiceImpl(staffDataHelper);
        }
        return userDataService;
    }

    public static synchronized UserDataService getSalesmanDataService() {
        if (userDataService == null) {
            DataHelper salesmanDataHelper = DataHelperFactory.getHibernateDataHelper();
            userDataService = new UserDataServiceImpl(salesmanDataHelper);
        }
        return userDataService;
    }

    public static synchronized UserDataService getClientDataService() {
        if (userDataService == null) {
            DataHelper clientDataHelper = DataHelperFactory.getHibernateDataHelper();
            userDataService = new UserDataServiceImpl(clientDataHelper);
        }
        return userDataService;
    }
}
