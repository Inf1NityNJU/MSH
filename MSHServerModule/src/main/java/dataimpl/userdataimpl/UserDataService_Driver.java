package dataimpl.userdataimpl;

import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.*;
import util.CreditAction;

import util.DateUtil;

/**
 * Created by Kray on 2016/10/13.
 */
public class UserDataService_Driver {

    @Test
    public void drive() {

        UserDataService userDataService;

        ClientPO exampleClientPO = new ClientPO("", "testClient", 500, new DateUtil(2015, 10, 10).toString(),
                "18795963603", "no_enterprise", "songkuixi", "123456");
        StaffPO exampleStaffPO = new StaffPO("300001", "testStaff", "25010001", "adminStaff", "password");
        SalesmanPO exampleSalesmanPO = new SalesmanPO("100001", "testSalesman", "adminSalesman", "password");
        CreditPO exampleCreditPO = new CreditPO("20161012010112340000", "2016-01-01", 200, 700, CreditAction.ADD_CREDIT, "000000001");
        LevelPO exampleLevelPO = new LevelPO(1, 500);

        userDataService = UserDataServiceFactory.getSalesmanDataService();

        userDataService.addLevel(exampleLevelPO);
        System.out.println("Add Level Success");
        userDataService.addLevel(exampleLevelPO);
        System.out.println("Add Level Success");
        userDataService.updateLevel("1", exampleLevelPO);
        System.out.println("Update Level Success");

        userDataService = UserDataServiceFactory.getClientDataService();

        userDataService.addClient(exampleClientPO);
        System.out.println("Add success");
        userDataService.searchClientByID("000000001");
        System.out.println("Search By ID success");
        userDataService.updateClient("000000001", exampleClientPO);
        System.out.println("Update success");
        userDataService.searchClient("testClient");
        System.out.println("Search success");
        userDataService.addCreditRecord("000000001", exampleCreditPO);
        System.out.println("Add Credit success");
        userDataService.searchCreditByID("000000001");
        System.out.println("Search Credit success");

        userDataService.resetPassword("songkuixi", "123456", "12345678");
        System.out.println("Reset Success");

        userDataService.login("songkuixi", "12345678");
        System.out.println("Login");
        userDataService.deleteClient("000000001");
        System.out.println("Delete success");

        userDataService = UserDataServiceFactory.getStaffDataService();

        userDataService.addStaff(exampleStaffPO);
        System.out.println("Add success");
        userDataService.searchStaffByID("300001");
        System.out.println("Search By ID success");
        userDataService.updateStaff("300001", exampleStaffPO);
        System.out.println("Update success");
        userDataService.searchStaff("testStaff");
        System.out.println("Search success");

        userDataService.resetPassword("adminStaff", "password", "newpassword");
        System.out.println("Reset Success");

        userDataService.login("adminStaff", "newpassword");
        System.out.println("Login");

        userDataService.deleteStaff("300001");
        System.out.println("Delete success");

        userDataService = UserDataServiceFactory.getSalesmanDataService();

        userDataService.addSalesman(exampleSalesmanPO);
        System.out.println("Add success");
        userDataService.searchSalesmanByID("100001");
        System.out.println("Search By ID success");
        userDataService.updateSalesman("100001", exampleSalesmanPO);
        System.out.println("Update success");
        userDataService.searchSalesman("testSalesman");
        System.out.println("Search success");

        userDataService.resetPassword("adminSalesman", "password", "newpassword");
        System.out.println("Reset Success");

        userDataService.login("adminSalesman", "newpassword");
        System.out.println("Login");

        userDataService.deleteSalesman("100001");
        System.out.println("Delete Success");

        userDataService.deleteLevel("1");
        System.out.println("Delete Level Success");
    }

}

