package user;

import blimpl.blfactory.BLFactoryImpl;
import blimpl.userblimpl.Staff;
import blservice.userblservice.UserBLInfo;
import org.junit.Test;
import util.LoginState;
import util.ResultMessage;
import vo.StaffVO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kray on 2016/11/6.
 */
public class StaffTest {

    private Staff staff;

    public StaffTest() {
        staff = new Staff();
    }

    @Test
    public void testLogin() throws Exception {
        LoginState ls = staff.login("adminStaff", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Staff, ls);
        ls = staff.login("adminStaff", "00000000");
        assertEquals(LoginState.LOGIN_FAIL, ls);
    }

    @Test
    public void testAddStaff() throws Exception {
        ResultMessage rm = staff.add(new StaffVO("300999", "隔壁老王", "25010001", "adminStaff1"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = staff.add(new StaffVO("300001", "隔壁老王二", "25010004", "adminStaff1"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchStaffByID() throws Exception {
        StaffVO svo = staff.searchByID("300001");
        assertEquals(new StaffVO("300001", "隔壁老王", "25010001", "adminStaff1"), svo);
        svo = staff.searchByID("300002");
        assertEquals(null, svo);
    }

    @Test
    public void testUpdateStaff() throws Exception {
        ResultMessage rm = staff.update(new StaffVO("300001", "隔壁老王", "25010001", "adminStaff1"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = staff.update(new StaffVO("300002", "隔壁老王", "25010001", "adminStaff1"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testDeleteStaff() throws Exception {
        ResultMessage rm = staff.delete("300001");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = staff.delete("300002");
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchStaff() throws Exception {
        ArrayList<StaffVO> asvo = new ArrayList<StaffVO>();
        asvo.add(new StaffVO("300012", "老二", "25010002", "adminStaff2"));

        ArrayList<StaffVO> tmpAsvoS = staff.search("老二");
        for (int i = 0; i < asvo.size(); i++) {
            StaffVO tmpAsvo = asvo.get(i);
            assertEquals(tmpAsvo, tmpAsvoS.get(i));
        }

        asvo.clear();
        asvo.add(new StaffVO("300011", "老大", "25010001", "adminStaff1"));
        asvo.add(new StaffVO("300012", "老二", "25010002", "adminStaff2"));
        asvo.add(new StaffVO("300013", "老三", "25010001", "adminStaff3"));
        asvo.add(new StaffVO("300014", "老四", "25010003", "adminStaff4"));

        tmpAsvoS = staff.search("3000");
        for (int i = 0; i < asvo.size(); i++) {
            StaffVO tmpAsvo = asvo.get(i);
            assertEquals(tmpAsvo, tmpAsvoS.get(i));
        }
    }

    @Test
    public void testGetHotelIDByStaffID() throws Exception {
        UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();
        String hotelID = userBLInfo.getHotelIDByStaffID("300002");
        assertEquals("02000001", hotelID);
    }
}
