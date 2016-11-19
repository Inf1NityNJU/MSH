package dataimpl.userdataimpl;

import datahelper.DataHelper;
import dataservice.userdataservice.UserDataService;
import po.ClientPO;
import po.CreditPO;
import po.SalesmanPO;
import po.StaffPO;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * Created by Kray on 2016/11/17.
 */
public class UserDataServiceImpl implements UserDataService {

    private DataHelper userDataHelper;

    protected UserDataServiceImpl(DataHelper userDataHelper) {
        this.userDataHelper = userDataHelper;
    }

    public LoginState login(String account, String password) {
//        userDataHelper.
        return null;
    }

    public LoginState logout() {
        return LoginState.LOGIN_FAIL;
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
        return userDataHelper.save(StaffPO.class, staffPO);
    }

    public StaffPO searchStaffByID(String staffID) {
        return userDataHelper.exactlyQuery(StaffPO.class, "staffID", staffID);
    }

    public ResultMessage updateStaff(String staffID, StaffPO staffPO) {
        return userDataHelper.update(StaffPO.class, staffPO);
    }

    public ResultMessage deleteStaff(String staffID) {
        return userDataHelper.delete(StaffPO.class, "staffID", staffID);
    }

    public ArrayList<StaffPO> searchStaff(String keyword) {
        ArrayList<StaffPO> staffPOs = new ArrayList<StaffPO>();
        for (StaffPO staffPO : userDataHelper.fuzzyMatchQuery(StaffPO.class, "staffID", keyword)) {
            if (!staffPOs.contains(staffPO)) {
                staffPOs.add(staffPO);
            }
        }
        for (StaffPO staffPO : userDataHelper.fuzzyMatchQuery(StaffPO.class, "staffName", keyword)) {
            if (!staffPOs.contains(staffPO)) {
                staffPOs.add(staffPO);
            }
        }
        for (StaffPO staffPO : userDataHelper.fuzzyMatchQuery(StaffPO.class, "hotelID", keyword)) {
            if (!staffPOs.contains(staffPO)) {
                staffPOs.add(staffPO);
            }
        }
        return staffPOs;
    }

    public ResultMessage addSalesman(SalesmanPO salesmanPO) {
        return userDataHelper.save(SalesmanPO.class, salesmanPO);
    }

    public SalesmanPO searchSalesmanByID(String salesmanID) {
        return userDataHelper.exactlyQuery(SalesmanPO.class, "salesmanID", salesmanID);
    }

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) {
        return userDataHelper.update(SalesmanPO.class, salesmanPO);
    }

    public ResultMessage deleteSalesman(String salesmanID) {
        return userDataHelper.delete(SalesmanPO.class, "salesmanID", salesmanID);
    }

    public ArrayList<SalesmanPO> searchSalesman(String keyword) {
        ArrayList<SalesmanPO> salesmanPOs = new ArrayList<SalesmanPO>();
        for (SalesmanPO salesmanPO : userDataHelper.fuzzyMatchQuery(SalesmanPO.class, "salesmanID", keyword)) {
            if (!salesmanPOs.contains(salesmanPO)) {
                salesmanPOs.add(salesmanPO);
            }
        }
        for (SalesmanPO salesmanPO : userDataHelper.fuzzyMatchQuery(SalesmanPO.class, "salesmanName", keyword)) {
            if (!salesmanPOs.contains(salesmanPO)) {
                salesmanPOs.add(salesmanPO);
            }
        }
        return salesmanPOs;
    }

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) {
        return null;
    }

    public ArrayList<CreditPO> searchCreditByID(String clientID) {
        return null;
    }
}
