package vo;

import util.DateUtil;

/**
 * Created by Kray on 2016/10/12.
 */
public class ClientVO {
    public String clientID;
    public String clientName;
    public int credit;
    public int level;
    public DateUtil birthday;
    public int type;

    /**
     * 客户构造方法,包括 id,姓名,等级,生日,信用值,类型
     * @param id
     * @param name
     * @param level
     * @param birthday
     * @param credit
     * @param type
     */
    public ClientVO(String id, String name, int level, DateUtil birthday, int credit, int type){
        this.clientID = id;
        this.clientName = name;
        this.credit = credit;
        this.level = level;
        this.type = type;
        this.birthday = birthday;
    }
}

