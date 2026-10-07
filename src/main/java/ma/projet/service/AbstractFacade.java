package ma.projet.service;

import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractFacade<T> implements IDao<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean create(T o) {
        Session session = null;
        Transaction tx = null;
        boolean state = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            state = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return state;
    }

    @Override
    public boolean update(T o) {
        Session session = null;
        Transaction tx = null;
        boolean state = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            state = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return state;
    }

    @Override
    public boolean delete(T o) {
        Session session = null;
        Transaction tx = null;
        boolean state = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            state = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return state;
    }

    @Override
    public T findById(Long id) {
        Session session = null;
        T entity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entity = session.get(entityClass, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entity;
    }

    @Override
    public T findById(int id) {
        return findById((long) id);
    }

    @Override
    public List<T> findAll() {
        Session session = null;
        List<T> entities = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("from " + entityClass.getSimpleName(), entityClass).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entities;
    }
}
