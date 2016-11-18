package bl.userbl;

import blservice.userblservice.UserBLService;
import vo.StaffVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/18.
 */
public class UserBLTest {
    public static void main(String[] args) {
        UserBLService userBLService = UserBLFactory.getUserBLServiceImpl_Staff();
        System.out.println("--LOGIN--");
        userBLService.login("adminStaff", "password");
        System.out.println("--ADD--");
        userBLService.add(new StaffVO("100006", "SONG KUI XI", "25010001"));
        System.out.println("--UPDATE--");
        userBLService.update(new StaffVO("100006", "SONG KUI XI", "25010002"));
        System.out.println("--SEARCH ID--");
        StaffVO staffVO = (StaffVO) userBLService.searchByID("100006");
        System.out.println("ID   :" + staffVO.staffID);
        System.out.println("NAME :" + staffVO.staffName);
        System.out.println("--SEARCH--");
        ArrayList<StaffVO> staffVOs = (ArrayList<StaffVO>) userBLService.search("000");
        for (StaffVO staffVO1 : staffVOs) {
            System.out.println("ID   :" + staffVO1.staffID);
            System.out.println("NAME :" + staffVO1.staffName);
        }
        System.out.println("--DELETE--");
        userBLService.delete("100006");
    }
}
