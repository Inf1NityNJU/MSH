package util;

/**
 * Created by Kray on 2016/12/2.
 */
public class Encryptor {

    /**
     * 加密算法
     *
     * @param str
     * @return 加密后的字符串
     */
    public static String encrypt(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result = result + "!" + str.charAt(i);
        }
        return result;
    }

}
