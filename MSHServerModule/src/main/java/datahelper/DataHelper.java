package datahelper;

import util.ResultMessage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public interface DataHelper {

    public <T> ResultMessage save(Class<T> classType,Object o);

    public <T> ResultMessage update(Class<T> classType,Object o);

    public <T> ResultMessage delete(Class<T> classType,String ID,String key);

    public <T> T exactlyQuery(Class<T> classType, String field, Object value);

    public <T> ArrayList<T> prefixMatchQuery(Class<T> classType, String field, String value);

    public <T> ArrayList<T> suffixMatchQuery(Class<T> classType, String field, String value);

    public <T> ArrayList<T> fuzzyMatchQuery(Class<T> classType, String field, String value);

    public <T> ArrayList<T> rangeQuery(Class<T> classType, String field, Object min, Object max);
}
