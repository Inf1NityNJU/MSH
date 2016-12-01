package datahelper;

import util.CriteriaClause;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public interface DataHelper<T> {

    public ResultMessage save(Object o);

    public ResultMessage update(Object o);

    public ResultMessage delete(String key, String ID);

    public T exactlyQuery(String field, Object value);

    public ArrayList<T> fullMatchQuery(String field, Object value);

    public ArrayList<T> prefixMatchQuery(String field, String value);

    public ArrayList<T> suffixMatchQuery(String field, String value);

    public ArrayList<T> fuzzyMatchQuery(String field, String value);

    public ArrayList<T> rangeQuery(String field, Object min, Object max);

    public ArrayList<T> multiCriteriaQuery(ArrayList<CriteriaClause> criteriaClauses);

}
