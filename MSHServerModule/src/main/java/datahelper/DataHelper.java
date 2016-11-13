package datahelper;

import util.ResultMessage;

import java.util.List;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public interface DataHelper {
    public ResultMessage save(Object o);
    public ResultMessage update(Object o);
    public ResultMessage delete(Object o);
    public Object exactlyQuery(String field, Object value);
    public List<Object> prefixMatchSearch(String field, String value);
    public List<Object> suffixMatchQuery(String field, String value);
    public List<Object> FuzzyQuery(String field, String value);
    public List<Object> RangeQuery(String field, Object min, Object max);

}
