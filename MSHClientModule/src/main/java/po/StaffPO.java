package po;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class StaffPO {
    private String staffID;
    private String staffName;
    private String hotelID;
    private String account;
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
}
