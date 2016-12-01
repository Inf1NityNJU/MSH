package po;

import java.io.Serializable;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class StaffPO extends UserPO implements Serializable {
    /**
     * 酒店工作人员ID
     */
    private String staffID;
    /**
     * 酒店工作人员姓名
     */
    private String staffName;
    /**
     * 酒店工作人员对应酒店ID
     */
    private String hotelID;
    /**
     * 酒店工作人员账号
     */
    private String account;
    /**
     * 酒店工作人员密码
     */
    private String password;

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public StaffPO(String staffID, String staffName, String hotelID, String account, String password) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.hotelID = hotelID;
        this.account = account;
        this.password = password;
    }

    public StaffPO() {

    }

    /**
     * 比较两个PO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof StaffPO) {
            StaffPO staffPO = (StaffPO) o;
            return compareData(staffPO);
        }
        return false;
    }

    /**
     * 生成对象的hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return staffID.hashCode();
    }

    /**
     * 分别比较每个数据
     *
     * @param staffPO
     * @return 比较结果
     */
    private boolean compareData(StaffPO staffPO) {
        return judgeEqual(staffID, staffPO.staffID)
                && judgeEqual(staffName, staffPO.staffName)
                && judgeEqual(hotelID, staffPO.hotelID)
                && judgeEqual(account, staffPO.account)
                && judgeEqual(password, staffPO.password);
    }
}
