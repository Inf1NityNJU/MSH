package dataservice.userdataservice;

import po.*;
import util.CreditAction;
import util.DateUtil;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kray on 2016/10/12.
 */
public class UserDataService_Stub implements UserDataService {

    ClientPO exampleClientPO = new ClientPO("000000007", "老宋", 500, new DateUtil(2015, 10, 10).toString(),
            "18795963603", "no_enterprise", "songkuixi", "123456");
    StaffPO exampleStaffPO = new StaffPO("300001", "隔壁老王", "25010001", "adminStaff", "password");
    SalesmanPO exampleSalesmanPO = new SalesmanPO("100001", "隔壁老李", "adminSalesman", "password");
    CreditPO exampleCreditPO = new CreditPO("20161012010112340000", "2016-01-01", 20, 500, CreditAction.ADD_CREDIT, "000000007");

    public LoginState login(String account, String password) {
        if (account.equals("songkuixi") && password.equals("123456")) {
            return LoginState.LOGIN_SUCCESS_Client;
        } else if (account.equals("adminStaff") && password.equals("password")) {
            return LoginState.LOGIN_SUCCESS_Staff;
        } else if (account.equals("adminSalesman") && password.equals("password")) {
            return LoginState.LOGIN_SUCCESS_Salesman;
        } else {
            return LoginState.LOGIN_FAIL;
        }
    }

    public LoginState logout() {
        return LoginState.LOGOUT;
    }

    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) {
        if (account.equals("songkuixi") && oldPassword.equals("123456")) {
            return ResultMessage.SUCCESS;
        } else if (account.equals("adminStaff") && oldPassword.equals("password")) {
            return ResultMessage.SUCCESS;
        } else if (account.equals("adminSalesman") && oldPassword.equals("password")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage addClient(ClientPO clientPO) {
        System.out.println("Add Sucessfully!");
        return ResultMessage.SUCCESS;
    }

    public ClientPO searchClientByID(String clientID) {
        if (clientID.equals("000000007")) {
            return this.exampleClientPO;
        } else {
            return null;
        }
    }

    public ResultMessage updateClient(String clientID, ClientPO clientPO) {
        if (clientID.equals("000000007")) {
            System.out.println("Update Sucessfully!");
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Update Failed!");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage deleteClient(String clientID) {
        if (clientID.equals("000000007")) {
            System.out.println("Delete Sucessfully!");
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Delete Failed!");
            return ResultMessage.FAILED;
        }
    }

    public ArrayList<ClientPO> searchClient(String keyword) {
        ArrayList<ClientPO> clientPOs = new ArrayList<ClientPO>();
        if (keyword.contains("000000007") || keyword.contains("老宋")) {
            clientPOs.add(exampleClientPO);
            return clientPOs;
        } else {
            return null;
        }
    }

    public ResultMessage addStaff(StaffPO staffPO) {
        System.out.println("Add Sucessfully!");
        return ResultMessage.SUCCESS;
    }

    public StaffPO searchStaffByID(String staffID) {
        if (staffID.equals("300001")) {
            return this.exampleStaffPO;
        } else {
            return null;
        }
    }

    public ResultMessage updateStaff(String staffID, StaffPO staffPO) {
        if (staffID.equals("300001")) {
            System.out.println("Update Sucessfully!");
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Update Failed!");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage deleteStaff(String staffID) {
        if (staffID.equals("300001")) {
            System.out.println("Delete Sucessfully!");
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Delete Failed!");
            return ResultMessage.FAILED;
        }
    }

    public ArrayList<StaffPO> searchStaff(String keyword) {
        ArrayList<StaffPO> staffPOs = new ArrayList<StaffPO>();
        if (keyword.contains("300001") || keyword.contains("隔壁老王")) {
            staffPOs.add(exampleStaffPO);
            return staffPOs;
        } else {
            return null;
        }
    }

    public ResultMessage addSalesman(SalesmanPO salesmanPO) {
        System.out.println("Add Sucessfully!");
        return ResultMessage.SUCCESS;
    }

    public SalesmanPO searchSalesmanByID(String salesmanID) {
        if (salesmanID.equals("100001")) {
            return this.exampleSalesmanPO;
        } else {
            return null;
        }
    }

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) {
        if (salesmanID.equals("100001")) {
            System.out.println("Update Sucessfully!");
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Update Failed!");
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage deleteSalesman(String salesmanID) {
        if (salesmanID.equals("100001")) {
            System.out.println("Delete Sucessfully!");
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Delete Failed!");
            return ResultMessage.FAILED;
        }
    }

    public ArrayList<SalesmanPO> searchSalesman(String keyword) {
        ArrayList<SalesmanPO> salesmanPOs = new ArrayList<SalesmanPO>();
        if (keyword.contains("100001") || keyword.contains("隔壁老李")) {
            salesmanPOs.add(exampleSalesmanPO);
            return salesmanPOs;
        } else {
            return null;
        }
    }

    //TODO
    public StaffPO getStaffByHotelID(String hotelID){
        return null;
    }

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) {
        if (clientID.equals("000000007")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    public ArrayList<CreditPO> searchCreditByID(String clientID) {
        ArrayList<CreditPO> creditPOs = new ArrayList<CreditPO>();
        if (clientID.equals("000000007")) {
            CreditPO creditPO = new CreditPO("20161012010112340000", "2016-01-01", 200, 700, CreditAction.ADD_CREDIT, clientID);
            creditPOs.add(creditPO);
            return creditPOs;
        } else {
            return null;
        }
    }

    //TODO
    public ResultMessage addLevel(LevelPO levelPO) {
        return ResultMessage.SUCCESS;
    }

    //TODO
    public ResultMessage updateLevel(String ID, LevelPO levelPO) {
        return ResultMessage.SUCCESS;
    }

    //TODO
    public ResultMessage deleteLevel(String ID) {
        return ResultMessage.SUCCESS;
    }

    //TODO
    public LevelPO getLevel(String ID) {
        return null;
    }

    //TODO
    public ArrayList<LevelPO> getAllLevel() {
        return null;
    }

    //TODO
    public int getLevelByCredit(int credit) {
        return 1;
    }

}
