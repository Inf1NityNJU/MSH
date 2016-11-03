package blservice.userblservice;

import util.CreditAction;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;

/**
 * Created by Kray on 2016/10/12.
 */
public class UserBLService_Stub implements UserBLService {

    public LoginState login(String account, String password){
        if(account.equals("admin") && password.equals("123456")) {
            System.out.println("Login successfully");
            return LoginState.LOGIN_SUCCESS_Client;
        }else{
            System.out.println("Login failed");
            return LoginState.LOGIN_FAIL;
        }
    }

    public LoginState logout(){
        System.out.println("Logout");
        return LoginState.LOGOUT;
    }

    public ResetState reset(String account, String oldPassword, String newPassword){
        if(oldPassword.equals("123456") && newPassword.equals("1234567890")){
            return ResetState.RESET_SUCCESS;
        }else{
            return ResetState.RESET_FAIL;
        }
    }

    public ResultMessage addCreditRecord(String clientID, int credit, CreditAction creditAction){
        if(clientID.equals("000000007") && credit == 20 && creditAction == CreditAction.ADD_CREDIT) {
            System.out.println("Add successfully");
            return ResultMessage.SUCCESS;
        }else if(clientID.equals("000000007") && credit == 20 && creditAction == CreditAction.DEDUCT_CREDIT){
            System.out.println("Deduct successfully");
            return ResultMessage.SUCCESS;
        }else if(clientID.equals("000000007") && credit == 20 && creditAction == CreditAction.REVOKE_CREDIT){
            System.out.println("Deduct successfully");
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    public int searchCreditByID(String clientID){
        if(clientID.equals("000000007")) {
            return 500;
        }else{
            return -1;
        }
    }
}
