package dataservice.userdataservice;

import po.*;
import util.CreditAction;

import util.DateUtil;

/**
 * Created by Kray on 2016/10/13.
 */
public class UserDataService_Driver {

    public void drive(UserDataService userDataService) {

        ClientPO exampleClientPO = new ClientPO("000000007", "老宋", 500, new DateUtil(2015, 10, 10).toString(),
                "18795963603", "no_enterprise", "songkuixi", "123456");
        StaffPO exampleStaffPO = new StaffPO("300001", "隔壁老王", "25010001", "adminStaff", "password");
        SalesmanPO exampleSalesmanPO = new SalesmanPO("100001", "隔壁老李", "adminSalesman", "password");
        CreditPO exampleCreditPO = new CreditPO("20161012010112340000", "2016-01-01", 200, 700, CreditAction.ADD_CREDIT, "000000007");

        userDataService.login("songkuixi", "123456");
        userDataService.login("adminStaff", "password");
        userDataService.login("adminSalesman", "password");
        userDataService.logout();
        userDataService.resetPassword("songkuixi", "123456", "12345678");
        userDataService.resetPassword("adminStaff", "password", "newpassword");
        userDataService.resetPassword("adminSalesman", "password", "newpassword");

        userDataService.addClient(exampleClientPO);
        userDataService.searchClientByID("000000007");
        userDataService.updateClient("000000007", exampleClientPO);
        userDataService.deleteClient("000000007");
        userDataService.searchClient("老宋");
        userDataService.addCreditRecord("000000007", exampleCreditPO);
        userDataService.searchCreditByID("000000007");

        userDataService.addStaff(exampleStaffPO);
        userDataService.searchStaffByID("300001");
        userDataService.updateStaff("300001", exampleStaffPO);
        userDataService.deleteStaff("300001");
        userDataService.searchStaff("隔壁老王");

        userDataService.addSalesman(exampleSalesmanPO);
        userDataService.searchSalesmanByID("100001");
        userDataService.updateSalesman("100001", exampleSalesmanPO);
        userDataService.deleteSalesman("100001");
        userDataService.searchSalesman("隔壁老李");
    }

}

