package blimpl.userblimpl;

/**
 * Created by Kray on 2016/12/9.
 */
public class UserInfoManager {

    private static UserInfoManager userInfoManager;

    private String currentClientID;
    private String currentStaffID;
    private String currentSalesmanID;

    public String getCurrentClientID() {
        return currentClientID;
    }

    public void setCurrentClientID(String currentClientID) {
        this.currentClientID = currentClientID;
    }

    public String getCurrentStaffID() {
        return currentStaffID;
    }

    public void setCurrentStaffID(String currentStaffID) {
        this.currentStaffID = currentStaffID;
    }

    public String getCurrentSalesmanID() {
        return currentSalesmanID;
    }

    public void setCurrentSalesmanID(String currentSalesmanID) {
        this.currentSalesmanID = currentSalesmanID;
    }

    public synchronized static UserInfoManager getUserInfoManager() {
        if (userInfoManager == null) {
            userInfoManager = new UserInfoManager();
        }
        return userInfoManager;
    }
}
