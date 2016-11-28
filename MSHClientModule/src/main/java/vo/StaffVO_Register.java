package vo;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/11/23.
 */
public class StaffVO_Register extends StaffVO {
    /**
     * 酒店工作人员姓名
     */
    public String staffName;
    /**
     * 酒店工作人员对应酒店ID
     */
    public String hotelID;
    /**
     * 账号
     */
    public String account;
    /**
     * 密码
     */
    public String password;

    /**
     * 酒店工作人员构造方法,包括 id,姓名,酒店 id
     *
     * @param name
     * @param hotelID
     */
    public StaffVO_Register(String name, String hotelID, String account, String password) {
        this.staffName = name;
        this.hotelID = hotelID;
        this.account = account;
        this.password = password;
    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof StaffVO_Register) {
            StaffVO_Register staffVO = (StaffVO_Register) o;
            return compareData(staffVO);
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
     * @param staffVO
     * @return 比较结果
     */
    private boolean compareData(StaffVO_Register staffVO) {
        return judgeEqual(staffName, staffVO.staffName)
                && judgeEqual(hotelID, staffVO.hotelID)
                && judgeEqual(account, staffVO.account)
                && judgeEqual(password, staffVO.password);
    }
}
