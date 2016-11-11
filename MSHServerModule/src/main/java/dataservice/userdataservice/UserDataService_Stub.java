package dataservice.userdataservice;

import po.ClientPO;
import po.CreditPO;
import po.SalesmanPO;
import po.StaffPO;
import util.CreditAction;
import util.DateUtil;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kray on 2016/10/12.
 */
public class UserDataService_Stub implements UserDataService {

    ClientPO exampleClientPO = new ClientPO("000000007", "老宋", 500, 1, new DateUtil(2015, 10, 10),
            "18795963603", "no_enterprise", "songkuixi", "123456");
    StaffPO exampleStaffPO = new StaffPO("300001", "隔壁老王", "25010001", "admin", "password");
    SalesmanPO exampleSalesmanPO = new SalesmanPO("100001", "隔壁老李", "admin", "password");
    CreditPO exampleCreditPO = new CreditPO("20161012010112340000", new DateUtil(2015, 10, 10), 20, 500, CreditAction.ADD_CREDIT, "000000007");

    public ResultMessage login(String account, String password) {
        return null;
    }

    public ResultMessage logout() {
        return null;
    }

    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) {
        return null;
    }

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) {
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

    //TODO
    public ResultMessage deleteClient(String clientID) {
        return null;
    }

    public ArrayList<ClientPO> searchClient(String keyword) {
        return null;
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

    //TODO
    public ResultMessage deleteStaff(String staffID) {
        return null;
    }

    public ArrayList<StaffPO> searchStaff(String keyword) {
        return null;
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
        return null;
    }

    public ArrayList<SalesmanPO> searchSalesman(String keyword) {
        return null;
    }

    public ResultMessage addCreditByID(String clientID, int credit) {
        if (clientID.equals("000000007")) {
            System.out.println("Add Sucessfully!");
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("Update Failed!");
            return ResultMessage.FAILED;
        }
    }

    public int searchCreditByID(String clientID) {
        if (clientID.equals("000000007")) {
            return 500;
        } else {
            return -1;
        }
    }

}
