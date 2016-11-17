package dataservice.userdataservice;

import po.ClientPO;
import po.CreditPO;
import po.SalesmanPO;
import po.StaffPO;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/13.
 */
public interface UserDataService {

    public LoginState login(String account, String password);

    public LoginState logout();

    public ResultMessage resetPassword(String account, String oldPassword, String newPassword);

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO);

    public ClientPO searchClientByID(String clientID);

    public ResultMessage updateClient(String clientID, ClientPO clientPO);

    public ResultMessage deleteClient(String clientID);

    public ArrayList<ClientPO> searchClient(String keyword);

    public ResultMessage addStaff(StaffPO staffPO);

    public StaffPO searchStaffByID(String staffID);

    public ResultMessage updateStaff(String staffID, StaffPO staffPO);

    public ResultMessage deleteStaff(String staffID);

    public ArrayList<StaffPO> searchStaff(String keyword);

    public ResultMessage addSalesman(SalesmanPO salesmanPO);

    public SalesmanPO searchSalesmanByID(String salesmanID);

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO);

    public ResultMessage deleteSalesman(String salesmanID);

    public ArrayList<SalesmanPO> searchSalesman(String keyword);

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO);

    public ArrayList<CreditPO> searchCreditByID(String clientID);
}
