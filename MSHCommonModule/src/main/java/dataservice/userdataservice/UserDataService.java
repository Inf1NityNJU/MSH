package dataservice.userdataservice;

import po.*;
import util.LoginState;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by Kray on 2016/10/13.
 */
public interface UserDataService {

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    public LoginState login(String account, String password);

    /**
     * 登出
     *
     * @return
     */
    public LoginState logout();

    /**
     * 重置密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public ResultMessage resetPassword(String account, String oldPassword, String newPassword);

    /**
     * 增加客户
     *
     * @param clientPO
     * @return
     */
    public ResultMessage addClient(ClientPO clientPO);

    /**
     * 根据ID查找客户
     *
     * @param clientID
     * @return
     */
    public ClientPO searchClientByID(String clientID);

    /**
     * 更新客户信息
     *
     * @param clientID
     * @param clientPO
     * @return
     */
    public ResultMessage updateClient(String clientID, ClientPO clientPO);

    /**
     * 删除客户
     *
     * @param clientID
     * @return
     */
    public ResultMessage deleteClient(String clientID);

    /**
     * 根据关键词寻找客户
     *
     * @param keyword
     * @return
     */
    public ArrayList<ClientPO> searchClient(String keyword);

    /**
     * 增加酒店工作人员
     *
     * @param staffPO
     * @return
     */
    public ResultMessage addStaff(StaffPO staffPO);

    /**
     * 根据ID查找酒店工作人员
     *
     * @param staffID
     * @return
     */
    public StaffPO searchStaffByID(String staffID);

    /**
     * 更新酒店工作人员信息
     *
     * @param staffID
     * @param staffPO
     * @return
     */
    public ResultMessage updateStaff(String staffID, StaffPO staffPO);

    /**
     * 删除酒店工作人员
     *
     * @param staffID
     * @return
     */
    public ResultMessage deleteStaff(String staffID);

    /**
     * 根据关键词寻找酒店工作人员
     *
     * @param keyword
     * @return
     */
    public ArrayList<StaffPO> searchStaff(String keyword);

    /**
     * 增加网站营销人员
     *
     * @param salesmanPO
     * @return
     */
    public ResultMessage addSalesman(SalesmanPO salesmanPO);

    /**
     * 根据ID查找酒店工作人员
     *
     * @param salesmanID
     * @return
     */
    public SalesmanPO searchSalesmanByID(String salesmanID);

    /**
     * 更新网站营销人员信息
     *
     * @param salesmanID
     * @param salesmanPO
     * @return
     */
    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO);

    /**
     * 删除网站营销人员
     *
     * @param salesmanID
     * @return
     */
    public ResultMessage deleteSalesman(String salesmanID);

    /**
     * 根据关键词查找网站营销人员
     *
     * @param keyword
     * @return
     */
    public ArrayList<SalesmanPO> searchSalesman(String keyword);

    /**
     * 给客户增加信用记录
     *
     * @param clientID
     * @param creditPO
     * @return
     */
    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO);

    /**
     * 根据ID查找信用记录列表
     *
     * @param clientID
     * @return
     */
    public ArrayList<CreditPO> searchCreditByID(String clientID);

    /**
     * 通过酒店ID得到酒店工作人员
     *
     * @param hotelID
     * @return
     */
    public StaffPO getStaffByHotelID(String hotelID);

    /**
     * 增加一条等级信息
     *
     * @param levelPO
     * @return
     */
    public ResultMessage addLevel(LevelPO levelPO);

    /**
     * 更新一条等级信息
     *
     * @param ID
     * @param levelPO
     * @return
     */
    public ResultMessage updateLevel(String ID, LevelPO levelPO);

    /**
     * 删除一条等级信息
     *
     * @param ID
     * @return
     */
    public ResultMessage deleteLevel(String ID);

    /**
     * 得到一条等级信息
     *
     * @param level
     * @return
     */
    public LevelPO getLevel(String level);

    /**
     * 得到所有等级信息
     *
     * @return
     */
    public ArrayList<LevelPO> getAllLevel();

    /**
     * 得到对应的等级
     *
     * @param credit
     * @return
     */
    public int getLevelByCredit(int credit);

}
