package network.usernetworkservice;

import com.sun.org.apache.regexp.internal.RE;
import dataservice.userdataservice.UserDataService;
import po.*;
import util.LoginState;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public interface UserServerNetworkService extends Remote {

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    public LoginState login(String account, String password) throws RemoteException;

    /**
     * 登出
     *
     * @return
     */
    public LoginState logout() throws RemoteException;

    /**
     * 重置密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) throws RemoteException;

    /**
     * 增加客户
     *
     * @param clientPO
     * @return
     */
    public ResultMessage addClient(ClientPO clientPO) throws RemoteException;

    /**
     * 根据ID查找客户
     *
     * @param clientID
     * @return
     */
    public ClientPO searchClientByID(String clientID) throws RemoteException;

    /**
     * 更新客户信息
     *
     * @param clientID
     * @param clientPO
     * @return
     */
    public ResultMessage updateClient(String clientID, ClientPO clientPO) throws RemoteException;

    /**
     * 删除客户
     *
     * @param clientID
     * @return
     */
    public ResultMessage deleteClient(String clientID) throws RemoteException;

    /**
     * 根据关键词寻找客户
     *
     * @param keyword
     * @return
     */
    public ArrayList<ClientPO> searchClient(String keyword) throws RemoteException;

    /**
     * 增加酒店工作人员
     *
     * @param staffPO
     * @return
     */
    public ResultMessage addStaff(StaffPO staffPO) throws RemoteException;

    /**
     * 根据ID查找酒店工作人员
     *
     * @param staffID
     * @return
     */
    public StaffPO searchStaffByID(String staffID) throws RemoteException;

    /**
     * 更新酒店工作人员信息
     *
     * @param staffID
     * @param staffPO
     * @return
     */
    public ResultMessage updateStaff(String staffID, StaffPO staffPO) throws RemoteException;

    /**
     * 删除酒店工作人员
     *
     * @param staffID
     * @return
     */
    public ResultMessage deleteStaff(String staffID) throws RemoteException;

    /**
     * 根据关键词寻找酒店工作人员
     *
     * @param keyword
     * @return
     */
    public ArrayList<StaffPO> searchStaff(String keyword) throws RemoteException;

    /**
     * 增加网站营销人员
     *
     * @param salesmanPO
     * @return
     */
    public ResultMessage addSalesman(SalesmanPO salesmanPO) throws RemoteException;

    /**
     * 根据ID查找酒店工作人员
     *
     * @param salesmanID
     * @return
     */
    public SalesmanPO searchSalesmanByID(String salesmanID) throws RemoteException;

    /**
     * 更新网站营销人员信息
     *
     * @param salesmanID
     * @param salesmanPO
     * @return
     */
    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) throws RemoteException;

    /**
     * 删除网站营销人员
     *
     * @param salesmanID
     * @return
     */
    public ResultMessage deleteSalesman(String salesmanID) throws RemoteException;

    /**
     * 根据关键词查找网站营销人员
     *
     * @param keyword
     * @return
     */
    public ArrayList<SalesmanPO> searchSalesman(String keyword) throws RemoteException;

    /**
     * 给客户增加信用记录
     *
     * @param clientID
     * @param creditPO
     * @return
     */
    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) throws RemoteException;

    /**
     * 根据ID查找信用记录列表
     *
     * @param clientID
     * @return
     */
    public ArrayList<CreditPO> searchCreditByID(String clientID) throws RemoteException;

    /**
     * 通过酒店ID得到酒店工作人员
     *
     * @param hotelID
     * @return
     */
    public StaffPO getStaffByHotelID(String hotelID) throws RemoteException;

    /**
     * 增加一条等级信息
     *
     * @param levelPO
     * @return
     */
    public ResultMessage addLevel(LevelPO levelPO) throws RemoteException;

    /**
     * 更新一条等级信息
     *
     * @param ID
     * @param levelPO
     * @return
     */
    public ResultMessage updateLevel(String ID, LevelPO levelPO) throws RemoteException;

    /**
     * 删除一条等级信息
     *
     * @param ID
     * @return
     */
    public ResultMessage deleteLevel(String ID) throws RemoteException;

    /**
     * 得到一条等级信息
     *
     * @param level
     * @return
     */
    public LevelPO getLevel(String level) throws RemoteException;

    /**
     * 得到所有等级信息
     *
     * @return
     */
    public ArrayList<LevelPO> getAllLevel() throws RemoteException;

    /**
     * 得到对应的等级
     *
     * @param credit
     * @return
     */
    public int getLevelByCredit(int credit) throws RemoteException;
}
