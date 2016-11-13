package datahelper;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import util.ResultMessage;

import java.util.List;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public class HibernateHelper implements DataHelper{

    private SessionFactory sessionFactory;
    private Session session;
    private String classFullName;

    private HibernateHelper(String cfgLocation,String classFullName) {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure(cfgLocation).buildSessionFactory();
        this.classFullName=classFullName;
    }

    /**
     * 初始化Session
     */
    private void setUpSession() {
        Session session = sessionFactory.openSession();
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
     */
    public ResultMessage save(Object o) throws HibernateException{
        try {
            setUpSession();
            session.contains(o);
            session.save(o);
            commitAndClose();
        }
        catch (S){

        }
        catch (Exception e){
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 更新数据表中的对象
     *
     * @param o
     */
    public ResultMessage update(Object o) throws HibernateException{
        setUpSession();
        session.update(o);
        commitAndClose();
    }

    /**
     * 删除数据表中的对象
     * @param ID
     * @throws ClassNotFoundException
     * @throws HibernateException
     */
    public ResultMessage delete(String ID) throws ClassNotFoundException, HibernateException {
        setUpSession();
        session.delete(exactlyQuery("ID", ID));
        commitAndClose();
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
    public Object exactlyQuery(String field, Object value) throws ClassNotFoundException, HibernateException {
        Criteria criteria = SetUpCriteria();
        criteria.add(Restrictions.eq(field, value));
        return (criteria.list().size() != 0) ? null : criteria.list().get(0);
    }

    /**
     * 前缀匹配查询PO
     *
     * @param field 属性名称
     * @param value 属性值
     * @return PO列表
     * @throws ClassNotFoundException
     * @throws HibernateException
     */
    public List<Object> prefixMatchSearch(String field, String value) throws ClassNotFoundException, HibernateException {
        Criteria criteria = SetUpCriteria();
        criteria.add(Restrictions.like(field, value + "%"));
        return criteria.list();
    }

    /**
     * 后缀匹配查询PO
     *
     * @param field 属性名称
     * @param value 属性值
     * @return PO列表
     * @throws ClassNotFoundException
     * @throws HibernateException
     */
    public List<Object> suffixMatchQuery(String field, String value) throws ClassNotFoundException, HibernateException {
        Criteria criteria = SetUpCriteria();
        criteria.add(Restrictions.like(field, "%" + value));
        return criteria.list();
    }

    /**
     * 模糊查询PO
     *
     * @param field
     * @param value
     * @return PO列表
     * @throws ClassNotFoundException
     * @throws HibernateException
     */
    public List<Object> FuzzyQuery(String field, String value) throws ClassNotFoundException, HibernateException {
        Criteria criteria = SetUpCriteria();
        criteria.add(Restrictions.like(field, "%" + value + "%"));
        return criteria.list();
    }

    /**
     * 值域范围查询PO
     *
     * @param field
     * @param min
     * @param max
     * @return PO列表
     * @throws ClassNotFoundException
     * @throws HibernateException
     */
    public List<Object> RangeQuery(String field, Object min, Object max) throws ClassNotFoundException, HibernateException {
        Criteria criteria = SetUpCriteria();
        criteria.add(Restrictions.between(field, min, max));
        return criteria.list();
    }

    /**
     * 生成一个新的匹配标准
     *
     * @return
     * @throws ClassNotFoundException
     */
    private Criteria SetUpCriteria() throws ClassNotFoundException {
        return session.createCriteria(Class.forName("hotelPO"));
    }
}
