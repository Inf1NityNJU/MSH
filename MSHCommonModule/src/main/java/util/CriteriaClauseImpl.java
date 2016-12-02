package util;

/**
 * Created by SilverNarcissus on 2016/12/1.
 */
public class CriteriaClauseImpl implements CriteriaClause {
    /**
     * 查询域名
     */
    private String field;
    /**
     * 查询域值
     */
    private Object value;
    /**
     * 另一个域值
     */
    private Object anotherValue;
    /**
     * 查询方式
     */
    private QueryMethod queryMethod;
    /**
     * 关键字段
     * 由该类自行生成，用户不需要填写
     */
    private String keyWord;

    private CriteriaClauseImpl(String field, Object value, Object anotherValue, QueryMethod queryMethod) throws IllegalArgumentException {
        //检查参数是否合理
        //检查null
        if (field == null) {
            throw new IllegalArgumentException("Field argument is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value argument is null");
        }
        if (queryMethod == null) {
            throw new IllegalArgumentException("QueryMethod argument is null");
        }

        this.field = field;
        this.value = value;
        this.queryMethod = queryMethod;

        //检查查询方式是否匹配
        if (queryMethod.valueShouldBeString()&&!(value instanceof String)) {
            throw new IllegalArgumentException("Value for " + queryMethod + " query isn't a string");
        }
        //按照查询方式改变String
        switch (queryMethod) {
            case Prefix:
                keyWord = value + "%";
                break;
            case Suffix:
                keyWord = "%" + value;
                break;
            case Fuzz:
                keyWord = "%" + value + "%";
                break;
            case Range:
                //检查是否上限为空
                if (anotherValue == null) {
                    throw new IllegalArgumentException("AnotherValue argument is null");
                }
                //
                this.anotherValue = anotherValue;
                //
                break;
            case Full:
                keyWord = null;
                break;
            default:
                throw new IllegalArgumentException("Illegal query method");
        }
    }

    /**
     * 生成单一字段查询的条目
     *
     * @param field       查询字段名称
     * @param value       查询字段值
     * @param queryMethod 查询方式
     * @return 生成的单一字段查询条目<br>
     * 如果有非法参数，将返回null并打印异常栈情况
     */
    public static CriteriaClauseImpl createSingleValueQuery(String field, Object value, QueryMethod queryMethod) {
        try {
            return new CriteriaClauseImpl(field, value, null, queryMethod);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成双字段范围查询的条目
     *
     * @param field       查询字段名称
     * @param min         查询字段下限值
     * @param max         查询字段上限值
     * @param queryMethod 查询方式
     * @return 生成的双字段范围查询条目<br>
     * 如果有非法参数，将返回null并打印异常栈情况
     */
    public static CriteriaClauseImpl createRangeValueQuery(String field, Object min, Object max, QueryMethod queryMethod) {
        try {
            return new CriteriaClauseImpl(field, min, max, queryMethod);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public QueryMethod getQueryMethod() {
        return queryMethod;
    }

    public Object getAnotherValue() {
        return anotherValue;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
