package user;

import bl.userbl.Mock.MockSalesman;
import bl.userbl.Salesman;
import org.junit.Test;
import util.LoginState;
import util.ResultMessage;
import vo.SalesmanVO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kray on 2016/11/6.
 */
public class SalesmanTest {
    private Salesman salesman;

    public SalesmanTest() {
        this.salesman = new MockSalesman();
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
        ResultMessage rm = salesman.add(new SalesmanVO("100789", "老王儿"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.add(new SalesmanVO("100001", "老王二"));
        assertEquals(ResultMessage.FAILED, rm);
    }

    @Test
    public void testSearchSalesmanByID() throws Exception {
        SalesmanVO svo = salesman.searchByID("100001");
        assertEquals(new SalesmanVO("100001", "老王"), svo);
        svo = salesman.searchByID("100002");
        assertEquals(null, svo);
    }

    @Test
    public void testUpdateSalesman() throws Exception {
        ResultMessage rm = salesman.update(new SalesmanVO("100001", "小王"));
        assertEquals(ResultMessage.SUCCESS, rm);
        rm = salesman.update(new SalesmanVO("100002", "老王"));
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
        asvo.add(new SalesmanVO("100012", "老二"));

        ArrayList<SalesmanVO> tmpAsvoS = salesman.search("老二");
        for (int i = 0; i < asvo.size(); i++) {
            SalesmanVO tmpAsvo = asvo.get(i);
            assertEquals(tmpAsvo, tmpAsvoS.get(i));
        }

        asvo.clear();
        asvo.add(new SalesmanVO("100011", "老大"));
        asvo.add(new SalesmanVO("100012", "老二"));
        asvo.add(new SalesmanVO("100013", "老三"));
        asvo.add(new SalesmanVO("100014", "老四"));

        tmpAsvoS = salesman.search("1000");
        for (int i = 0; i < asvo.size(); i++) {
            SalesmanVO tmpAsvo = asvo.get(i);
            assertEquals(tmpAsvo, tmpAsvoS.get(i));
        }
    }
}
