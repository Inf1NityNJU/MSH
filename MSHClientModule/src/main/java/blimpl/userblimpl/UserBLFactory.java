package blimpl.userblimpl;

//import blservice.userblservice.LevelService;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.mock.MockClient;
import blimpl.userblimpl.mock.MockSalesman;
import blimpl.userblimpl.mock.MockStaff;
import blservice.blfactoryservice.BLFactoryService;
import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import util.LoginState;

/**
 * Created by Kray on 2016/11/11.
 */
public class UserBLFactory {

    private static UserBLServiceImpl userBLService;

    public synchronized static UserBLServiceImpl getUserBLServiceImpl_Client() {
        if (userBLService == null || !(userBLService.getUser() instanceof Client)) {
            userBLService = new UserBLServiceImpl(new Client(), LoginState.LOGOUT);
        }
        return userBLService;
    }

    public synchronized static UserBLServiceImpl getUserBLServiceImpl_Staff() {
        if (userBLService == null || !(userBLService.getUser() instanceof Staff)) {
            userBLService = new UserBLServiceImpl(new Staff(), LoginState.LOGOUT);
        }
        return userBLService;
    }

    public synchronized static UserBLServiceImpl getUserBLServiceImpl_Salesman() {
        if (userBLService == null || !(userBLService.getUser() instanceof Salesman)) {
            userBLService = new UserBLServiceImpl(new Salesman(), LoginState.LOGOUT);
        }
        return userBLService;
    }

    public synchronized static UserBLInfo getUserInfo_Client() {
        if (userBLService == null || !(userBLService.getUser() instanceof Client)) {
            userBLService = new UserBLServiceImpl(new Client(), LoginState.LOGOUT);
        }
        return userBLService;
    }

    public synchronized static UserBLInfo getUserInfo_Staff() {
        if (userBLService == null || !(userBLService.getUser() instanceof Staff)) {
            userBLService = new UserBLServiceImpl(new Staff(), LoginState.LOGOUT);
        }
        return userBLService;
    }

    public synchronized static UserBLInfo getUserInfo_Salesman() {
        if (userBLService == null || !(userBLService.getUser() instanceof Salesman)) {
            userBLService = new UserBLServiceImpl(new Salesman(), LoginState.LOGOUT);
        }
        return userBLService;
    }

    //Test

    public synchronized static UserBLService getUser_Client_BLServiceImplForTest() {
        return new UserBLServiceImpl(getClientForTest(), LoginState.LOGOUT);
    }

    public synchronized static UserBLService getUser_Staff_BLServiceImplForTest() {
        return new UserBLServiceImpl(getStaffForTest(), LoginState.LOGOUT);
    }

    public synchronized static UserBLService getUser_Salesman_BLServiceImplForTest() {
        return new UserBLServiceImpl(getSalesmanForTest(), LoginState.LOGOUT);
    }

    private synchronized static User getClientForTest() {
        return new MockClient();
    }

    private synchronized static User getStaffForTest() {
        return new MockStaff();
    }

    private synchronized static User getSalesmanForTest() {
        return new MockSalesman();
    }
}
