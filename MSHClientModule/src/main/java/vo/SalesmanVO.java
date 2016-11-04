package vo;

/**
 * Created by Kray on 2016/10/12.
 */
public class SalesmanVO {
    /**
     * 网站营销人员ID
     */
    public String salesmanID;
    /**
     * 网站营销人员姓名
     */
    public String salesmanName;

    /**
     * 网站营销人员的构造方法,包括人员ID和姓名
     * @param id
     * @param name
     */
    public SalesmanVO(String id, String name){
        this.salesmanID = id;
        this.salesmanName = name;
    }
}
