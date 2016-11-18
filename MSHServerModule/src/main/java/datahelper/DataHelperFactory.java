package datahelper;

import datahelper.DataHelper;
import datahelper.HibernateHelper;

/**
 * Created by SilverNarcissus on 16/11/14.
 */
public class DataHelperFactory {
    private static DataHelper dataHelper;

    /**
     * 构建以Hibernate框架为数据存取方式的DataHelper
     *
     * @return 以Hibernate框架为数据存取方式的DataHelper
     */
    public synchronized static DataHelper getHibernateDataHelper() {
        if (dataHelper == null) {
            dataHelper = new HibernateHelper();
        }
        return dataHelper;
    }
}
