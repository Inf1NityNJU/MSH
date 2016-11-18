package dataimpl.User;

import datahelper.DataHelper;
import dataservice.userdataservice.UserDataService;
import po.ClientPO;
import po.CreditPO;
import po.SalesmanPO;
import po.StaffPO;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/17.
 */
public class UserDataServiceImpl implements UserDataService {
    private DataHelper staffDataHelper;

    protected UserDataServiceImpl(DataHelper staffDataHelper) {
        this.staffDataHelper = staffDataHelper;
    }

    public LoginState login(String account, String password) {
        return null;
    }

    public LoginState logout() {
        return null;
    }

    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) {
        return null;
    }

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) {
        return null;
    }

    public ClientPO searchClientByID(String clientID) {
        return null;
    }

    public ResultMessage updateClient(String clientID, ClientPO clientPO) {
        return null;
    }

    public ResultMessage deleteClient(String clientID) {
        return null;
    }

    public ArrayList<ClientPO> searchClient(String keyword) {
        return null;
    }

    public ResultMessage addStaff(StaffPO staffPO) {
        return staffDataHelper.save(StaffPO.class,staffPO);
    }

    public StaffPO searchStaffByID(String staffID) {
        return null;
    }

    public ResultMessage updateStaff(String staffID, StaffPO staffPO) {
        return staffDataHelper.update(StaffPO.class,staffPO);
    }

    public ResultMessage deleteStaff(String staffID) {
        return staffDataHelper.delete(StaffPO.class,staffID,"StaffID");
    }

    public ArrayList<StaffPO> searchStaff(String keyword) {
        return null;
    }

    public ResultMessage addSalesman(SalesmanPO salesmanPO) {
        return null;
    }

    public SalesmanPO searchSalesmanByID(String salesmanID) {
        return null;
    }

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) {
        return null;
    }

    public ResultMessage deleteSalesman(String salesmanID) {
        return null;
    }

    public ArrayList<SalesmanPO> searchSalesman(String keyword) {
        return null;
    }

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) {
        return null;
    }

    public ArrayList<CreditPO> searchCreditByID(String clientID) {
        return null;
    }
}
