package vo;

/**
 * Created by Kray on 2016/10/12.
 */
public class StaffVO {
    public String staffID;
    public String staffName;
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
