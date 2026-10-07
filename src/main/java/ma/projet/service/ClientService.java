package ma.projet.service;


import ma.projet.entities.Client;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

public class ClientService extends AbstractFacade<Client> {

    public ClientService() {
        super(Client.class);
    }
    public Client findByCin(String cin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("SELECT c FROM Client c WHERE c.cin = :cin", Client.class)
                    .setParameter("cin", cin)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

}
