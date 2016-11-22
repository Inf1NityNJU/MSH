package datahelper;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import util.ResultMessage;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.
 * DataHelper which use hibernate frame
 */
public class HibernateHelper<T> implements DataHelper<T> {

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
    public ResultMessage save(Object o) {
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
    public ResultMessage update( Object o) {
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
    public ResultMessage delete( String key, String ID) {
        try {
            Object o = exactlyQuery(key, ID);
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
     * @param field
     * @param value
     * @return
     */
    @Override
    public T exactlyQuery(String field, Object value) {
        try {
            Criteria criteria = SetUpCriteria();
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
    public ArrayList<T> prefixMatchQuery(String field, String value) {
        value = value + "%";
        return likePatternQuery(field, value);
    }

    /**
     * 后缀匹配查询PO
     *
     * @param field 属性名称
     * @param value 属性值
     * @return PO列表
     */
    @Override
    public ArrayList<T> suffixMatchQuery( String field, String value) {
        value = "%" + value;
        return likePatternQuery(field, value);
    }

    /**
     * 模糊查询PO
     *
     * @param field
     * @param value
     * @return PO列表
     */
    @Override
    public ArrayList<T> fuzzyMatchQuery(String field, String value) {
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
     */
    @Override
    public ArrayList<T> rangeQuery( String field, Object min, Object max) {
        try {
            Criteria criteria = SetUpCriteria();
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
     * @return 新的匹配标准
     * @throws ClassNotFoundException
     */
    private Criteria SetUpCriteria() throws ClassNotFoundException {
        setUpSession();
        return session.createCriteria(getSuperClassGenricType(getClass(),0));
    }

    /**
     * 利用模糊查找返回符合条件的PO列表
     *
     * @param field
     * @param value
     * @return PO列表
     */
    private  ArrayList<T> likePatternQuery(String field, String value) {
        try {
            Criteria criteria = SetUpCriteria();
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

    /**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     *
     *@param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("unchecked")
    private Class<Object> getSuperClassGenricType(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }
}
