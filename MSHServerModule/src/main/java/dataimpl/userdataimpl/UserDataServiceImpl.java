package dataimpl.userdataimpl;

import datahelper.DataHelper;
import datahelper.HibernateHelper;
import dataservice.userdataservice.UserDataService;
import po.*;
import util.LoginState;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Created by Kray on 2016/11/17.
 */
public class UserDataServiceImpl implements UserDataService {

    private DataHelper<ClientPO> clientDataHelper;
    private DataHelper<StaffPO> staffDataHelper;
    private DataHelper<SalesmanPO> salesmanDataHelper;
    private DataHelper<CreditPO> creditDataHelper;
    private DataHelper<LevelPO> levelDataHelper;

    private UserDataState userDataState;

    protected UserDataServiceImpl() {
        this.userDataState = UserDataState.NONE;
    }

    protected void setClient(DataHelper<ClientPO> clientDataHelper) {
        this.clientDataHelper = clientDataHelper;
        this.creditDataHelper = new HibernateHelper<CreditPO>(CreditPO.class);
        this.levelDataHelper = new HibernateHelper<LevelPO>(LevelPO.class);
        this.userDataState = UserDataState.CLIENT;
    }

    protected void setStaff(DataHelper<StaffPO> staffDataHelper) {
        this.staffDataHelper = staffDataHelper;
        this.userDataState = UserDataState.STAFF;
    }

    protected void setSalesman(DataHelper<SalesmanPO> salesmanDataHelper) {
        this.salesmanDataHelper = salesmanDataHelper;
        this.levelDataHelper = new HibernateHelper<LevelPO>(LevelPO.class);
        this.userDataState = UserDataState.SALESMAN;
    }

