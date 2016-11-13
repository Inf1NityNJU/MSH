package datahelper;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import util.ResultMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SilverNarcissus on 16/11/12.
 * DataHelper which use hibernate frame
 */
public class HibernateHelper implements DataHelper {

    private SessionFactory sessionFactory;
    private Session session;
    private String classFullName;

    public HibernateHelper(String cfgLocation) {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure(cfgLocation).buildSessionFactory();
    }

    /**
     * 设置PO类的全名
     * @param classFullName
     */
    public void setClassName(String classFullName){
        this.classFullName=classFullName;
    }

    /**
     * 初始化Session
     */
    private void setUpSession() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    /**
     * 提交事务及关闭session
     */
    private void commitAndClose() {
        session.getTransaction().commit();
        session.close();
    }

    /**
     * 将对象添加到数据库表中
     *
     * @param o
     * @since 1.6
     * @return 保存操作的结果信息
     */
    public ResultMessage save(Object o) {
        try {
            setUpSession();
            session.contains(o);
            session.save(o);
            commitAndClose();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return ResultMessage.EXIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 更新数据表中的对象
     * @param o
     * @return 更新操作的结果信息
     */
    public ResultMessage update(Object o) {
        try {
            setUpSession();
            session.update(o);
            commitAndClose();
        } catch (StaleStateException e) {
            e.printStackTrace();
            return ResultMessage.NOT_EXIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }


    /**
     * 删除数据表中的对象
     *
     * @param ID
     */
    public ResultMessage delete(String ID) {
        try {
            setUpSession();
            if (exactlyQuery("ID", ID) == null) {
                return ResultMessage.NOT_EXIST;
            }
            session.delete(exactlyQuery("ID", ID));
            commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 按照ID精确查找PO
     *
     * @param field 属性名称
     * @param value 属性值
     * @return
     * @throws ClassNotFoundException
     * @throws HibernateException
     */
    public Object exactlyQuery(String field, Object value) {
        try {
            Criteria criteria = SetUpCriteria();
            criteria.add(Restrictions.eq(field, value));
            return (criteria.list().size() != 0) ? null : criteria.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 前缀匹配查询PO
     *
     * @param field 属性名称
     * @param value 属性值
     * @return PO列表
     *
     */
    public List<Object> prefixMatchQuery(String field, String value) {
        value = value + "%";
        return likePatternQuery(field, value);
    }

    /**
     * 后缀匹配查询PO
     *
     * @param field 属性名称
     * @param value 属性值
     * @return PO列表
     *
     */
    public List<Object> suffixMatchQuery(String field, String value) {
        value = "%" + value;
        return likePatternQuery(field, value);
    }

    /**
     * 模糊查询PO
     *
     * @param field
     * @param value
     * @return PO列表
     *
     */
    public List<Object> FuzzyQuery(String field, String value) {
        value = "%" + value + "%";
        return likePatternQuery(field, value);
    }

    /**
     * 值域范围查询PO
     *
     * @param field
     * @param min
     * @param max
     * @return PO列表
     *
     */
    public List<Object> RangeQuery(String field, Object min, Object max) {
        try {
            Criteria criteria = SetUpCriteria();
            criteria.add(Restrictions.between(field, min, max));
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object>();
        }
    }

    /**
     * 生成一个新的匹配标准
     *
     * @return
     * @throws ClassNotFoundException
     */
    private Criteria SetUpCriteria() throws ClassNotFoundException {
        return session.createCriteria(Class.forName(classFullName));
    }

    /**
     * 利用模糊查找返回符合条件的PO列表
     *
     * @param field
     * @param value
     * @return PO列表
     */
    private List<Object> likePatternQuery(String field, String value) {
        try {
            Criteria criteria = SetUpCriteria();
            criteria.add(Restrictions.like(field, value));
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object>();
        }
    }
}
