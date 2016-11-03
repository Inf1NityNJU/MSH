package bl.userbl;

import util.LoginState;
import util.ResultMessage;
import vo.SalesmanVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class MockSalesman extends MockUser {

    private SalesmanVO exampleSalesmanVO = new SalesmanVO("100001", "老王");

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    public LoginState login(String account, String password){
        return LoginState.LOGIN_SUCCESS_Salesman;
    }

    /**
     * 增加网站营销人员
     * @param salesmanVO
     * @return
     */
    public ResultMessage addSalesman(SalesmanVO salesmanVO){
        if(!salesmanVO.salesmanID.equals("100001")) {
            return ResultMessage.SUCCESS;
        } else {
            System.out.println("salesman exist");
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据ID查找网站营销人员
     * @param SalesmanID
     * @return
     */
    public SalesmanVO searchSalesmanByID(String SalesmanID){
        if(SalesmanID.equals("100001")) {
            return exampleSalesmanVO;
        }else{
            return null;
        }
    }

    /**
     * 更新网站营销人员
     * @param salesmanVO
     * @return
     */
    public ResultMessage updateSalesman(SalesmanVO salesmanVO){
        if(salesmanVO.salesmanID.equals("100001")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 删除网站营销人员
     * @param salesmanID
     * @return
     */
    public ResultMessage deleteSalesman(String salesmanID){
        if(salesmanID.equals("100001")) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据关键词搜索网站营销人员
     * @param keyword
     * @return
     */
    public ArrayList<SalesmanVO> searchSalesman(String keyword){
        SalesmanVO exampleSalesmanVO1 = new SalesmanVO("100011","老大");
        SalesmanVO exampleSalesmanVO2 = new SalesmanVO("100012","老大");
        SalesmanVO exampleSalesmanVO3 = new SalesmanVO("100013","老大");
        SalesmanVO exampleSalesmanVO4 = new SalesmanVO("100014","老大");
        ArrayList<SalesmanVO> salesmanVOs = new ArrayList<SalesmanVO>();
        if(keyword.equals("1000")){
            salesmanVOs.add(exampleSalesmanVO1);
            salesmanVOs.add(exampleSalesmanVO2);
            salesmanVOs.add(exampleSalesmanVO3);
            salesmanVOs.add(exampleSalesmanVO4);
        }else if(keyword.equals("老二")){
            salesmanVOs.add(exampleSalesmanVO2);
        }else{

        }
        return salesmanVOs;
    }

}
