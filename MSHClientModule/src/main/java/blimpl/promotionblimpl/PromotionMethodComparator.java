package blimpl.promotionblimpl;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Created by SilverNarcissus on 2016/12/22.
 * Done on 2016/12/22
 */
class PromotionMethodComparator implements Comparator<Method> {

    @Override
    public int compare(Method o1, Method o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
