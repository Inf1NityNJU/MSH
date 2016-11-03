package blservice.userblservice;

import util.CreditAction;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;

/**
 * Created by Kray on 2016/10/13.
 */
public interface UserBLService {

    public LoginState login(String account, String password);

    public LoginState logout();

    public ResetState reset(String account, String oldPassword, String newPassword);

    public ResultMessage addCreditRecord(String clientID, int credit, CreditAction creditAction);

    public int searchCreditByID(String clientID);
}
