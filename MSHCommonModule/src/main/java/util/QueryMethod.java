package util;

/**
 * Created by SilverNarcissus on 2016/12/1.
 *
 */
public enum QueryMethod {
    Prefix, Suffix, Fuzz, Range, Full;

    public boolean valueShouldBeString(){
        return this.equals(QueryMethod.Prefix)||this.equals(QueryMethod.Suffix)||this.equals(QueryMethod.Fuzz);
    }
}
