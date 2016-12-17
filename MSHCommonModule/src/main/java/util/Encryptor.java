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
        boolean isTest = true;
        if(isTest){
            return str;
        }else{
            String result = "";
            for(int i = 0; i < str.length(); ++i) {
                result += (char)(str.charAt(i)<<1);
            }
            return result;
        }
    }

}
