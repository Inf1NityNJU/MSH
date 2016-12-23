package blservice.userblservice;

import blimpl.userblimpl.Client;
import blimpl.userblimpl.Salesman;
import blimpl.userblimpl.UserBLFactory;
import org.junit.Test;
import po.StaffPO;
import util.*;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/12.
 */
public class UserBLService_Driver {

    @Test
    public void testClient() {
        UserBLService userBLService = UserBLFactory.getUser_Client_BLServiceImplForTest();
        driver_Client(userBLService);
    }

    @Test
    public void testStaff() {
        UserBLService userBLService = UserBLFactory.getUser_Staff_BLServiceImplForTest();
        driver_Staff(userBLService);
    }

    @Test
    public void testSalesman(){
        UserBLService userBLService = UserBLFactory.getUser_Salesman_BLServiceImplForTest();
        driver_Salesman(userBLService);
    }

    private ClientVO exampleClientVO = new ClientVO("000000001", "KrayC", 1, new DateUtil(1996, 4, 25),
            500, 0, "18795963603", "", "adminClient");
    private ClientVO_Register clientVO_register = new ClientVO_Register(new DateUtil(1996, 4, 25), 0,
            "", "adminClient", "12345678");
    private StaffVO exampleStaffVO = new StaffVO("300001", "staff1", "25010001", "adminStaff");
    private StaffVO_Register staffVO_register = new StaffVO_Register("staff1", "25010001", "adminStaff", "12345678");
    private SalesmanVO exampleSalesmanVO = new SalesmanVO("100001", "salesman", "adminSalesman");
    private SalesmanVO_Register salesmanVO_register = new SalesmanVO_Register("salesman", "adminSalesman", "12345678");

    private void driver_Client(UserBLService userBLService) {
        String userName = "adminClient";
        String password = "12345678";
        String oldPassword = "12345678";
        String newPassword = "1234567890";

        ResultMessage rm;

        rm = userBLService.add(clientVO_register);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("ADD CLIENT SUCCESS");
        } else if (rm == ResultMessage.EXIST) {
            System.out.println("CLIENT EXIST");
        } else if (rm == ResultMessage.FAILED) {
            System.out.println("ADD CLIENT FAILED");
        }

        LoginState loginState = userBLService.login(userName, password);
        if (loginState == LoginState.LOGIN_SUCCESS_Client) {
            System.out.println("LOGIN SUCCESS");
        } else if (loginState == LoginState.LOGIN_FAIL) {
            System.out.println("LOGIN FAILED");
        }

        ResetState resetState = userBLService.reset(userName, oldPassword, newPassword);
        if (resetState == ResetState.RESET_SUCCESS) {
            System.out.println("RESET SUCCESS");
        } else if (resetState == ResetState.RESET_FAIL) {
            System.out.println("RESET FAILED");
        }

        ClientVO searchByIDClientVO = (ClientVO) userBLService.searchByID(exampleClientVO.clientID);
        if (searchByIDClientVO.equals(exampleClientVO)) {
            System.out.println("SEARCH BY ID SUCCESS");
        } else {
            System.out.println("SEARCH BY ID FAILED");
        }

