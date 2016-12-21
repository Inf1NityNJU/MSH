package blservice.userblservice;

import util.ResultMessage;
import vo.*;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/11.
 */
public interface UserBLInfo {

    /**
     * 根据客户ID获得总信用值
     *
     * @param id
     * @return 该客户的总信用值
     */
    public int getCreditOfID(String id);

    /**
     * 得到当前登录状态下的客户ID
     *
     * @return 当前登录状态下的客户ID
     */
    public String getCurrentClientID();

    public String getCurrentStaffID();

    public String getCurrentSalesmanID();

    /**
     * 得到酒店工作人员对应的酒店名字
     *
     * @param ID
     * @return
     */
    public String getHotelIDByStaffID(String ID);

    /**
     * 得到所有等级信息
     *
     * @return
     */
    public ArrayList<LevelVO> getAllLevel();

    /**
     * 得到客户
     *
     * @param ID
     * @return
     */
    public ClientVO getClientByID(String ID);

    /**
     * 得到酒店工作人员
     *
     * @param ID
     * @return
     */
    public StaffVO getStaffByID(String ID);

    /**
     * 得到网站营销人员
     *
     * @param ID
     * @return
     */
    public SalesmanVO getSalesmanByID(String ID);

    /**
     * 通过酒店ID得到酒店工作人员
     *
     * @param hotelID
     * @return
     */
    public StaffVO getStaffByHotelID(String hotelID);

    /**
     * 给客户增加信用记录
     *
     * @param clientID
     * @param creditChangeInfoVO
     * @return
     */
    public ResultMessage addCreditRecord(String clientID, CreditChangeInfoVO creditChangeInfoVO);
}
