package launcher;

/**
 * Created by SilverNarcissus on 2016/12/5.
 */
public class ServerMain {
    public static void main(String[] args) {
        //打开定时检测器
        new ServiceGUI().showGUI();
        DataChecker dataChecker = new DataChecker();
        dataChecker.launch();
    }
}