        rm = userBLService.update(exampleClientVO);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("UPDATE SUCCESS");
        } else if (rm == ResultMessage.EXIST) {
            System.out.println("UPDATE FAILED");
        }

        rm = userBLService.addCreditRecord(exampleClientVO.clientID, new CreditChangeInfoVO(200, CreditAction.ADD_CREDIT, "20161012010112340000", new DateUtil(2016, 10, 13)));
        if (rm == ResultMessage.EXIST) {
            System.out.println("CREDIT RECORD EXIST");
        } else if (rm == ResultMessage.SUCCESS) {
            System.out.println("ADD CREDIT RECORD SUCCESS");
        } else if (rm == ResultMessage.FAILED) {
            System.out.println("ADD CREDIT RECORD FAIL");
        }

        rm = userBLService.delete(exampleClientVO.clientID);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("DELETE SUCCESS");
        } else if (rm == ResultMessage.FAILED) {
            System.out.println("DELETE FAILED");
        }

        int level = Integer.parseInt(userBLService.getLevel(exampleClientVO.level + "").level);
        if (level == 1) {
            System.out.println("FIND LEVEL SUCCESS : " + level);
        } else {
            System.out.println("FIND LEVEL FAILED" + level);
        }

        loginState = userBLService.logout();
        if (loginState == LoginState.LOGOUT) {
            System.out.println("LOGOUT");
        }
    }

    private void driver_Staff(UserBLService userBLService) {
        String userName = "adminStaff";
        String password = "12345678";
        String oldPassword = "12345678";
        String newPassword = "1234567890";

        ResultMessage rm;

        rm = userBLService.add(staffVO_register);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("ADD STAFF SUCCESS");
        } else if (rm == ResultMessage.EXIST) {
            System.out.println("STAFF EXIST");
        } else if (rm == ResultMessage.FAILED) {
            System.out.println("ADD STAFF FAILED");
        }

        LoginState loginState = userBLService.login(userName, password);
        if (loginState == LoginState.LOGIN_SUCCESS_Client) {
            System.out.println("LOGIN SUCCESS");
        } else if (loginState == LoginState.LOGIN_FAIL) {
            System.out.println("LOGIN FAILED");
        }

        ResetState resetState = userBLService.reset(userName, oldPassword, newPassword);
        if (resetState == ResetState.RESET_SUCCESS) {
            System.out.println("RESET SUCCESS");
        } else if (resetState == ResetState.RESET_FAIL) {
            System.out.println("RESET FAILED");
        }

        StaffVO staffVO = (StaffVO) userBLService.searchByID(exampleStaffVO.staffID);
        if (staffVO.equals(exampleStaffVO)) {
            System.out.println("SEARCH BY ID SUCCESS");
        } else {
            System.out.println("SEARCH BY ID FAILED");
        }

        rm = userBLService.update(exampleStaffVO);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("UPDATE SUCCESS");
        } else if (rm == ResultMessage.EXIST) {
            System.out.println("UPDATE FAILED");
        }

        rm = userBLService.delete(exampleStaffVO.staffID);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("DELETE SUCCESS");
        } else if (rm == ResultMessage.FAILED) {
            System.out.println("DELETE FAILED");
        }

        loginState = userBLService.logout();
        if (loginState == LoginState.LOGOUT) {
            System.out.println("LOGOUT");
        }
    }

    private void driver_Salesman(UserBLService userBLService){
        String userName = "adminSalesman";
        String password = "12345678";
        String oldPassword = "12345678";
        String newPassword = "1234567890";

        ResultMessage rm;

        rm = userBLService.add(salesmanVO_register);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("ADD SALESMAN SUCCESS");
        } else if (rm == ResultMessage.EXIST) {
            System.out.println("SALESMAN EXIST");
        } else if (rm == ResultMessage.FAILED) {
            System.out.println("ADD SALESMAN FAILED");
        }

        LoginState loginState = userBLService.login(userName, password);
        if (loginState == LoginState.LOGIN_SUCCESS_Client) {
            System.out.println("LOGIN SUCCESS");
        } else if (loginState == LoginState.LOGIN_FAIL) {
            System.out.println("LOGIN FAILED");
        }

        ResetState resetState = userBLService.reset(userName, oldPassword, newPassword);
        if (resetState == ResetState.RESET_SUCCESS) {
            System.out.println("RESET SUCCESS");
        } else if (resetState == ResetState.RESET_FAIL) {
            System.out.println("RESET FAILED");
        }

        SalesmanVO salesmanVO = (SalesmanVO) userBLService.searchByID(exampleSalesmanVO.salesmanID);
        if (salesmanVO.equals(exampleSalesmanVO)) {
            System.out.println("SEARCH BY ID SUCCESS");
        } else {
            System.out.println("SEARCH BY ID FAILED");
        }

        rm = userBLService.update(exampleSalesmanVO);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("UPDATE SUCCESS");
        } else if (rm == ResultMessage.EXIST) {
            System.out.println("UPDATE FAILED");
        }

        rm = userBLService.delete(exampleSalesmanVO.salesmanID);
        if (rm == ResultMessage.SUCCESS) {
            System.out.println("DELETE SUCCESS");
        } else if (rm == ResultMessage.FAILED) {
            System.out.println("DELETE FAILED");
        }

        loginState = userBLService.logout();
        if (loginState == LoginState.LOGOUT) {
            System.out.println("LOGOUT");
        }
    }
}
