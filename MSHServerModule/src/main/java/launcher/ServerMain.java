package launcher;

import network.ServerHelper;

/**
 * Created by SilverNarcissus on 2016/12/5.
 */
public class ServerMain {
    public static void main(String[] args) {
        //打开网络连接
        ServerHelper.buildNetwork();
        //打开定时检测器
        DataChecker dataChecker = new DataChecker();
        dataChecker.lunch();
        new ServiceGUI().showGUI();
    }
}
