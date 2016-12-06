package vo;

import util.DateUtil;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/11/23.
 */
public class ClientVO_Register extends ClientVO {

    /**
     * 客户姓名
     */
    public String clientName;
    /**
     * 客户等级
     */
    public int level;
    /**
     * 客户生日
     */
    public DateUtil birthday;
    /**
     * 客户类别
     */
    public int type;
    /**
     * 客户联系方式
     */
    public String contactInfo;
    /**
     * 客户所属企业,若是普通用户则为空
     */
    public String enterprise;
    /**
     * 账号
     */
    public String account;
    /**
     * 密码
     */
    public String password;

    /**
     * 注册时的客户构造方法
     *
     * @param birthday
     * @param type
     * @param enterprise
     * @param account
     * @param password
     */
    public ClientVO_Register(DateUtil birthday, int type, String enterprise, String account, String password) {
        this.clientName = account;
        this.credit = 500;
        this.level = 1;
        this.type = type;
        this.birthday = birthday;
        this.enterprise = enterprise;
        this.contactInfo = "";
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
        if (o instanceof ClientVO_Register) {
            ClientVO_Register clientVO = (ClientVO_Register) o;
            return compareData(clientVO);
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
        return level;
    }

    /**
     * 分别比较每个数据
     *
     * @param clientVO
     * @return 比较结果
     */
    private boolean compareData(ClientVO_Register clientVO) {
        return judgeEqual(clientName, clientVO.clientName)
                && judgeEqual(credit, clientVO.credit)
                && judgeEqual(level, clientVO.level)
                && judgeEqual(birthday, clientVO.birthday)
                && judgeEqual(type, clientVO.type)
                && judgeEqual(contactInfo, clientVO.contactInfo)
                && judgeEqual(enterprise, clientVO.enterprise)
                && judgeEqual(account, clientVO.account)
                && judgeEqual(password, clientVO.password);

    }
}
