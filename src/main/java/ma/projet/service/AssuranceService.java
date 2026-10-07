package ma.projet.service;

import ma.projet.entities.Assurance;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AssuranceService extends AbstractFacade<Assurance> {

    public AssuranceService() {
        super(Assurance.class);
    }

    public Assurance findByType(String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("SELECT a FROM Assurance a WHERE a.type = :type", Assurance.class)
                    .setParameter("type", type)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public List<Assurance> findAllByType(String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("SELECT a FROM Assurance a WHERE a.type = :type", Assurance.class)
                    .setParameter("type", type)
                    .getResultList();
        } finally {
            session.close();
        }
    }
}
