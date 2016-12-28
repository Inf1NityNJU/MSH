package user;

import blimpl.userblimpl.Salesman;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;
import vo.LevelVO;
import vo.SalesmanVO;
import vo.SalesmanVO_Register;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kray on 2016/11/6.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesmanTest {
    private Salesman salesman;

    public SalesmanTest() {
        this.salesman = new Salesman();
    }

    @Test
    public void a_testAddSalesman() throws Exception {
        ResultMessage rm = salesman.add(new SalesmanVO_Register("testSalesman", "adminSalesman", "password"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.add(new SalesmanVO_Register("testSalesman", "adminSalesman", "password"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void b_testLogin() throws Exception {
        LoginState ls = salesman.login("adminSalesman", "password");
        assertEquals(LoginState.LOGIN_SUCCESS_Salesman, ls);
        ls = salesman.login("adminSalesman", "00000000");
        assertEquals(LoginState.LOGIN_FAIL, ls);
    }

    @Test
    public void c_testSearchSalesmanByID() throws Exception {
        SalesmanVO svo = salesman.searchByID("100001");
        assertEquals(new SalesmanVO("100001", "testSalesman", "adminSalesman"), svo);
        svo = salesman.searchByID("100002");
        assertEquals(null, svo);
    }

    @Test
    public void d_testUpdateSalesman() throws Exception {
        ResultMessage rm = salesman.update(new SalesmanVO("100001", "testSalesman1", "adminSalesman"));
        assertEquals(ResultMessage.SUCCESS, rm);
//        rm = salesman.update(new SalesmanVO("100001", "", "adminSalesman"));
//        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void e_testSearchSalesman() throws Exception {
        ArrayList<SalesmanVO> asvo = new ArrayList<SalesmanVO>();
        asvo.add(new SalesmanVO("100001", "testSalesman1", "adminSalesman"));

        ArrayList<SalesmanVO> tmpAsvoS = salesman.search("admin");
        for (int i = 0; i < asvo.size(); i++) {
            SalesmanVO tmpAsvo = asvo.get(i);
            assertEquals(tmpAsvo, tmpAsvoS.get(i));
        }
    }

    @Test
    public void f_testAddLevel() throws Exception {
        ResultMessage rm = salesman.addLevel(new LevelVO("1","500"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.addLevel(new LevelVO("2","1000"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.addLevel(new LevelVO("2","1100"));
        assertEquals(ResultMessage.EXIST, rm);
    }

    @Test
    public void g_testUpdateLevel() throws Exception {
        ResultMessage rm = salesman.updateLevel(new LevelVO("2","1500"));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void h_testGetAllLevel() throws Exception {
        ArrayList<LevelVO> levelVOs = new ArrayList<>();
        levelVOs.add(new LevelVO("1", "500"));
        levelVOs.add(new LevelVO("2", "1500"));
        assertEquals(salesman.getAllLevel(), levelVOs);
    }

    @Test
    public void i_testDeleteLevel() throws Exception {
        ResultMessage rm = salesman.deleteLevel("1");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.deleteLevel("2");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.deleteLevel("2");
        assertEquals(ResultMessage.NOT_EXIST, rm);
    }

    @Test
    public void j_testResetPassword() throws Exception {
        ResetState resetState = salesman.resetPassword("adminSalesman", "password", "1234567890");
        assertEquals(ResetState.RESET_SUCCESS, resetState);
    }

    @Test
    public void k_testDeleteSalesman() throws Exception {
        ResultMessage rm = salesman.delete("100001");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.delete("100002");
        assertEquals(ResultMessage.NOT_EXIST, rm);
    }
}
