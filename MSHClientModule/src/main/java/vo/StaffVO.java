package vo;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class StaffVO extends UserVO {
    /**
     * 酒店工作人员ID
     */
    public String staffID;
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
     * 酒店工作人员构造方法,包括 id,姓名,酒店 id
     *
     * @param staffID
     * @param name
     * @param hotelID
     */
    public StaffVO(String staffID, String name, String hotelID, String account) {
        this.staffID = staffID;
        this.staffName = name;
        this.hotelID = hotelID;
        this.account = account;
    }

    public StaffVO() {

    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof StaffVO) {
            StaffVO staffVO = (StaffVO) o;
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
    private boolean compareData(StaffVO staffVO) {
        return judgeEqual(staffID, staffVO.staffID)
                && judgeEqual(staffName, staffVO.staffName)
                && judgeEqual(hotelID, staffVO.hotelID)
                && judgeEqual(account, staffVO.account);
    }
}