    public UserDataState getUserDataState() {
        return userDataState;
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    public LoginState login(String account, String password) {
        UserPO userPO;
        if (clientDataHelper != null) {
            System.out.println("Client Login");
            if ((userPO = clientDataHelper.exactlyQuery("account", account)) != null) {
                UserPO tmpUserPO;
                if ((tmpUserPO = clientDataHelper.exactlyQuery("password", password)) != null) {
                    if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                        return LoginState.LOGIN_SUCCESS_Client;
                    }
                }
            }
        } else if (staffDataHelper != null) {
            System.out.println("Staff Login");
            if ((userPO = staffDataHelper.exactlyQuery("account", account)) != null) {
                UserPO tmpUserPO;
                if ((tmpUserPO = staffDataHelper.exactlyQuery("password", password)) != null) {
                    if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                        return LoginState.LOGIN_SUCCESS_Staff;
                    }
                }
            }
        } else if (salesmanDataHelper != null) {
            System.out.println("Salesman Login");
            if ((userPO = salesmanDataHelper.exactlyQuery("account", account)) != null) {
                UserPO tmpUserPO;
                if ((tmpUserPO = salesmanDataHelper.exactlyQuery("password", password)) != null) {
                    if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                        return LoginState.LOGIN_SUCCESS_Salesman;
                    }
                }
            }
        }
        return LoginState.LOGIN_FAIL;
    }

    /**
     * 登出
     *
     * @return
     */
    public LoginState logout() {
        clientDataHelper = null;
        salesmanDataHelper = null;
        staffDataHelper = null;
        creditDataHelper = null;
        return LoginState.LOGOUT;
    }

    /**
     * 重置密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) {
        if (newPassword.equals("")) {
            System.out.println("Empty Password");
            return ResultMessage.FAILED;
        } else if (newPassword.equals(oldPassword)) {
            System.out.println("Same as old password");
            return ResultMessage.FAILED;
        } else {
            UserPO userPO;
            if (clientDataHelper != null) {
                if ((userPO = clientDataHelper.exactlyQuery("account", account)) != null) {
                    UserPO tmpUserPO;
                    if ((tmpUserPO = clientDataHelper.exactlyQuery("password", oldPassword)) != null) {
                        if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                            ClientPO clientPO = (ClientPO) tmpUserPO;
                            return updateClient(
                                    clientPO.getClientID(),
                                    new ClientPO(
                                            clientPO.getClientID(),
                                            clientPO.getClientName(),
                                            clientPO.getCredit(),
                                            clientPO.getBirthday(),
                                            clientPO.getContactInfo(),
                                            clientPO.getEnterprise(),
                                            clientPO.getAccount(),
                                            newPassword));
                        }
                    }
                }
            } else if (staffDataHelper != null) {
                if ((userPO = staffDataHelper.exactlyQuery("account", account)) != null) {
                    UserPO tmpUserPO;
                    if ((tmpUserPO = staffDataHelper.exactlyQuery("password", oldPassword)) != null) {
                        if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                            StaffPO staffPO = (StaffPO) tmpUserPO;
                            return updateStaff(staffPO.getStaffID(),
                                    new StaffPO(
                                            staffPO.getStaffID(),
                                            staffPO.getStaffName(),
                                            staffPO.getHotelID(),
                                            staffPO.getAccount(),
                                            newPassword));
                        }
                    }
                }
            } else if (salesmanDataHelper != null) {
                if ((userPO = salesmanDataHelper.exactlyQuery("account", account)) != null) {
                    UserPO tmpUserPO;
                    if ((tmpUserPO = salesmanDataHelper.exactlyQuery("password", oldPassword)) != null) {
                        if (userPO.getPassword().equals(tmpUserPO.getPassword())) {
                            SalesmanPO salesmanPO = (SalesmanPO) tmpUserPO;
                            return updateSalesman(salesmanPO.getSalesmanID(),
                                    new SalesmanPO(
                                            salesmanPO.getSalesmanID(),
                                            salesmanPO.getSalesmanName(),
                                            salesmanPO.getAccount(),
                                            newPassword));
                        }
                    }
                }
            }
            return ResultMessage.FAILED;
        }
    }

    /**
     * 增加客户
     *
     * @param clientPO
     * @return
     */
    public ResultMessage addClient(ClientPO clientPO) {
        String clientID = getMaxClientID();
        ClientPO tmpPO = new ClientPO(
                clientID,
                clientPO.getClientName(),
                clientPO.getCredit(),
                clientPO.getBirthday(),
                clientPO.getContactInfo(),
                clientPO.getEnterprise(),
                clientPO.getAccount(),
                clientPO.getPassword());
        if (checkAccountExist(clientPO.getAccount(), "client")) {
            return ResultMessage.EXIST;
        } else if (clientDataHelper.save(tmpPO) == ResultMessage.SUCCESS
                && creditDataHelper.save(new CreditPO(clientID)) == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 根据ID寻找客户
     *
     * @param clientID
     * @return
     */
    public ClientPO searchClientByID(String clientID) {
        return clientDataHelper.exactlyQuery("clientID", clientID);
    }

    /**
     * 更新客户信息
     *
     * @param clientID
     * @param clientPO
     * @return
     */
    public ResultMessage updateClient(String clientID, ClientPO clientPO) {
        return clientDataHelper.update(clientPO);
    }

    /**
     * 删除客户
     *
     * @param clientID
     * @return
     */
    public ResultMessage deleteClient(String clientID) {
        //也要删除信用记录
        if (deleteAllCredit(clientID)
                && clientDataHelper.delete("clientID", clientID) == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    /**
     * 搜索符合关键词的客户
     *
     * @param keyword
     * @return
     */
    public ArrayList<ClientPO> searchClient(String keyword) {
        ArrayList<ClientPO> clientPOs = new ArrayList<ClientPO>();
        Predicate<ClientPO> clientPOPredicate = (clientPO) -> !(clientPOs.contains(clientPO));
        clientDataHelper.fuzzyMatchQuery("clientID", keyword).stream().filter(clientPOPredicate).forEach(clientPOs::add);
        clientDataHelper.fuzzyMatchQuery("clientName", keyword).stream().filter(clientPOPredicate).forEach(clientPOs::add);
        clientDataHelper.fuzzyMatchQuery("account", keyword).stream().filter(clientPOPredicate).forEach(clientPOs::add);
        return clientPOs;
    }

    /**
     * 增加酒店工作人员
     *
     * @param staffPO
     * @return
     */
    public ResultMessage addStaff(StaffPO staffPO) {
        String staffID = getMaxStaffID();
        StaffPO tmpPO = new StaffPO(staffID, staffPO.getStaffName(), staffPO.getHotelID(), staffPO.getAccount(), staffPO.getPassword());
        if (checkAccountExist(staffPO.getAccount(), "staff")) {
            return ResultMessage.FAILED;
        } else {
            return staffDataHelper.save(tmpPO);
        }
    }

    /**
     * 根据ID查找酒店工作人员
     *
     * @param staffID
     * @return
     */
    public StaffPO searchStaffByID(String staffID) {
        return staffDataHelper.exactlyQuery("staffID", staffID);
    }

    /**
     * 更新酒店工作人员信息
     *
     * @param staffID
     * @param staffPO
     * @return
     */
    public ResultMessage updateStaff(String staffID, StaffPO staffPO) {
        return staffDataHelper.update(staffPO);
    }

    /**
     * 删除酒店工作人员
     *
     * @param staffID
     * @return
     */
    public ResultMessage deleteStaff(String staffID) {
        return staffDataHelper.delete("staffID", staffID);
    }

    /**
     * 搜索符合关键词的酒店工作人员
     *
     * @param keyword
     * @return
     */
    public ArrayList<StaffPO> searchStaff(String keyword) {
        ArrayList<StaffPO> staffPOs = new ArrayList<StaffPO>();
        Predicate<StaffPO> staffPOPredicate = (staffPO -> !staffPOs.contains(staffPO));
        staffDataHelper.fuzzyMatchQuery("staffID", keyword).stream().filter(staffPOPredicate).forEach(staffPOs::add);
        staffDataHelper.fuzzyMatchQuery("staffName", keyword).stream().filter(staffPOPredicate).forEach(staffPOs::add);
        staffDataHelper.fuzzyMatchQuery("hotelID", keyword).stream().filter(staffPOPredicate).forEach(staffPOs::add);
        staffDataHelper.fuzzyMatchQuery("account", keyword).stream().filter(staffPOPredicate).forEach(staffPOs::add);
        return staffPOs;
    }

    /**
     * 增加网站营销人员
     *
     * @param salesmanPO
     * @return
     */
    public ResultMessage addSalesman(SalesmanPO salesmanPO) {
        String salesmanID = getMaxSalesmanID();
        SalesmanPO tmpPO = new SalesmanPO(
                salesmanID,
                salesmanPO.getSalesmanName(),
                salesmanPO.getAccount(),
                salesmanPO.getPassword());
        if (checkAccountExist(salesmanPO.getAccount(), "salesman")) {
            return ResultMessage.FAILED;
        } else {
            return salesmanDataHelper.save(tmpPO);
        }
    }

    /**
     * 根据ID查找网站营销人员
     *
     * @param salesmanID
     * @return
     */
    public SalesmanPO searchSalesmanByID(String salesmanID) {
        return salesmanDataHelper.exactlyQuery("salesmanID", salesmanID);
    }

    /**
     * 更新网站营销人员信息
     *
     * @param salesmanID
     * @param salesmanPO
     * @return
     */
    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) {
        return salesmanDataHelper.update(salesmanPO);
    }

    /**
     * 删除网站营销人员
     *
     * @param salesmanID
     * @return
     */
    public ResultMessage deleteSalesman(String salesmanID) {
        return salesmanDataHelper.delete("salesmanID", salesmanID);
    }

    /**
     * 搜索符合关键词的网站营销人员
     *
     * @param keyword
     * @return
     */
    public ArrayList<SalesmanPO> searchSalesman(String keyword) {
        ArrayList<SalesmanPO> salesmanPOs = new ArrayList<SalesmanPO>();
        Predicate<SalesmanPO> salesmanPOPredicate = (salesmanPO -> !salesmanPOs.contains(salesmanPO));
        salesmanDataHelper.fuzzyMatchQuery("salesmanID", keyword).stream().filter(salesmanPOPredicate).forEach(salesmanPOs::add);
        salesmanDataHelper.fuzzyMatchQuery("salesmanName", keyword).stream().filter(salesmanPOPredicate).forEach(salesmanPOs::add);
        salesmanDataHelper.fuzzyMatchQuery("account", keyword).stream().filter(salesmanPOPredicate).forEach(salesmanPOs::add);
        return salesmanPOs;
    }

    /**
     * 给客户增加一条信用记录
     *
     * @param clientID
     * @param creditPO
     * @return
     */
    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) {
        ClientPO clientPO = clientDataHelper.exactlyQuery("clientID", clientID);
        int resultCredit = clientPO.getCredit() + creditPO.getDeltaCredit();
        creditPO.setResultCredit(resultCredit);
        ResultMessage rm = creditDataHelper.save(creditPO);
        if (rm == ResultMessage.SUCCESS) {
            int level = 1;
            while (true) {
                if (reachedLevel(level, resultCredit)) {
                    break;
                }
                level++;
            }
            clientDataHelper.update(new ClientPO(
                    clientPO.getClientID(),
                    clientPO.getClientName(),
                    resultCredit,
                    clientPO.getBirthday(),
                    clientPO.getContactInfo(),
                    clientPO.getEnterprise(),
                    clientPO.getAccount(),
                    clientPO.getPassword()));
        }
        return rm;
    }

    /**
     * 根据客户ID查找所有的信用记录
     *
     * @param clientID
     * @return
     */
    public ArrayList<CreditPO> searchCreditByID(String clientID) {
        ArrayList<CreditPO> creditPOs = new ArrayList<CreditPO>();
        Predicate<CreditPO> creditPOPredicate = creditPO -> !creditPOs.contains(creditPO);
        creditDataHelper.prefixMatchQuery("clientID", clientID).stream().filter(creditPOPredicate).forEach(creditPOs::add);
        return creditPOs;
    }

    /**
     * 通过酒店ID得到酒店工作人员
     *
     * @param hotelID
     * @return
     */
    public StaffPO getStaffByHotelID(String hotelID) {
        return staffDataHelper.exactlyQuery("hotelID", hotelID);
    }

    /**
     * 增加一条等级信息
     *
     * @param levelPO
     * @return
     */
    public ResultMessage addLevel(LevelPO levelPO) {
        return levelDataHelper.save(levelPO);
    }

    /**
     * 更新一条等级信息
     *
     * @param ID
     * @param levelPO
     * @return
     */
    public ResultMessage updateLevel(String ID, LevelPO levelPO) {
        return levelDataHelper.update(levelPO);
    }

    /**
     * 删除一条等级信息
     *
     * @param ID
     * @return
     */
    public ResultMessage deleteLevel(String ID) {
        return levelDataHelper.delete("ID", ID);
    }

    /**
     * 得到一条等级信息
     *
     * @param level
     * @return
     */
    public LevelPO getLevel(String level) {
        return levelDataHelper.exactlyQuery("ID", level);
    }

    /**
     * 得到所有等级信息
     *
     * @return
     */
    public ArrayList<LevelPO> getAllLevel() {
        return levelDataHelper.prefixMatchQuery("ID", "");
    }

    /**
     * 得到对应的等级
     *
     * @param credit
     * @return
     */
    public int getLevelByCredit(int credit) {
        ArrayList<LevelPO> levelPOs = getAllLevel();
        levelPOs.sort((LevelPO l1, LevelPO l2) -> l1.getLevel() - l2.getLevel());
        for (LevelPO levelPO : levelPOs) {
            if (levelPO.getCredit() > credit) {
                return levelPO.getLevel() - 1;
            }
        }
        return getAllLevel().size();
    }

    /**
     * 删除客户对应所有信用记录
     *
     * @param clientID
     * @return 是否删除成功
     */
    private boolean deleteAllCredit(String clientID) {
        ArrayList<CreditPO> creditPOs = searchCreditByID(clientID);
        for (CreditPO creditPO : creditPOs) {
            if (creditDataHelper.delete("orderID", creditPO.getOrderID()) == ResultMessage.FAILED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 得到酒店工作人员ID最大值
     *
     * @return
     */
    private String getMaxStaffID() {
        ArrayList<StaffPO> staffPOs = staffDataHelper.prefixMatchQuery("staffID", "");
        if (staffPOs.size() == 0) {
            return "300001";
        } else {
            StaffPO staffPO = staffPOs.get(staffPOs.size() - 1);
            String str = staffPO.getStaffID();
            int i = Integer.parseInt(str);
            i = i + 1;
            return i + "";
        }
    }

    /**
     * 得到网站营销人员ID最大值
     *
     * @return
     */
    private String getMaxSalesmanID() {
        ArrayList<SalesmanPO> salesmanPOs = salesmanDataHelper.prefixMatchQuery("salesmanID", "");
        if (salesmanPOs.size() == 0) {
            return "100001";
        } else {
            SalesmanPO salesmanPO = salesmanPOs.get(salesmanPOs.size() - 1);
            String str = salesmanPO.getSalesmanID();
            int i = Integer.parseInt(str);
            i = i + 1;
            return i + "";
        }
    }

    /**
     * 得到客户ID最大值
     *
     * @return
     */
    private String getMaxClientID() {
        ArrayList<ClientPO> clientPOs = clientDataHelper.prefixMatchQuery("clientID", "");
        if (clientPOs.size() == 0) {
            return "000000001";
        } else {
            ClientPO clientPO = clientPOs.get(clientPOs.size() - 1);
            String str = clientPO.getClientID();
            int i = Integer.parseInt(str) + 1;
            String result = i + "";
            while (result.length() < 9) {
                result = "0" + result;
            }
            return result;
        }
    }

    /**
     * 判断账号是否存在
     *
     * @param account
     * @param userType
     * @return
     */
    private boolean checkAccountExist(String account, String userType) {
        if (userType.equals("client")) {
            return clientDataHelper.exactlyQuery("account", account) != null;
        } else if (userType.equals("staff")) {
            return staffDataHelper.exactlyQuery("account", account) != null;
        } else if (userType.equals("salesman")) {
            return salesmanDataHelper.exactlyQuery("account", account) != null;
        } else {
            return false;
        }
    }

    /**
     * 判断是否可以升级
     *
     * @param level
     * @param credit
     * @return
     */
    private boolean reachedLevel(int level, int credit) {
        if (level == getLevel(getAllLevel().size() + "").getLevel()) {
            return true;
        } else {
            return credit >= getLevel(level + "").getCredit() && credit < getLevel((level + 1) + "").getCredit();
        }
    }

}
