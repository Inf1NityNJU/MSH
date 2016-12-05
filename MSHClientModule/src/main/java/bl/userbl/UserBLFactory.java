package bl.userbl;

//import blservice.userblservice.LevelService;

import blservice.userblservice.UserBLInfo;
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

}
