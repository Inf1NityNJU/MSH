package user;

import blimpl.userblimpl.Salesman;
import org.junit.Test;
import util.LoginState;
import util.ResultMessage;
import vo.LevelVO;
import vo.SalesmanVO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kray on 2016/11/6.
 */
public class SalesmanTest {
    private Salesman salesman;

    public SalesmanTest() {
        this.salesman = new Salesman();
    }

    @Test
    public void testLogin() throws Exception {
        LoginState ls = salesman.login("adminSalesman", "12345678");
        assertEquals(LoginState.LOGIN_SUCCESS_Salesman, ls);
        ls = salesman.login("adminSalesman", "00000000");
        assertEquals(LoginState.LOGIN_FAIL, ls);
    }

    @Test
    public void testAddSalesman() throws Exception {
        ResultMessage rm = salesman.add(new SalesmanVO("100789", "老王儿", "adminSalesman1"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.add(new SalesmanVO("100001", "老王二", "adminSalesman1"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchSalesmanByID() throws Exception {
        SalesmanVO svo = salesman.searchByID("100001");
        assertEquals(new SalesmanVO("100001", "老王", "adminSalesman1"), svo);
        svo = salesman.searchByID("100002");
        assertEquals(null, svo);
    }

    @Test
    public void testUpdateSalesman() throws Exception {
        ResultMessage rm = salesman.update(new SalesmanVO("100001", "小王", "adminSalesman1"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.update(new SalesmanVO("100002", "老王", "adminSalesman1"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testDeleteSalesman() throws Exception {
        ResultMessage rm = salesman.delete("100001");
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.delete("100002");
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchSalesman() throws Exception {
        ArrayList<SalesmanVO> asvo = new ArrayList<SalesmanVO>();
        asvo.add(new SalesmanVO("100012", "老二", "adminSalesman2"));

        ArrayList<SalesmanVO> tmpAsvoS = salesman.search("老二");
        for (int i = 0; i < asvo.size(); i++) {
            SalesmanVO tmpAsvo = asvo.get(i);
            assertEquals(tmpAsvo, tmpAsvoS.get(i));
        }

        asvo.clear();
        asvo.add(new SalesmanVO("100011", "老大", "adminSalesman1"));
        asvo.add(new SalesmanVO("100012", "老二", "adminSalesman2"));
        asvo.add(new SalesmanVO("100013", "老三", "adminSalesman3"));
        asvo.add(new SalesmanVO("100014", "老四", "adminSalesman4"));

        tmpAsvoS = salesman.search("1000");
        for (int i = 0; i < asvo.size(); i++) {
            SalesmanVO tmpAsvo = asvo.get(i);
            assertEquals(tmpAsvo, tmpAsvoS.get(i));
        }
    }

    @Test
    public void testAddLevel() throws Exception {
        ResultMessage rm = salesman.addLevel(new LevelVO("2","1000"));
        assertEquals(ResultMessage.SUCCESS, rm);
    }

    @Test
    public void testDeleteLevel() throws Exception {
        ResultMessage rm = salesman.deleteLevel("2");
        assertEquals(ResultMessage.SUCCESS, rm);
    }
}
