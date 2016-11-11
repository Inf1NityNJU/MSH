package vo;

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
     * 酒店工作人员构造方法,包括 id,姓名,酒店 id
     * @param staffID
     * @param name
     * @param hotelID
     */
    public StaffVO(String staffID, String name, String hotelID){
        this.staffID = staffID;
        this.staffName = name;
        this.hotelID = hotelID;
    }
}
