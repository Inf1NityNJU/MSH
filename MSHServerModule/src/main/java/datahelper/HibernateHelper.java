package datahelper;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import util.ResultMessage;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.
 * DataHelper which use hibernate frame
 */
public class HibernateHelper implements DataHelper {

    private SessionFactory sessionFactory;
    private Session session;

    public HibernateHelper() {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure().buildSessionFactory();
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
     * @return 保存操作的结果信息
     * @since 1.6
     */
    @Override
    public <T> ResultMessage save(Class<T> classType, Object o) {
        try {
            setUpSession();
            session.save(o);
            commitAndClose();
        } catch (PersistenceException e) {
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
     *
     * @param o
     * @return 更新操作的结果信息
     */
    @Override
    public <T> ResultMessage update(Class<T> classType, Object o) {
        try {
            setUpSession();
            session.update(o);
            commitAndClose();
        } catch (OptimisticLockException e) {
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
    @Override
    public <T> ResultMessage delete(Class<T> classType, String ID, String key) {
        try {
            Object o = exactlyQuery(classType, key, ID);
            if (o == null) {
                return ResultMessage.NOT_EXIST;
            }
            setUpSession();
            session.delete(o);
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
     * @param classType
     * @param field
     * @param value
     * @param <T>
     * @return
     */
    @Override
    public <T> T exactlyQuery(Class<T> classType, String field, Object value) {
        try {
            Criteria criteria = SetUpCriteria(classType);
            criteria.add(Restrictions.eq(field, value));
            T result = (criteria.list().size() == 0) ? null : (T) criteria.list().get(0);
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    /**
     * 前缀匹配查询PO
     *
     * @param field 属性名称
     * @param value 属性值
     * @return PO列表
     */
    @Override
    public <T> ArrayList<T> prefixMatchQuery(Class<T> classType, String field, String value) {
        value = value + "%";
        return likePatternQuery(classType, field, value);
    }

    /**
     * 后缀匹配查询PO
     *
     * @param field 属性名称
     * @param value 属性值
     * @return PO列表
     */
    @Override
    public <T> ArrayList<T> suffixMatchQuery(Class<T> classType, String field, String value) {
        value = "%" + value;
        return likePatternQuery(classType, field, value);
    }

    /**
     * 模糊查询PO
     *
     * @param field
     * @param value
     * @return PO列表
     */
    @Override
    public <T> ArrayList<T> fuzzyMatchQuery(Class<T> classType, String field, String value) {
        value = "%" + value + "%";
        return likePatternQuery(classType, field, value);
    }

    /**
     * 值域范围查询PO
     *
     * @param field
     * @param min
     * @param max
     * @return PO列表
     */
    @Override
    public <T> ArrayList<T> rangeQuery(Class<T> classType, String field, Object min, Object max) {
        try {
            Criteria criteria = SetUpCriteria(classType);
            criteria.add(Restrictions.between(field, min, max));
            ArrayList<T> arrayList = (ArrayList<T>) criteria.list();
            session.close();
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return new ArrayList<T>();
        }
    }

    /**
     * 生成一个新的匹配标准
     *
     * @return
     * @throws ClassNotFoundException
     */
    private Criteria SetUpCriteria(Class classType) throws ClassNotFoundException {
        setUpSession();
        return session.createCriteria(classType);
    }

    /**
     * 利用模糊查找返回符合条件的PO列表
     *
     * @param field
     * @param value
     * @return PO列表
     */
    private <T> ArrayList<T> likePatternQuery(Class<T> classType, String field, String value) {
        try {
            Criteria criteria = SetUpCriteria(classType);
            criteria.add(Restrictions.like(field, value));
            ArrayList<T> arrayList = (ArrayList<T>) criteria.list();
            session.close();
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return new ArrayList<T>();
        }
    }
}
