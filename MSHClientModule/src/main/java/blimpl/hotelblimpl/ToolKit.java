package blimpl.hotelblimpl;

/**
 * Created by SilverNarcissus on 2016/12/2.
 *
 */
public class ToolKit {
    /**
     * 通过容器的ID生成组分的ID
     *
     * @param ID     容器的ID
     * @param number 组分的代号
     * @return 生成的组分的ID
     */
    public static String generateID(String ID, int number) {
        String cache = String.valueOf(number);
        if (cache.length() == 1) {
            cache = "0" + cache;
        }
        return ID + cache;
    }
}
