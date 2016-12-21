package user;

import blimpl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLInfo;
import org.junit.Test;

/**
 * Created by Kray on 2016/11/18.
 */
public class UserBLTest {
    @Test
    public static void main(String[] args) {
/*
        System.out.println("--STAFF--");
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        System.out.println("--LOGIN--");
        userBLService.login("adminStaff", "password");
        System.out.println("--ADD--");
        userBLService.add(new StaffVO("300006", "SONG KUI XI", "25010001"));
        System.out.println("--UPDATE--");
        userBLService.update(new StaffVO("300006", "SONG KUI XI", "25010002"));
        System.out.println("--SEARCH ID--");
        StaffVO staffVO = (StaffVO) userBLService.searchByID("300006");
        System.out.println("ID   :" + staffVO.staffID);
        System.out.println("NAME :" + staffVO.staffName);
        System.out.println("--SEARCH--");
        ArrayList<StaffVO> staffVOs = (ArrayList<StaffVO>) userBLService.search("000");
        for (StaffVO staffVO1 : staffVOs) {
            System.out.println("ID   :" + staffVO1.staffID);
            System.out.println("NAME :" + staffVO1.staffName);
        }
        System.out.println("--DELETE--");
        userBLService.delete("300006");

        userBLService = null;
*/
        /*

        System.out.println("--SALESMAN--");
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        System.out.println("--LOGIN--");
        if (userBLService.login("adminSalesman", "password4") == LoginState.LOGIN_SUCCESS_Salesman) {
            System.out.println("--ADD--");
            userBLService.add(new SalesmanVO("100006", "SONG KUI XI", "adminSalesman1"));
            System.out.println("--UPDATE--");
            userBLService.update(new SalesmanVO("100006", "SONG KUI XI 2", "adminSalesman2"));
            System.out.println("--SEARCH ID--");
            SalesmanVO salesmanVO = (SalesmanVO) userBLService.searchByID("100006");
            System.out.println("ID   :" + salesmanVO.salesmanID);
            System.out.println("NAME :" + salesmanVO.salesmanName);
            System.out.println("--SEARCH--");
            ArrayList<SalesmanVO> salesmanVOs = (ArrayList<SalesmanVO>) userBLService.search("000");
            for (SalesmanVO salesmanVO1 : salesmanVOs) {
                System.out.println("ID   :" + salesmanVO1.salesmanID);
                System.out.println("NAME :" + salesmanVO1.salesmanName);
            }
            System.out.println("--DELETE--");
            userBLService.delete("100006");
        } else {
            System.out.println("LOGIN FAILED");
        }

        */

//        UserBLService userBLService = new BLFactoryImpl().getClientBLService();
//        userBLService.login("s", "s");
//
//        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();
//        System.out.println(userBLInfo.getCurrentClientID());
//
//        userBLService = new BLFactoryImpl().getStaffBLService();
//        userBLService.login("AAAAA", "B");
//
//        userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
//        System.out.println(userBLInfo.getCurrentStaffID());
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
        System.out.println(userBLInfo.getStaffByHotelID("00000001").staffID);
    }
}
