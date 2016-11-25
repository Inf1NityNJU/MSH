package vo;

import util.DateUtil;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class ClientVO extends UserVO {
    /**
     * 客户ID
     */
    public String clientID;
    /**
     * 客户姓名
     */
    public String clientName;
    /**
     * 客户信用值
     */
    public int credit;
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
     * 客户构造方法,包括 id,姓名,等级,生日,信用值,类型
     *
     * @param id
     * @param name
     * @param level
     * @param birthday
     * @param credit
     * @param type
     */
    public ClientVO(String id, String name, int level, DateUtil birthday, int credit, int type, String contactInfo, String enterprise, String account) {
        this.clientID = id;
        this.clientName = name;
        this.credit = credit;
        this.level = level;
        this.type = type;
        this.birthday = birthday;
        this.contactInfo = contactInfo;
        this.enterprise = enterprise;
        this.account = account;
    }

    public ClientVO(){

    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ClientVO) {
            ClientVO clientVO = (ClientVO) o;
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
    private boolean compareData(ClientVO clientVO) {
        return judgeEqual(clientID, clientVO.clientID)
                && judgeEqual(clientName, clientVO.clientName)
                && judgeEqual(credit, clientVO.credit)
                && judgeEqual(level, clientVO.level)
                && judgeEqual(birthday, clientVO.birthday)
                && judgeEqual(type, clientVO.type)
                && judgeEqual(contactInfo, clientVO.contactInfo)
                && judgeEqual(enterprise, clientVO.enterprise)
                && judgeEqual(account, clientVO.account);
    }
}

