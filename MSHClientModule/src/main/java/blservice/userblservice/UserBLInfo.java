package blservice.userblservice;

import bl.userbl.Salesman;

/**
 * Created by Kray on 2016/11/11.
 */
public interface UserBLInfo {

    /**
     * 根据客户ID获得总信用值
     *
     * @param id
     * @return  该客户的总信用值
     */
    public int getCreditOfID(String id);

    /**
     * 得到当前登录状态下的客户ID
     *
     * @return  当前登录状态下的客户ID
     */
    public String getCurrentID();
}
