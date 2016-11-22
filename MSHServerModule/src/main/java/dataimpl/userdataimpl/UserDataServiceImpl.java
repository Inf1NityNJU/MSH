package dataimpl.userdataimpl;

import datahelper.DataHelper;
import datahelper.HibernateHelper;
import dataservice.userdataservice.UserDataService;
import po.*;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/17.
 */
public class UserDataServiceImpl implements UserDataService {

    private DataHelper<UserPO> userDataHelper=new HibernateHelper<UserPO>();
    private DataHelper<ClientPO> clientDataHelper = new HibernateHelper<ClientPO>();
    private DataHelper<StaffPO> staffDataHelper = new HibernateHelper<StaffPO>();
    private DataHelper<CreditPO> creditDataHelper = new HibernateHelper<CreditPO>();
    private DataHelper<SalesmanPO> salesmanDataHelper=new HibernateHelper<SalesmanPO>();

    protected UserDataServiceImpl(DataHelper<UserPO> userDataHelper) {
        //this.userDataHelper = userDataHelper;
    }

    public LoginState login(String account, String password) {
        System.out.println(account);
        System.out.println(password);
        UserPO userPO;
        if ((userPO = userDataHelper.exactlyQuery("account", account)) != null) {
            UserPO tmpUserPO;
            if ((tmpUserPO = userDataHelper.exactlyQuery("password", password)) != null) {
                if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                    return LoginState.LOGIN_SUCCESS_Client;
                } else {
                    return LoginState.LOGIN_FAIL;
                }
            } else {
                return LoginState.LOGIN_FAIL;
            }
        } else if ((userPO = userDataHelper.exactlyQuery("account", account)) != null) {
            UserPO tmpUserPO;
            if ((tmpUserPO = userDataHelper.exactlyQuery("password", password)) != null) {
                if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                    return LoginState.LOGIN_SUCCESS_Staff;
                } else {
                    return LoginState.LOGIN_FAIL;
                }
            } else {
                return LoginState.LOGIN_FAIL;
            }
        } else if ((userPO = userDataHelper.exactlyQuery("account", account)) != null) {
            UserPO tmpUserPO;
            if ((tmpUserPO = userDataHelper.exactlyQuery("password", password)) != null) {
                if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                    return LoginState.LOGIN_SUCCESS_Salesman;
                } else {
                    return LoginState.LOGIN_FAIL;
                }
            } else {
                return LoginState.LOGIN_FAIL;
            }
        } else {
            return LoginState.LOGIN_FAIL;
        }
    }

    public LoginState logout() {
        return LoginState.LOGOUT;
    }

    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) {
        return null;
    }

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) {
        if (creditDataHelper.save(clientPO) == ResultMessage.SUCCESS
                && creditDataHelper.save(creditPO) == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    public ClientPO searchClientByID(String clientID) {
        return clientDataHelper.exactlyQuery("clientID", clientID);
    }

    public ResultMessage updateClient(String clientID, ClientPO clientPO) {
        return userDataHelper.update(clientPO);
    }

    public ResultMessage deleteClient(String clientID) {
        return userDataHelper.delete("clientID", clientID);
    }

    public ArrayList<ClientPO> searchClient(String keyword) {
        ArrayList<ClientPO> clientPOs = new ArrayList<ClientPO>();
        for (ClientPO clientPO : clientDataHelper.fuzzyMatchQuery("clientID", keyword)) {
            if (!clientPOs.contains(clientPO)) {
                clientPOs.add(clientPO);
            }
        }
        for (ClientPO clientPO : clientDataHelper.fuzzyMatchQuery("clientName", keyword)) {
            if (!clientPOs.contains(clientPO)) {
                clientPOs.add(clientPO);
            }
        }
        return clientPOs;
    }

    public ResultMessage addStaff(StaffPO staffPO) {
        return userDataHelper.save(staffPO);
    }

    public StaffPO searchStaffByID(String staffID) {
        return staffDataHelper.exactlyQuery("staffID", staffID);
    }

    public ResultMessage updateStaff(String staffID, StaffPO staffPO) {
        return userDataHelper.update(staffPO);
    }

    public ResultMessage deleteStaff(String staffID) {
        return userDataHelper.delete("staffID", staffID);
    }

    public ArrayList<StaffPO> searchStaff(String keyword) {
        ArrayList<StaffPO> staffPOs = new ArrayList<StaffPO>();
        for (StaffPO staffPO : staffDataHelper.fuzzyMatchQuery("staffID", keyword)) {
            if (!staffPOs.contains(staffPO)) {
                staffPOs.add(staffPO);
            }
        }
        for (StaffPO staffPO : staffDataHelper.fuzzyMatchQuery("staffName", keyword)) {
            if (!staffPOs.contains(staffPO)) {
                staffPOs.add(staffPO);
            }
        }
        for (StaffPO staffPO : staffDataHelper.fuzzyMatchQuery("hotelID", keyword)) {
            if (!staffPOs.contains(staffPO)) {
                staffPOs.add(staffPO);
            }
        }
        return staffPOs;
    }

    public ResultMessage addSalesman(SalesmanPO salesmanPO) {
        return userDataHelper.save(salesmanPO);
    }

    public SalesmanPO searchSalesmanByID(String salesmanID) {
        return salesmanDataHelper.exactlyQuery("salesmanID", salesmanID);
    }

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) {
        return userDataHelper.update(salesmanPO);
    }

    public ResultMessage deleteSalesman(String salesmanID) {
        return userDataHelper.delete("salesmanID", salesmanID);
    }

    public ArrayList<SalesmanPO> searchSalesman(String keyword) {
        ArrayList<SalesmanPO> salesmanPOs = new ArrayList<SalesmanPO>();
        for (SalesmanPO salesmanPO : salesmanDataHelper.fuzzyMatchQuery("salesmanID", keyword)) {
            if (!salesmanPOs.contains(salesmanPO)) {
                salesmanPOs.add(salesmanPO);
            }
        }
        for (SalesmanPO salesmanPO : salesmanDataHelper.fuzzyMatchQuery("salesmanName", keyword)) {
            if (!salesmanPOs.contains(salesmanPO)) {
                salesmanPOs.add(salesmanPO);
            }
        }
        return salesmanPOs;
    }

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) {
        //这个 clientID 好像没用?
        return userDataHelper.save(creditPO);
    }

    public ArrayList<CreditPO> searchCreditByID(String clientID) {
        ArrayList<CreditPO> creditPOs = new ArrayList<CreditPO>();
        for (CreditPO creditPO : creditDataHelper.prefixMatchQuery("clientID", clientID)) {
            if (!creditPOs.contains(creditPO)) {
                creditPOs.add(creditPO);
            }
        }
        return creditPOs;
    }
}
