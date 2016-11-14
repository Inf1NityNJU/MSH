package datafactory;

import datahelper.DataHelper;
import datahelper.HibernateHelper;

/**
 * Created by SilverNarcissus on 16/11/14.
 */
public class DataHelperFactory {

    /**
     * 构建以Hibernate框架为数据存取方式的DataHelper
     * @param mappingClassFullName 映射类的全名
     * @return 以Hibernate框架为数据存取方式的DataHelper
     */
    public static DataHelper getHibernateDataHelper(String mappingClassFullName){
         return new HibernateHelper(mappingClassFullName);
    }
}
