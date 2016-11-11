package util;

/**
 * Created by SilverNarcissus on 16/11/9.
 */
public class EqualJudgeHelper {

    /**
     * 判断两个int变量是否相等
     * @param i1;
     * @param i2;
     * @return 判断结果
     */
    public static boolean judgeEqual(int i1,int i2){
        return i1==i2;
    }

    /**
     * 判断两个double变量是否相等
     * @param d1;
     * @param d2;
     * @return 判断结果
     */
    public static boolean judgeEqual(double d1,double d2){
        return d1==d2;
    }

    /**
     * 判断两个对象是否相等
     * @param o1;
     * @param o2;
     * @return 判断结果
     */
    public static boolean judgeEqual(Object o1,Object o2){
        if(o1 == null&&o2 == null){
            return true;
        }
        else if(o1 == null){
            return false;
        }
        return o1.equals(o2);
    }
}
