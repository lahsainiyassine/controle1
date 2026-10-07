package ma.projet.service;


import ma.projet.entities.Contrat;
import ma.projet.entities.StatutContrat;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ContratService extends AbstractFacade<Contrat> {

    public ContratService() {

        super(Contrat.class);
    }
    // Méthode métier 2 : Afficher les contrats d'un client (par CIN)
    public List<Contrat> findContratsByClientCin(String cin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("SELECT c FROM Contrat c WHERE c.client.cin = :cin", Contrat.class)
                    .setParameter("cin", cin)
                    .getResultList();
        } finally {
            session.close();
        }
    }

    public void afficherContratsParClientCin(String cin) {
        List<Contrat> list = findContratsByClientCin(cin);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Contrats du client (CIN: " + cin + ") :");
        for (Contrat c : list) {
            System.out.printf("- Contrat N°: %d | Du: %s au: %s | Statut: %s | Assurance: %s\n",
                    c.getId(),
                    sdf.format(c.getDateDebut()),
                    sdf.format(c.getDateFin()),
                    c.getStatut(),
                    c.getAssurance() != null ? c.getAssurance().getType() : "N/A");
        }
    }

    // Méthode métier 3 : Afficher les contrats associés à une assurance (par type)
    public List<Contrat> findContratsByAssuranceType(String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("SELECT c FROM Contrat c WHERE c.assurance.type = :type", Contrat.class)
                    .setParameter("type", type)
                    .getResultList();
        } finally {
            session.close();
        }
    }

    public void afficherContratsParTypeAssurance(String type) {
        List<Contrat> list = findContratsByAssuranceType(type);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Contrats associés à l'assurance de type : " + type);
        for (Contrat c : list) {
            System.out.printf("- Contrat N°: %d | Client: %s %s | Du: %s au: %s | Statut: %s\n",
                    c.getId(),
                    c.getClient().getNom(),
                    c.getClient().getPrenom(),
                    sdf.format(c.getDateDebut()),
                    sdf.format(c.getDateFin()),
                    c.getStatut());
        }
    }

    // Méthode métier 4 : Afficher les contrats actifs dont la dateFin est supérieure à une date donnée (non expirés)
    public List<Contrat> findContratsActifsNonExpires(Date dateRef) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "SELECT c FROM Contrat c WHERE c.statut = :statut AND c.dateFin > :dateRef",
                            Contrat.class
                    )
                    .setParameter("statut", StatutContrat.ACTIF)
                    .setParameter("dateRef", dateRef, TemporalType.DATE)
                    .getResultList();
        } finally {
            session.close();
        }
    }

    public void afficherContratsActifsNonExpires(Date dateRef) {
        List<Contrat> list = findContratsActifsNonExpires(dateRef);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Contrats ACTIFS non expirés après le " + sdf.format(dateRef) + " :");
        for (Contrat c : list) {
            System.out.printf("- Contrat N°: %d | Client: %s | Assurance: %s | Date Fin: %s\n",
                    c.getId(),
                    c.getClient().getNom() + " " + c.getClient().getPrenom(),
                    c.getAssurance().getType(),
                    sdf.format(c.getDateFin()));
        }
    }

}
