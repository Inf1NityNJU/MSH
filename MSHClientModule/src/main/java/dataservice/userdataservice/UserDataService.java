package dataservice.userdataservice;

import po.ClientPO;
import po.CreditPO;
import po.SalesmanPO;
import po.StaffPO;
import util.ResultMessage;

/**
 * Created by Kray on 2016/10/13.
 */
public interface UserDataService {

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO);

    public ClientPO searchClientByID(String clientID);

    public ResultMessage updateClient(String clientID, ClientPO clientPO);

    public ResultMessage addStaff(StaffPO staffPO);

    public StaffPO searchStaffByID(String staffID);

    public ResultMessage updateStaff(String staffID, StaffPO staffPO);

    public ResultMessage addSalesman(SalesmanPO salesmanPO);

    public SalesmanPO searchSalesmanByID(String salesmanID);

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO);

    public ResultMessage addCreditByID(String clientID, int credit);

    public int searchCreditByID(String clientID);
}
