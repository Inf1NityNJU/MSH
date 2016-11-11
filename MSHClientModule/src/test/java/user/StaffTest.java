//package user;
//
//import bl.userbl.MockStaff;
//import bl.userbl.Staff;
//import org.junit.Test;
//import util.LoginState;
//import util.ResultMessage;
//import vo.StaffVO;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
///**
// * Created by Kray on 2016/11/6.
// */
//public class StaffTest {
//
//    private Staff staff;
//
//    public StaffTest(){
//        staff = new MockStaff();
//    }
//
//    @Test
//    public void testLogin() throws Exception {
//        LoginState ls = staff.login("adminStaff","12345678");
//        assertEquals(LoginState.LOGIN_SUCCESS_Staff,ls);
//        ls = staff.login("adminStaff","00000000");
//        assertEquals(LoginState.LOGIN_FAIL,ls);
//    }
//
//    @Test
//    public void testAddStaff() throws Exception {
//        ResultMessage rm = staff.add(new StaffVO("300999", "隔壁老王", "25010001"));
//        assertEquals(ResultMessage.SUCCESS,rm);
//        rm = staff.add(new StaffVO("300001", "隔壁老王二", "25010004"));
//        assertEquals(ResultMessage.FAILED,rm);
//    }
//
//    @Test
//    public void testSearchStaffByID() throws Exception {
//        StaffVO svo = staff.searchStaffByID("300001");
//        assertEquals(new StaffVO("300001", "隔壁老王", "25010001").hotelID, svo.hotelID);
//        assertEquals(new StaffVO("300001", "隔壁老王", "25010001").staffID, svo.staffID);
//        assertEquals(new StaffVO("300001", "隔壁老王", "25010001").staffName, svo.staffName);
//        svo = staff.searchStaffByID("300002");
//        assertEquals(null, svo);
//    }
//
//    @Test
//    public void testUpdateStaff() throws Exception {
//        ResultMessage rm = staff.updateStaff(new StaffVO("300001", "隔壁老王", "25010001"));
//        assertEquals(ResultMessage.SUCCESS,rm);
//        rm = staff.updateStaff(new StaffVO("300002", "隔壁老王", "25010001"));
//        assertEquals(ResultMessage.FAILED,rm);
//    }
//
//    @Test
//    public void testDeleteStaff() throws Exception {
//        ResultMessage rm = staff.deleteStaff("300001");
//        assertEquals(ResultMessage.SUCCESS,rm);
//        rm = staff.deleteStaff("300002");
//        assertEquals(ResultMessage.FAILED,rm);
//    }
//
//    @Test
//    public void testSearchStaff() throws Exception {
//        ArrayList<StaffVO> asvo = new ArrayList<StaffVO>();
//        asvo.add(new StaffVO("300012","老二","25010002"));
//
//        ArrayList<StaffVO> tmpAsvoS = staff.searchStaff("老二");
//        for(int i = 0; i < asvo.size(); i++) {
//            StaffVO tmpAsvo = asvo.get(i);
//            assertEquals(tmpAsvo.staffID, tmpAsvoS.get(i).staffID);
//            assertEquals(tmpAsvo.staffName, tmpAsvoS.get(i).staffName);
//        }
//
//        asvo.clear();
//        asvo.add(new StaffVO("300011","老大","25010001"));
//        asvo.add(new StaffVO("300012","老二","25010002"));
//        asvo.add(new StaffVO("300013","老三","25010003"));
//        asvo.add(new StaffVO("300014","老四","25010004"));
//
//        tmpAsvoS = staff.searchStaff("3000");
//        for(int i = 0; i < asvo.size(); i++) {
//            StaffVO tmpAsvo = asvo.get(i);
//            assertEquals(tmpAsvo.staffID, tmpAsvoS.get(i).staffID);
//            assertEquals(tmpAsvo.staffName, tmpAsvoS.get(i).staffName);
//        }
//    }
//}
