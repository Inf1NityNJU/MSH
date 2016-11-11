import bl.userbl.Client;
import bl.userbl.User;
import bl.userbl.UserBLServiceImpl;
import util.DateUtil;
import vo.ClientVO;
import vo.UserVO;

/**
 * Created by Kray on 2016/11/11.
 */
public class t {
    public static void main(String[] args) {
        User user = new Client();
        UserVO userVO = new ClientVO("000000008","老宋",0, new DateUtil(2016,1,1),500,0);
        UserBLServiceImpl userBLService = new UserBLServiceImpl(user);
        System.out.println("ADD");
        userBLService.add(userVO);
    }
}
