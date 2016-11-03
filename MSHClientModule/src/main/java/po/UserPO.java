package po;

/**
 * Created by Kray on 2016/10/30.
 */
public class UserPO {
    private String account;
    private String password;

    public UserPO(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
