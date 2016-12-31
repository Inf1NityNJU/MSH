package user;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.Staff;
import blservice.userblservice.UserBLInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;
import vo.StaffVO;
import vo.StaffVO_Register;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kray on 2016/11/6.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffTest {

    private Staff staff;

    public StaffTest() {
        staff = new Staff();
    }

    @Test
    public void a_testAddStaff() throws Exception {
        ResultMessage rm = staff.add(new StaffVO_Register("testStaff", "25010001", "adminStaff", "password"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = staff.add(new StaffVO_Register("testStaff", "25010001", "adminStaff", "password"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void b_testLogin() throws Exception {
        LoginState ls = staff.login("adminStaff", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Staff, ls);
        ls = staff.login("adminStaff", "00000000");
        assertEquals(LoginState.LOGIN_FAIL, ls);
    }

    @Test
    public void c_testSearchStaffByID() throws Exception {
        StaffVO svo = staff.searchByID("300001");
        assertEquals(new StaffVO("300001", "testStaff", "25010001", "adminStaff"), svo);
        svo = staff.searchByID("300002");
        assertEquals(null, svo);
    }

    @Test
    public void d_testUpdateStaff() throws Exception {
        ResultMessage rm = staff.update(new StaffVO("300001", "testStaff2", "25010001", "adminStaff"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = staff.update(new StaffVO("300001", "隔壁老王", "", "adminStaff1"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void e_testSearchStaff() throws Exception {
        ArrayList<StaffVO> asvo = new ArrayList<StaffVO>();
        asvo.add(new StaffVO("300001", "testStaff2", "25010001", "adminStaff"));

        ArrayList<StaffVO> tmpAsvoS = staff.search("admin");
        for (int i = 0; i < asvo.size(); i++) {
            StaffVO tmpAsvo = asvo.get(i);
            assertEquals(tmpAsvo, tmpAsvoS.get(i));
        }
    }

    @Test
    public void f_testGetHotelIDByStaffID() throws Exception {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
        String hotelID = userBLInfo.getHotelIDByStaffID("300001");
        assertEquals("25010001", hotelID);
    }

    @Test
    public void g_testGetStaffByHotelID() throws Exception {
        StaffVO staffVO = new StaffVO("300001", "testStaff2", "25010001", "adminStaff");
        assertEquals(staff.getStaffByHotelID("25010001"), staffVO);
    }

    @Test
    public void h_testResetPassword() throws Exception {
        ResetState resetState = staff.resetPassword("adminStaff", "password", "1234567890");
        assertEquals(ResetState.RESET_SUCCESS, resetState);
    }

    @Test
    public void i_testDeleteStaff() throws Exception {
        ResultMessage rm = staff.delete("300001");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = staff.delete("300002");
        assertEquals(ResultMessage.NOT_EXIST, rm);
    }
}
